var editorCreatedCallback;
var contentChangeListener;
var cursorChangeListener;
var scrollChangeListener;

var editor;

require.config({ paths: { 'vs': './package/min/vs' }});

/* require(['vs/editor/editor.main'], function() {
	monaco.languages.registerCompletionItemProvider('javascript', {
		provideCompletionItems: () => {
			return {
				suggestions: [
					{
						label: 'Api',
						kind: monaco.languages.CompletionItemKind.Interface,
						insertText: 'Api',
						values: [
							{
								name: 'on',
								kind: monaco.languages.CompletionItemKind.Function,
								description: '(test, test)',
								insertText: 'on'
							},
							{
								name: 'off',
								kind: monaco.languages.CompletionItemKind.Function,
								description: '(test, test)',
								insertText: 'off'
							}
						]
					},
					{
						label: 'AppData',
						kind: monaco.languages.CompletionItemKind.Interface,
						insertText: 'AppData'
					}
				]
			};
		}
	});
}); */

// Auto Complete Source
var source = [
	'declare class Api {',
	'   /**',
	'     * 해당 스크립트의 전원을 켭니다.',
	'     * 스크립트가 존재하지 않을 시 \'false\', 존재할 시 \'true\'를 반환합니다.',
	'     * ',
	'     * ※ 만약 인자를 입력하지 않은 경우 모든 스크립트의 전원을 키며, 반환값은 항상 \'true\'입니다.',
	'     * ',
	'     */',
	'	static on(scriptName: string): boolean;',
	'   /**',
	'     * 해당 스크립트의 전원을 끕니다.',
	'     * 스크립트가 존재하지 않거나 \'Api.off 무시\'설정을 활성화 했을 경우에는 \'false\', 존재할 경우 \'true\'를 반홥합니다.',
	'     * ',
	'     * ※ 만약 인자를 입력하지 않은 경우 모든 스크립트의 전원을 끄며, 반환값은 항상 \'true\'입니다.',
	'     * ',
	'     */',
	'	static off(scriptName: string): boolean;',
	'   /**',
	'     * 해당 스크립트를 재컴파일 합니다.',
	'     * \'throwOnError\'가 \'true\'라면, 컴파일 에러 시 예외를 \'throw\'합니다.',
	'     * 에러가 발생하거나 스크립트가 존재하지 않을 시 \'false\', 컴파일 성공 시 \'true\'를 반환합니다.',
	'     * ',
	'     * ※ 만약 인자를 입력하지 않은 경우 모든 스크립트를 재컴파일 하며, 반환값은 항상 \'true\'입니다.',
	'     * ',
	'     */',
	'	static reload(scriptName: string, stopOnError: boolean): boolean;',
	'   /**',
	'     * 해당 스크립트를 재컴파일 합니다.',
	'     * \'throwOnError\'가 \'true\'라면, 컴파일 에러 시 예외를 \'throw\'합니다.',
	'     * 에러가 발생하거나 스크립트가 존재하지 않을 시 \'false\', 컴파일 성공 시 \'true\'를 반환합니다.\'',
	'     * ',
	'     * ※ 만약 인자를 입력하지 않은 경우 모든 스크립트를 재컴파일 하며, 반환값은 항상 \'true\'입니다.',
	'     * ',
	'     */',
	'	static compile(scriptName: string, stopOnError: boolean): boolean;',
	'   /**',
	'     * 해당 스크립트가 컴파일 된 적이 없을 경우에만 컴파일합니다.',
	'     * 컴파일 실패 시 에러를 \'throw\'합니다.',
	'     * ',
	'     * ※ 스크립트가 존재하지 않을 시 0, 컴파일 성공 시 1, 한번이라도 컴파일이 된 적이 있을 시 2를 반환합니다.',
	'     * ',
	'     */',
	'	static prepare(scriptName: string): number;',
	'	static unload(scriptName: string): boolean;',
	'	static isOn(scriptName: string): boolean;',
	'	static isCompiled(scriptName: string): boolean;',
	'	static isCompiling(scriptName: string): boolean;',
	'	static getScriptNames(): any;',
	'	static replyRoom(scriptName: string): boolean;',
	'	static canReply(scriptName: string): boolean;',
	'	static showToast(scriptName: string): boolean;',
	'	static papagoTranslate(scriptName: string): boolean;',
	'	static makeNoti(scriptName: string): boolean;',
	'	static gc(): void;',
	'}',
	'declare class AppData {',
	'}',
	'declare class Test {',
	'   static on(name: string): boolean;',
	'	static off(name: string): boolean;',
	'}',
].join('\n');

