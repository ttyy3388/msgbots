/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/
import './contextview.css';
import * as DOM from '../../dom.js';
import * as platform from '../../../common/platform.js';
import { toDisposable, Disposable, DisposableStore } from '../../../common/lifecycle.js';
import { Range } from '../../../common/range.js';
import { BrowserFeatures } from '../../canIUse.js';
/**
 * Lays out a one dimensional view next to an anchor in a viewport.
 *
 * @returns The view offset within the viewport.
 */
export function layout(viewportSize, viewSize, anchor) {
    const anchorEnd = anchor.offset + anchor.size;
    if (anchor.position === 0 /* Before */) {
        if (viewSize <= viewportSize - anchorEnd) {
            return anchorEnd; // happy case, lay it out after the anchor
        }
        if (viewSize <= anchor.offset) {
            return anchor.offset - viewSize; // ok case, lay it out before the anchor
        }
        return Math.max(viewportSize - viewSize, 0); // sad case, lay it over the anchor
    }
    else {
        if (viewSize <= anchor.offset) {
            return anchor.offset - viewSize; // happy case, lay it out before the anchor
        }
        if (viewSize <= viewportSize - anchorEnd) {
            return anchorEnd; // ok case, lay it out after the anchor
        }
        return 0; // sad case, lay it over the anchor
    }
}
export class ContextView extends Disposable {
    constructor(container, domPosition) {
        super();
        this.container = null;
        this.delegate = null;
        this.toDisposeOnClean = Disposable.None;
        this.toDisposeOnSetContainer = Disposable.None;
        this.shadowRoot = null;
        this.shadowRootHostElement = null;
        this.view = DOM.$('.context-view');
        this.useFixedPosition = false;
        this.useShadowDOM = false;
        DOM.hide(this.view);
        this.setContainer(container, domPosition);
        this._register(toDisposable(() => this.setContainer(null, 1 /* ABSOLUTE */)));
    }
    setContainer(container, domPosition) {
        if (this.container) {
            this.toDisposeOnSetContainer.dispose();
            if (this.shadowRoot) {
                this.shadowRoot.removeChild(this.view);
                this.shadowRoot = null;
                DOM.removeNode(this.shadowRootHostElement);
                this.shadowRootHostElement = null;
            }
            else {
                this.container.removeChild(this.view);
            }
            this.container = null;
        }
        if (container) {
            this.container = container;
            this.useFixedPosition = domPosition !== 1 /* ABSOLUTE */;
            this.useShadowDOM = domPosition === 3 /* FIXED_SHADOW */;
            if (this.useShadowDOM) {
                this.shadowRootHostElement = DOM.$('.shadow-root-host');
                this.container.appendChild(this.shadowRootHostElement);
                this.shadowRoot = this.shadowRootHostElement.attachShadow({ mode: 'open' });
                this.shadowRoot.innerHTML = `
					<style>
						${SHADOW_ROOT_CSS}
					</style>
				`;
                this.shadowRoot.appendChild(this.view);
                this.shadowRoot.appendChild(DOM.$('slot'));
            }
            else {
                this.container.appendChild(this.view);
            }
            const toDisposeOnSetContainer = new DisposableStore();
            ContextView.BUBBLE_UP_EVENTS.forEach(event => {
                toDisposeOnSetContainer.add(DOM.addStandardDisposableListener(this.container, event, (e) => {
                    this.onDOMEvent(e, false);
                }));
            });
            ContextView.BUBBLE_DOWN_EVENTS.forEach(event => {
                toDisposeOnSetContainer.add(DOM.addStandardDisposableListener(this.container, event, (e) => {
                    this.onDOMEvent(e, true);
                }, true));
            });
            this.toDisposeOnSetContainer = toDisposeOnSetContainer;
        }
    }
    show(delegate) {
        if (this.isVisible()) {
            this.hide();
        }
        // Show static box
        DOM.clearNode(this.view);
        this.view.className = 'context-view';
        this.view.style.top = '0px';
        this.view.style.left = '0px';
        this.view.style.zIndex = '2500';
        this.view.style.position = this.useFixedPosition ? 'fixed' : 'absolute';
        DOM.show(this.view);
        // Render content
        this.toDisposeOnClean = delegate.render(this.view) || Disposable.None;
        // Set active delegate
        this.delegate = delegate;
        // Layout
        this.doLayout();
        // Focus
        if (this.delegate.focus) {
            this.delegate.focus();
        }
    }
    getViewElement() {
        return this.view;
    }
    layout() {
        if (!this.isVisible()) {
            return;
        }
        if (this.delegate.canRelayout === false && !(platform.isIOS && BrowserFeatures.pointerEvents)) {
            this.hide();
            return;
        }
        if (this.delegate.layout) {
            this.delegate.layout();
        }
        this.doLayout();
    }
    doLayout() {
        // Check that we still have a delegate - this.delegate.layout may have hidden
        if (!this.isVisible()) {
            return;
        }
        // Get anchor
        let anchor = this.delegate.getAnchor();
        // Compute around
        let around;
        // Get the element's position and size (to anchor the view)
        if (DOM.isHTMLElement(anchor)) {
            let elementPosition = DOM.getDomNodePagePosition(anchor);
            around = {
                top: elementPosition.top,
                left: elementPosition.left,
                width: elementPosition.width,
                height: elementPosition.height
            };
        }
        else {
            around = {
                top: anchor.y,
                left: anchor.x,
                width: anchor.width || 1,
                height: anchor.height || 2
            };
        }
        const viewSizeWidth = DOM.getTotalWidth(this.view);
        const viewSizeHeight = DOM.getTotalHeight(this.view);
        const anchorPosition = this.delegate.anchorPosition || 0 /* BELOW */;
        const anchorAlignment = this.delegate.anchorAlignment || 0 /* LEFT */;
        const verticalAnchor = { offset: around.top - window.pageYOffset, size: around.height, position: anchorPosition === 0 /* BELOW */ ? 0 /* Before */ : 1 /* After */ };
        let horizontalAnchor;
        if (anchorAlignment === 0 /* LEFT */) {
            horizontalAnchor = { offset: around.left, size: 0, position: 0 /* Before */ };
        }
        else {
            horizontalAnchor = { offset: around.left + around.width, size: 0, position: 1 /* After */ };
        }
        const top = layout(window.innerHeight, viewSizeHeight, verticalAnchor) + window.pageYOffset;
        // if view intersects vertically with anchor, shift it horizontally
        if (Range.intersects({ start: top, end: top + viewSizeHeight }, { start: verticalAnchor.offset, end: verticalAnchor.offset + verticalAnchor.size })) {
            horizontalAnchor.size = around.width;
            if (anchorAlignment === 1 /* RIGHT */) {
                horizontalAnchor.offset = around.left;
            }
        }
        const left = layout(window.innerWidth, viewSizeWidth, horizontalAnchor);
        DOM.removeClasses(this.view, 'top', 'bottom', 'left', 'right');
        DOM.addClass(this.view, anchorPosition === 0 /* BELOW */ ? 'bottom' : 'top');
        DOM.addClass(this.view, anchorAlignment === 0 /* LEFT */ ? 'left' : 'right');
        DOM.toggleClass(this.view, 'fixed', this.useFixedPosition);
        const containerPosition = DOM.getDomNodePagePosition(this.container);
        this.view.style.top = `${top - (this.useFixedPosition ? DOM.getDomNodePagePosition(this.view).top : containerPosition.top)}px`;
        this.view.style.left = `${left - (this.useFixedPosition ? DOM.getDomNodePagePosition(this.view).left : containerPosition.left)}px`;
        this.view.style.width = 'initial';
    }
    hide(data) {
        const delegate = this.delegate;
        this.delegate = null;
        if (delegate === null || delegate === void 0 ? void 0 : delegate.onHide) {
            delegate.onHide(data);
        }
        this.toDisposeOnClean.dispose();
        DOM.hide(this.view);
    }
    isVisible() {
        return !!this.delegate;
    }
    onDOMEvent(e, onCapture) {
        if (this.delegate) {
            if (this.delegate.onDOMEvent) {
                this.delegate.onDOMEvent(e, document.activeElement);
            }
            else if (onCapture && !DOM.isAncestor(e.target, this.container)) {
                this.hide();
            }
        }
    }
    dispose() {
        this.hide();
        super.dispose();
    }
}
ContextView.BUBBLE_UP_EVENTS = ['click', 'keydown', 'focus', 'blur'];
ContextView.BUBBLE_DOWN_EVENTS = ['click'];
let SHADOW_ROOT_CSS = /* css */ `
	:host {
		all: initial; /* 1st rule so subsequent properties are reset. */
	}

	@font-face {
		font-family: "codicon";
		src: url("./codicon.ttf?5d4d76ab2ce5108968ad644d591a16a6") format("truetype");
	}

	.codicon[class*='codicon-'] {
		font: normal normal normal 16px/1 codicon;
		display: inline-block;
		text-decoration: none;
		text-rendering: auto;
		text-align: center;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		user-select: none;
		-webkit-user-select: none;
		-ms-user-select: none;
	}

	:host {
		font-family: -apple-system, BlinkMacSystemFont, "Segoe WPC", "Segoe UI", "HelveticaNeue-Light", system-ui, "Ubuntu", "Droid Sans", sans-serif;
	}

	:host-context(.mac) { font-family: -apple-system, BlinkMacSystemFont, sans-serif; }
	:host-context(.mac:lang(zh-Hans)) { font-family: -apple-system, BlinkMacSystemFont, "PingFang SC", "Hiragino Sans GB", sans-serif; }
	:host-context(.mac:lang(zh-Hant)) { font-family: -apple-system, BlinkMacSystemFont, "PingFang TC", sans-serif; }
	:host-context(.mac:lang(ja)) { font-family: -apple-system, BlinkMacSystemFont, "Hiragino Kaku Gothic Pro", sans-serif; }
	:host-context(.mac:lang(ko)) { font-family: -apple-system, BlinkMacSystemFont, "Nanum Gothic", "Apple SD Gothic Neo", "AppleGothic", sans-serif; }

	:host-context(.windows) { font-family: "Segoe WPC", "Segoe UI", sans-serif; }
	:host-context(.windows:lang(zh-Hans)) { font-family: "Segoe WPC", "Segoe UI", "Microsoft YaHei", sans-serif; }
	:host-context(.windows:lang(zh-Hant)) { font-family: "Segoe WPC", "Segoe UI", "Microsoft Jhenghei", sans-serif; }
	:host-context(.windows:lang(ja)) { font-family: "Segoe WPC", "Segoe UI", "Yu Gothic UI", "Meiryo UI", sans-serif; }
	:host-context(.windows:lang(ko)) { font-family: "Segoe WPC", "Segoe UI", "Malgun Gothic", "Dotom", sans-serif; }

	:host-context(.linux) { font-family: system-ui, "Ubuntu", "Droid Sans", sans-serif; }
	:host-context(.linux:lang(zh-Hans)) { font-family: system-ui, "Ubuntu", "Droid Sans", "Source Han Sans SC", "Source Han Sans CN", "Source Han Sans", sans-serif; }
	:host-context(.linux:lang(zh-Hant)) { font-family: system-ui, "Ubuntu", "Droid Sans", "Source Han Sans TC", "Source Han Sans TW", "Source Han Sans", sans-serif; }
	:host-context(.linux:lang(ja)) { font-family: system-ui, "Ubuntu", "Droid Sans", "Source Han Sans J", "Source Han Sans JP", "Source Han Sans", sans-serif; }
	:host-context(.linux:lang(ko)) { font-family: system-ui, "Ubuntu", "Droid Sans", "Source Han Sans K", "Source Han Sans JR", "Source Han Sans", "UnDotum", "FBaekmuk Gulim", sans-serif; }
`;
