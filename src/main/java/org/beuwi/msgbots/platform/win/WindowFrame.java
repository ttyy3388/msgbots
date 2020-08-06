package org.beuwi.msgbots.platform.win;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.Component;
import java.lang.reflect.Method;

public class WindowFrame extends StackPane
{
	final WindowProcedure procedure;

	public WindowFrame()
    {
        procedure = new WindowProcedure();
	}

	private WinDef.HWND getHwnd()
    {
		WinDef.HWND hwnd = new WinDef.HWND();
		hwnd.setPointer(getWindowPointer(null));
		return hwnd;
	}

	private Pointer getWindowPointer(Stage stage)
	{
		try
		{
			Method getPeer = stage.getClass().getMethod("impl_getPeer");
			final Object tkStage = getPeer.invoke(stage);
			Method getPlatformWindow = tkStage.getClass().getDeclaredMethod("getPlatformWindow");
			getPlatformWindow.setAccessible(true);
			final Object platformWindow = getPlatformWindow.invoke(tkStage);
			Method getNativeHandle = platformWindow.getClass().getMethod("getNativeHandle");

			return new Pointer((Long) getNativeHandle.invoke(platformWindow));
		}
		catch (Throwable t)
		{
			return null;
		}
	}

	private void initializeFrame()
    {
		WindowParams.setControlBoxWidth(controlBox.getWidth() + 10);
	}

	public void addUserControlsToTitleBar(Component component)
    {
        WindowParams.setExtraLeftReservedWidth(titleBarCustomContent.getWidth() + 10);
	}

	public void setIcon(Image image)
    {
		WindowParams.setExtraLeftReservedWidth(titleBarCustomContent.getWidth() + 10);
	}
}
