// 주의: let가 아닌 var로 해야 적용됨
var editorCreatedCallback;
var contentChangeListener;
var cursorChangeListener;
var scrollChangeListener;

// var autoCompleteContent;

require.config({ paths: { 'vs': './package/min/vs' }});

require(['vs/editor/editor.main'], function () {
	monaco.languages.typescript.javascriptDefaults.addExtraLib(readfile("index.d.ts"));
});

function readfile(path) {
	var file = new XMLHttpRequest();
	var text = null;
	file.open("GET", path, false);
	file.onreadystatechange = function () {
		if(file.readyState === 4) {
			if(file.status === 200 || file.status == 0) {
				text = file.responseText;
			}
		}
	}
	file.send(null);
	return text;
}

// 참고1: "https://microsoft.github.io/monaco-editor/playground.html#extending-language-services-semantic-tokens-provider-example"
// 참고2: "https://github.com/microsoft/monaco-editor/issues/1070"
// 컬러맵: "https://github.com/samdark/intellij-visual-studio-code-dark-plus/blob/master/resources/visual_studio_code_dark_plus.theme.json"
// 참고3: "https://github.com/microsoft/monaco-languages/blob/main/src/javascript/javascript.ts"
// 중요(※): "https://github.com/microsoft/vscode/blob/b6f87798570cef6d99554fe10c747e9f6b02a274/src/vs/editor/standalone/common/themes.ts"
require(['vs/editor/editor.main'], function () {
	monaco.editor.defineTheme('dark', {
		base: 'vs-dark',
		inherit: true,
		/* Token Types :
		'comment', 'string', 'keyword', 'number', 'regexp', 'operator', 'namespace',
		'type', 'struct', 'class', 'interface', 'enum', 'typeParameter', 'function',
		'member', 'macro', 'variable', 'parameter', 'property', 'label' */
		rules: [
			// { token: 'delimiter', foreground: 'D4D4D4' },
			// js
			// { token: '', foreground: '9CDCFE' },
			/* { token: 'type', foreground: 'dcdcaa' },
			// log
			{ token: 'info', foreground: '007ACC' },
			{ token: 'error', foreground: 'FF0000' },
			{ token: 'notice', foreground: 'FFA500' },
			{ token: 'date', foreground: '008800' }, */
		],
	});
})

require(['vs/editor/editor.main'], function() {
	monaco.languages.register({ id: 'log' });
	monaco.languages.setMonarchTokensProvider('log', {
		tokenizer: {
			root: [
				[/\[error.*/, "error"],
				[/\[notice.*/, "notice"],
				[/\[info.*/, "info"],
				[/\[[a-zA-Z 0-9:]+\]/, "date"],
			]
		}
	});
	/* monaco.editor.defineTheme('dark', {
		base: 'dark',
		inherit: false,
		rules: [
			{ token: 'info', foreground: '007ACC' },
			{ token: 'error', foreground: 'FF0000' },
			{ token: 'notice', foreground: 'FFA500' },
			{ token: 'date', foreground: '008800' },
		]
	}); */
});

var editor;

require(['vs/editor/editor.main'], function() {
	editor = monaco.editor.create(document.getElementById('container'), {
		theme: 'dark',
		value: getCode(),
		language: 'javascript',
		// fontFamily: 'Consolas', // 한글 입력 시 물음표가 뜨는 버그가 있음
		fontSize: '14px',
		// lineHeight: '25px',
		automaticLayout: true,
		roundedSelection: false,
		mouseWheelScrollSensitivity: 0.02,
		scrollBeyondLastLine: false,
		contextmenu: true,
		minimap: {
			enabled: false,
		},
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

	if (editorCreatedCallback != null) {
		editorCreatedCallback.apply([editor]);
	}

	editor.onDidChangeCursorPosition((event) => {
		if (cursorChangeListener != null) {
			cursorChangeListener.apply([event]);
		}
	});

	editor.onDidChangeModelContent((event) => {
		if (contentChangeListener != null) {
			contentChangeListener.apply([event]);
		}
	});

	editor.onDidScrollChange((event) => {
		if (scrollChangeListener != null) {
			scrollChangeListener.apply([event]);
		}
	});

	/* editor.onDidChangeCursorPosition((event) => {
		console.log(JSON.stringify(event));
	});

	editor.onDidChangeCursorSelection((event) => {
		console.log(JSON.stringify(event));
	}); */
});

// setValue()를 하면 초기화처럼 redo, undo 스택과 같은 메모리가 사라지기 때문에 다른 방법 사용
// 그래서 setText로 현제 모델에서 값만 바꿔주는 식으로 함
function setText(data) {
	var range =  editor.getModel().getFullModelRange();
	var id = { major: 1, minor: 1 };
	var text = data;
	var option = { identifier: id, range: range, text: text, forceMoveMarkers: true };

	editor.executeEdits("", [option]);
}

function getCode() {
	return [''].join('\n');
}

function getEditor() {
	return editor;
}