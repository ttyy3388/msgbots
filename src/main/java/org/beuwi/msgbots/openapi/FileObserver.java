package org.beuwi.msgbots.openapi;

import javafx.application.Platform;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileObserver extends FileWatcher {
	private final Set<FileListener> listeners = new HashSet<>();

	// private final File file;

	public FileObserver(File file) {
		super(file);
	}

	/* public File getFile() {
		return file;
	} */

    public void addListener(FileListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

    public void removeListener(FileListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

    protected void fireEvent() {
		synchronized (listeners) {
			for (final FileListener listener : listeners) {
				Platform.runLater(() -> {
					listener.changed();
				});
			}
		}
	}

	@Override
	public void changed() {
		fireEvent();
	}
}
