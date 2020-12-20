/*
 * MIT License
 *
 * Copyright (c) 2020 Michael Hoffer <info@michaelhoffer.de>. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.beuwi.msgbots.platform.gui.editor;

import javafx.beans.property.*;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Document
{
	private final StringProperty text = new SimpleStringProperty();
	private final StringProperty language = new SimpleStringProperty();
	private final IntegerProperty line = new SimpleIntegerProperty();

	private WebEngine engine;

	private JSObject editor;
	private JSObject window;

	void setEditor(WebEngine engine, JSObject window, JSObject editor) {
		this.engine = engine;
		this.editor = editor;
		this.window = window;

		// Initialize text
		editor.call("setValue", getText());

		getTextProperty().addListener(change ->
		{
			editor.call("setValue", getText());
		});

		// Text changes <- js
		window.setMember("contentChangeListener", new JFunction(args ->
        {
			String text = (String) editor.call("getValue");

			if (text != null)
			{
				setText(text);
			}

			return null;
		}));
	}

	public void setText(String text)
	{
		this.text.set(text);
	}

	public void setLanguage(String language)
	{
		this.language.set(language);
	}

	public String getText()
	{
		return text.get();
	}

	public String getLanguage()
	{
		return language.get();
	}

	public StringProperty getTextProperty()
	{
		return text;
	}

	public StringProperty getLanguageProperty()
	{
		return language;
	}
}
