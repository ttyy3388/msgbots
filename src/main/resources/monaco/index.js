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
	'declare class replier {',
	'   /**',
	'     * 디버그 채팅방에 \'msg\'라는 내용을 전송합니다.',
	'     */',
	'   static reply(string: msg): void;',
	'}',
	'declare class imageDB {',
	'   /**',
	'     * 유저의 프로필 이미지를 \'Base64\'의 형태로 반환합니다.',
	'     */',
	'   static getProfileBase64(): string;',
	'   /**',
	'     * 유저의 프로필 이미지를 \'Base64\'의 형태로 반환합니다.',
	'     */',
	'   static getProfileImage(): string;',
	'}',
	'declare class Api {',
	'   /**',
	'     * 앱의 컨텍스트를 가져옵니다.',
	'     * ',
	'     * (!) 이 함수는 호환성을 위해 구현했으므로 고정값인 \'null\'를 반환합니다.',
	'     */',
	'	static getContext(): any;',
	'   /**',
	'     * 해당 스크립트의 전원을 켭니다.',
	'     * 스크립트가 존재하지 않을 시 \'false\', 존재할 시 \'true\'를 반환합니다.',
	'     * ',
	'     * 만약 인자를 입력하지 않은 경우 모든 스크립트의 전원을 키며, 반환값은 항상 \'true\'입니다.',
	'     * ',
	'     */',
	'	static on(scriptName: string): boolean;',
	'   /**',
	'     * 해당 스크립트의 전원을 끕니다.',
	'     * 스크립트가 존재하지 않거나 \'Api.off 무시\'설정을 활성화 했을 경우에는 \'false\', 존재할 경우 \'true\'를 반홥합니다.',
	'     * ',
	'     * 만약 인자를 입력하지 않은 경우 모든 스크립트의 전원을 끄며, 반환값은 항상 \'true\'입니다.',
	'     * ',
	'     */',
	'	static off(scriptName: string): boolean;',
	'   /**',
	'     * 해당 스크립트를 재컴파일 합니다.',
	'     * \'throwOnError\'가 \'true\'라면, 컴파일 에러 시 예외를 \'throw\'합니다.',
	'     * 에러가 발생하거나 스크립트가 존재하지 않을 시 \'false\', 컴파일 성공 시 \'true\'를 반환합니다.',
	'     * ',
	'     * 만약 인자를 입력하지 않은 경우 모든 스크립트를 재컴파일 하며, 반환값은 항상 \'true\'입니다.',
	'     * ',
	'     */',
	'	static reload(scriptName: string, stopOnError: boolean): boolean;',
	'   /**',
	'     * 해당 스크립트를 재컴파일 합니다.',
	'     * \'throwOnError\'가 \'true\'라면, 컴파일 에러 시 예외를 \'throw\'합니다.',
	'     * 에러가 발생하거나 스크립트가 존재하지 않을 시 \'false\', 컴파일 성공 시 \'true\'를 반환합니다.\'',
	'     * ',
	'     * 만약 인자를 입력하지 않은 경우 모든 스크립트를 재컴파일 하며, 반환값은 항상 \'true\'입니다.',
	'     * ',
	'     */',
	'	static compile(scriptName: string, stopOnError: boolean): boolean;',
	'   /**',
	'     * 해당 스크립트가 컴파일 된 적이 없을 경우에만 컴파일합니다.',
	'     * 컴파일 실패 시 에러를 \'throw\'합니다.',
	'     * ',
	'     * 스크립트가 존재하지 않을 시 0, 컴파일 성공 시 1, 한번이라도 컴파일이 된 적이 있을 시 2를 반환합니다.',
	'     * ',
	'     */',
	'	static prepare(scriptName: string): number;',
	'   /**',
	'     * 해당 스크립트를 컴파일되지 않은 상태로 되돌립니다.',
	'     * ',
	'     * 원래부터 컴파일 되지 않은 상태였거나, 스크립트가 존재하지 않을 시 \'false\'를',
	'     * 그렇지 않은 경우에는 \'true\'를 반환합니다.',
	'     */',
	'	static unload(scriptName: string): boolean;',
	'   /**',
	'     * 해당 스크립트의 전원 상태를 반환합니다.',
	'     */',
	'	static isOn(scriptName: string): boolean;',
	'   /**',
	'     * 해당 스크립트의 컴파일 여부를 반환합니다.',
	'     */',
	'	static isCompiled(scriptName: string): boolean;',
	'   /**',
	'     * 해당 스크립트가 컴파일 진행중인지 여부를 반환합니다.',
	'     */',
	'	static isCompiling(scriptName: string): boolean;',
	'   /**',
	'     * 모든 스크립트의 이름을 배열로 반환합니다.',
	'     */',
	'	static getScriptNames(): any;',
	'   /**',
	'     * 해당 방에 메세지를 보냅니다. \'hideToast\'가 \'true\'일 경우 방 세션이 없어도 토스트를 띄우지 않습니다.',
	'     * ',
	'     * 방 세션이 있을 시 \'true\'를 없을 시 \'false\'를 반환합니다. (이는 일반적인 상황에서 전송 성공 여부를 나타냅니다.)',
	'     * ',
	'     * (!) 이 함수는 호환성을 위해 구현했으므로 고정값인 \'true\'를 반환합니다.',
	'     */',
	'	static replyRoom(scriptName: string): boolean;',
	'   /**',
	'     * 해당 채팅방으로의 메세지 전송 가능 여부를 반환합니다.',
	'     * ',
	'     * (!) 이 함수는 호환성을 위해 구현했으므로 고정값인 \'true\'를 반환합니다.',
	'     */',
	'	static canReply(scriptName: string): boolean;',
	'   /**',
	'     * 좌측 상단에 토스트 메시를 띄웁니다.',
	'     */',
	'	static showToast(content: string, length:  number);',
	'   /**',
	'     * 네이버 파파고의 번역 결과를 제공합니다.',
	'     * ',
	'     * (!) 이 함수는 호환성을 위해 구현했으므로 작동하지 않으며 고정값인 \'null\'을 반환합니다.',
	'     */',
	'	static papagoTranslate(scriptName: string): stirng;',
	'   /**',
	'     * 윈도우 알림을 띄웁니다.',
	'     */',
	'	static makeNoti(title: string, content: string, id: int): boolean;',
	'   /**',
	'     * 가비지 컬렉팅을 강제로 시작합니다.',
	'     */',
	'	static gc(): void;',
	'}',
	'declare class Utils {',
	'   /**',
	'     * 웹사이트의 HTML을 로드하여 문자열로 반환합니다.',
	'     */',
	'	static getWebText(url: string): string;',
	'   /**',
	'     * 웹사이트의 HTML을 가져와 \'org.jsoup.nodes.Document\'로 반환합니다.',
	'     */',
	'	static parse(url: string): any;',
	'   /**',
	'     * 앱 구동 환경의 안드로이드 버전 코드를 반환합니다.',
	'     * ',
	'     * (!) 이 함수는 호환성을 위해 구현했으므로 고정값인 \'28\'을 반환합니다.',
	'     */',
	'	static getAndroidVersionCode(): int;',
	'   /**',
	'     * 앱 구동 환경의 안드로이드 버전 코드를 반환합니다.',
	'     * ',
	'     * (!) 이 함수는 호환성을 위해 구현했으므로 고정값인 \'28\'을 반환합니다.',
	'     */',
	'	static getAndroidVersionCode(): int;',
	'   /**',
	'     * 앱 구동 환경의 안드로이드 버전 이름를 반환합니다.',
	'     * ',
	'     * (!) 이 함수는 호환성을 위해 구현했으므로 고정값인 \'9\'를 반환합니다.',
	'     */',
	'	static getAndroidVersionName(): string;',
	'   /**',
	'     * 앱 구동 환경의 휴대폰 브랜드명을 반환합니다.',
	'     * ',
	'     * (!) 이 함수는 호환성을 위해 구현했으므로 고정값인 \'samsung\'을 반환합니다.',
	'     */',
	'	static getPhoneBrand(): string;',
	'   /**',
	'     * 앱 구동 환경의 휴대폰 모델명을 반환합니다.',
	'     * ',
	'     * (!) 이 함수는 호환성을 위해 구현했으므로 고정값인 \'greatlteks\'을 반환합니다.',
	'     */',
	'	static getPhoneModel(): string;',
	'}',
	'declare class FileStream {',
	'   /**',
	'     * \'path\'경로에 있는 파일 내용을 읽어 반환합니다.',
	'     * ',
	'     * (!) 파일 저장 및 읽기 관련 경로는 프로그램 폴더 내 봇 폴더를 기본 폴더로 합니다.',
	'     */',
	'	static read(path: string): string;',
	'   /**',
	'     * \'path\'경로에 있는 파일에 \'data\'를 덮어씁니다.',
	'     * 반환값은 최종 파일 내용입니다.',
	'     * ',
	'     * (!) 파일 저장 및 읽기 관련 경로는 프로그램 폴더 내 봇 폴더를 기본 폴더로 합니다.',
	'     */',
	'	static write(path: string): string;',
	'   /**',
	'     * \'path\'경로에 있는 파일에 \'data\'를 이어씁니다.',
	'     * 반환값은 최종 파일 내용입니다.',
	'     * ',
	'     * (!) 파일 저장 및 읽기 관련 경로는 프로그램 폴더 내 봇 폴더를 기본 폴더로 합니다.',
	'     */',
	'	static append(path: string): string;',
	'   /**',
	'     * \'path\'경로에 있는 파일을 삭제합니다.',
	'     * 반환값은 \'java.io.File.delete()\' 결과값 또는 삭제 중 예외가 발생할 경우 \'false\'를 반환합니다.',
	'     * ',
	'     * (!) 파일 저장 및 읽기 관련 경로는 프로그램 폴더 내 봇 폴더를 기본 폴더로 합니다.',
	'     */',
	'	static remove(path: string): string;',
	'}',
	'declare class Log {',
	'   /**',
	'     * 디버그 로그를 씁니다.',
	'     */',
	'	static debug(data: string, showToast: boolean): void;',
	'   /**',
	'     * 에러 로그를 씁니다.',
	'     */',
	'	static error(path: string, showToast: boolean): void;',
	'   /**',
	'     * 정보 로그를 씁니다.',
	'     */',
	'	static info(path: string, showToast: boolean): void;',
	'   /**',
	'     * 디버그 로그를 씁니다.',
	'     */',
	'	static d(data: string, showToast: boolean): void;',
	'   /**',
	'     * 에러 로그를 씁니다.',
	'     */',
	'	static e(path: string, showToast: boolean): void;',
	'   /**',
	'     * 정보 로그를 씁니다.',
	'     */',
	'	static i(path: string, showToast: boolean): void;',
	'   /**',
	'     * 로그를 모두 지웁니다.',
	'     */',
	'	static remove(): void;',
	'}',
	'declare class Bridge {',
	'   /**',
	'     * 해당 스크립트의 전역 스코프를 반환합니다.',
	'     * 만약 대상 스크립트가 컴파일이나 인터프리트 되지 않았거나,',
	'     * 설정에서 \'Bridge\'를 통한 접근을 허용하지 않은 경우 \'null\'을 반환합니다.',
	'     */',
	'   static getScopeOf(scriptName: string): any;',
	'   /**',
	'     * 해당 스크립트의 \'Bridge\'를 통한 접근 허용 여부를 반환합니다.',
	'     * ',
	'     * (!) 이 함수는 호환성을 위해 구현했으므로 고정값인 \'true\'를 반환합니다.',
	'     */',
	'	static isAllowed(scriptName: string): boolean;',
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
		mouseWheelScrollSensitivity: 0.02,
		scrollBeyondLastLine: false,
		contextmenu: true,
        minimap: {
            enabled: true
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

// setValue()를 하면 초기화처럼 redo, undo 스택과 같은 메모리가 사라지기 때문에 다른 방법 사용
// 그래서 changeText로 현제 모델에서 값만 바꿔주는 식으로 함
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