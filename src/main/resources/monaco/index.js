var editorCreatedCallback;
var foldingProvider;
var editorView;
var contentChangeListener;
var scrollChangeListener;

require.config({ paths: { 'vs': './package/min/vs' }});

require(['vs/editor/editor.main'], function()
{
	view = monaco.editor.create(document.getElementById('container'),
	{
		// theme: 'vs-dark',
		value: getCode(),
		// language: 'mylang',
		automaticLayout: true,
		roundedSelection: false,
		mouseWheelScrollSensitivity: 0.1,
		scrollBeyondLastLine: false,
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
		if (contentChangeListener != null)
		{
		   contentChangeListener.apply([ev]);
		}
	});

	editorView.onDidScrollChange((ev) =>
	{
		if (scrollChangeListener != null)
		{
		   scrollChangeListener.apply([ev]);
		}
	});
});

function getCode()
{
	return [''].join('\n');
}

function getEditorView()
{
	return editorView;
}