require(['vs/editor/editor.main'], function() {
	monaco.languages.typescript.javascriptDefaults.addExtraLib(source);
});

require(['vs/editor/editor.main'], function() {
	editor = monaco.editor.create(document.getElementById('container'), {
		theme: 'vs-dark',
		value: getCode(),
		language: 'javascript',
		fontFamily: 'D2Coding', // 한글 입력 시 물음표가 뜨는 버그가 있음
		fontSize: '14px',
        // fontFamily: 'Consolas, \'D2Coding\', monospace';,
		automaticLayout: true,
		roundedSelection: false,
		mouseWheelScrollSensitivity: 0.1,
		scrollBeyondLastLine: false,
		contextmenu: true,
		scrollbar: {
			// Subtle shadows to the left & top. Defaults to true.
			useShadows: true,
			// Render vertical arrows. Defaults to false.
			verticalHasArrows: false,
			// Render horizontal arrows. Defaults to false.
			horizontalHasArrows: false,
			// Render vertical scrollbar.
			// Accepted values: 'auto', 'visible', 'hidden'.
			// Defaults to 'auto'
			vertical: 'auto',
			// Render horizontal scrollbar.
			// Accepted values: 'auto', 'visible', 'hidden'.
			// Defaults to 'auto'
			horizontal: 'auto',
			verticalScrollbarSize: 15,
			horizontalScrollbarSize: 15,
			// arrowSize: 15,
			alwaysConsumeMouseWheel: false
		}
	});

	// Regist Actions
	editor.addAction({
		id: 'undo',
		label: 'Undo',
		run: () => {
			editor.focus()
			if (!document.execCommand('undo')) {
				editor.getModel().undo()
			}
		},
	});
	editor.addAction({
		id: 'redo',
		label: 'Redo',
		run: () => {
			editor.focus()
			if (!document.execCommand('redo')) {
				editor.getModel().redo()
			}
		},
	});
	editor.addAction({
		id: 'copy',
		// id: 'editor.action.clipboardCopyAction',
		label: 'Copy',
		keybindings: [
			monaco.KeyMod.chord(monaco.KeyMod.CtrlCmd | monaco.KeyCode.C),
		],
		run: () => {
			editor.focus()
			document.execCommand('copy')
		},
	});
	editor.addAction({
		id: 'cut',
		// id: 'editor.action.clipboardCutAction',
		label: 'Cut',
		run: () => {
			editor.focus()
			document.execCommand('cut')
		},
	});
	editor.addAction({
		id: 'paste',
		// id: 'editor.action.clipboardPasteAction',
		label: 'Paste',
		run: () => {
			editor.focus()
			document.execCommand('paste')
		},
	});

	if (editorCreatedCallback != null) {
		editorCreatedCallback.apply([editor]);
	}

	/* editor.onContextMenu((ev) => {
		alert("showing")
	}); */

	editor.onDidChangeCursorPosition((ev) => {
		cursorChangeListener.apply([ev]);
	});

	editor.onDidChangeModelContent((ev) => {
		contentChangeListener.apply([ev]);
	});

	editor.onDidScrollChange((ev) => {
		scrollChangeListener.apply([ev]);
	});

	/* editor.onDidChangeCursorPosition((e) => {
		console.log(JSON.stringify(e));
	});

	editor.onDidChangeCursorSelection((e) => {
		console.log(JSON.stringify(e));
	}); */
});

function changeText(data) {
	var range =  editor.getModel().getFullModelRange();
	var id = { major: 1, minor: 1 };
	var text = data;
	var op = { identifier: id, range: range, text: text, forceMoveMarkers: true };
	editor.executeEdits("my-source", [op]);
}

function getCode() {
	return [''].join('\n');
}

function getEditor() {
	return editor;
}