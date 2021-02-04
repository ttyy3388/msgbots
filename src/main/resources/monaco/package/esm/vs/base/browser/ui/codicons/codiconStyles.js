/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/
import './codicon/codicon.css';
import './codicon/codicon-modifications.css';
import './codicon/codicon-animations.css';
import { Codicon, iconRegistry } from '../../../common/codicons.js';
export const CodiconStyles = new class {
    constructor() {
        this.onDidChange = iconRegistry.onDidRegister;
    }
    getCSS() {
        const rules = [];
        for (let c of iconRegistry.all) {
            rules.push(formatRule(c));
        }
        return rules.join('\n');
    }
};
export function formatRule(c) {
    let def = c.definition;
    while (def instanceof Codicon) {
        def = def.definition;
    }
    return `.codicon-${c.id}:before { content: '${def.character}'; }`;
}
