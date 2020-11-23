package org.beuwi.msgbots.openapi;

import org.beuwi.msgbots.platform.app.view.actions.AddToastMessageAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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

public class FileWatcher extends Thread
{
	private WatchService WATCH_SERVICE;
	private WatchKey WATCH_KEY = null;

	private Set<FileObserver> observers = new HashSet<>();

	private final File file;

	public FileWatcher(File file)
	{
		this.file = file;

		try
		{
			WATCH_SERVICE = FileSystems.getDefault().newWatchService();

			file.toPath().getParent().register
			(
				WATCH_SERVICE,
				ENTRY_CREATE,
				ENTRY_DELETE,
				ENTRY_MODIFY
			);

			new Thread(() ->
			{
				while (true)
				{
					try
					{
						WATCH_KEY = WATCH_SERVICE.take();
					}
					catch (InterruptedException e)
					{
						break;
					}

					List<WatchEvent<?>> events = WATCH_KEY.pollEvents();

					for (WatchEvent<?> event : events)
					{
						synchronized (observers)
						{
							// Changed File Path
							final Kind kind = event.kind();
							final Path path = (Path) event.context();

							if (file.getName().equals(path.toString()))
							{
								changed();

								if (kind.equals(ENTRY_CREATE))
								{
									created();
								}
								if (kind.equals(ENTRY_MODIFY))
								{
									modified();
								}
								if (kind.equals(ENTRY_DELETE))
								{
									modified();
								}
							}
						}

						if (!WATCH_KEY.reset())
						{
							try
							{
								WATCH_SERVICE.close();
							}
							catch (Exception e)
							{
								AddToastMessageAction.execute(e);
							}

							break;
						}
					}
				}
			}).start();
		}
		catch (IOException e)
		{
			AddToastMessageAction.execute(e);
		}
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
