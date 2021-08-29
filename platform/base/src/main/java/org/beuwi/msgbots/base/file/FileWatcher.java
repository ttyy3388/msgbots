package org.beuwi.msgbots.base.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

public class FileWatcher extends Thread {
	// Watcher를 중복으로 등록하는것을 방지하기 위해 리스트를 추가
	// private final List<String> registered = new ArrayList<>();

	private WatchService WATCH_SERVICE = null;
	private WatchKey WATCH_KEY = null;

	private Set<FileObserver> observers = new HashSet<>();

	private final File target;

	public FileWatcher(File file) {
		// 디렉토리가 아닐 경우 상위 폴더를 대상으로함
		if (!file.isDirectory()) {
			// 상위 파일이 폴더가 아닐 경우가 있는지는 모르겟음
			target = file.getParentFile();
		}
		else {
			target = file;
		}

		try {
			WATCH_SERVICE = FileSystems.getDefault().newWatchService();

			Paths.get(target.getPath()).register(
				WATCH_SERVICE,
				ENTRY_CREATE,
				ENTRY_DELETE,
				ENTRY_MODIFY,
				OVERFLOW
			);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		new Thread(() -> {
			while (true) {
				try {
					// 자원 사용량 및 속도 개선을 위해 1초 딜레이를 추가함
					Thread.sleep(1000);
				}
				catch (InterruptedException e) { }

				try {
					WATCH_KEY = WATCH_SERVICE.take();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}

				List<WatchEvent<?>> events = WATCH_KEY.pollEvents();

				for (WatchEvent<?> event : events) {
					final Kind kind = event.kind();

					if (kind.equals(ENTRY_CREATE)) created();
					if (kind.equals(ENTRY_MODIFY)) modified();
					if (kind.equals(ENTRY_DELETE)) deleted();

					changed();

					if (!WATCH_KEY.reset()) {
						try {
							WATCH_SERVICE.close();
						}
						catch (Exception e) {
							e.printStackTrace();
						}

						break;
					}
				}
			}
		}).start();
	}

	public void changed() {
		// Do whatever action you want here
	}
	public void created() {
		// Do whatever action you want here
	}
	public void deleted() {
		// Do whatever action you want here
	}
	public void modified() {
		// Do whatever action you want here
	}
}
