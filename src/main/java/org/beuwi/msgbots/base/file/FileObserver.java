package org.beuwi.msgbots.base.file;

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

    /* protected void fireEvent() {

	} */

	@Override
	public void changed() {
		listeners.forEach(listener -> {
			Platform.runLater(listener::changed);
		});
	}
	/* @Override
	public void deleted() {
		listeners.forEach(listener -> {
			Platform.runLater(listener::deleted);
		});
	}
	@Override
	public void created() {
    	listeners.forEach(listener -> {
			Platform.runLater(listener::created);
		});
	}
	@Override
	public void modified() {
		listeners.forEach(listener -> {
			Platform.runLater(listener::modified);
		});
	} */
}
