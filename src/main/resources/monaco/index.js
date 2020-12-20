var editorCreatedCallback;
var foldingProvider;
var editorView;
var contentChangeListener;
var scrollChangeListener;

require.config({ paths: { 'vs': './package/min/vs' }});

require(['vs/editor/editor.main'], function()
{
	editorView = monaco.editor.create(document.getElementById('container'),
	{
		theme: 'vs-dark',
		value: getCode(),
		language: 'javascript',
		automaticLayout: true,
		roundedSelection: false,
		mouseWheelScrollSensitivity: 0.1,
		scrollBeyondLastLine: false,
		contextmenu: true,
		scrollbar: {
            // Subtle shadows to the left & top. Defaults to true.
            useShadows: true,
            // Render vertical arrows. Defaults to false.
            verticalHasArrows: true,
            // Render horizontal arrows. Defaults to false.
            horizontalHasArrows: true,
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
            arrowSize: 15,
            alwaysConsumeMouseWheel: false
        }
    });

    if (editorCreatedCallback != null)
    {
        editorCreatedCallback.apply([editorView]);
    }

    editorView.onDidChangeModelContent((ev) =>
    {
        contentChangeListener.apply([ev]);
    });

    editorView.onDidScrollChange((ev) =>
    {
        scrollChangeListener.apply([ev]);
    });

    /* editor.onDidChangeCursorPosition((e) => {
        console.log(JSON.stringify(e));
    });

    editor.onDidChangeCursorSelection((e) => {
        console.log(JSON.stringify(e));
    }); */
});

function getCode()
{
	return [''].join('\n');
}

function getEditorView()
{
	return editorView;
}