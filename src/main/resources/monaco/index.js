var editorCreatedCallback;
var contentChangeListener;
var cursorChangeListener;
var scrollChangeListener;

var editor;

require.config({ paths: { 'vs': './package/min/vs' }});

/* monaco.languages.registerCompletionItemProvider('rhino-js', {
	provideCompletionItems: function(model, position) {
		return [
			{
				label: 'Api',
				kind: monaco.languages.CompletionItemKind.Interface,
				documentation: "My first Custom Interface",
				insertText: 'Api'
			},
			{
				label: 'AppData',
				kind: monaco.languages.CompletionItemKind.Interface,
				documentation: "My second Custom Interface",
				insertText: 'AppData'
			}
		];
	}
}); */

require(['vs/editor/editor.main'], function() {
	editor = monaco.editor.create(document.getElementById('container'), {
		theme: 'vs-dark',
		value: getCode(),
		language: 'rhino-js',
		// language: 'javascript',
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