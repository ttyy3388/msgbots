/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/
import * as dom from './dom.js';
import { renderCodiconsRegex } from '../common/codicons.js';
export function renderCodiconsAsElement(text) {
    const elements = new Array();
    let match;
    let textStart = 0, textStop = 0;
    while ((match = renderCodiconsRegex.exec(text)) !== null) {
        textStop = match.index || 0;
        elements.push(text.substring(textStart, textStop));
        textStart = (match.index || 0) + match[0].length;
        const [, escaped, codicon, name, animation] = match;
        elements.push(escaped ? `$(${codicon})` : dom.$(`span.codicon.codicon-${name}${animation ? `.codicon-animation-${animation}` : ''}`));
    }
    if (textStart < text.length) {
        elements.push(text.substring(textStart));
    }
    return elements;
}
