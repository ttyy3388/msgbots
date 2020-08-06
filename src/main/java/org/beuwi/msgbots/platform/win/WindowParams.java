package org.beuwi.msgbots.platform.win;

import com.sun.jna.Pointer;
import javafx.stage.Stage;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class WindowParams
{
	private static AtomicInteger titleBarHeight = new AtomicInteger(27);
	private static AtomicInteger controlBoxWidth = new AtomicInteger(150);
	private static AtomicInteger iconWidth = new AtomicInteger(40);
	private static AtomicInteger extraLeftReservedWidth = new AtomicInteger(0);
	private static AtomicInteger extraRightReservedWidth = new AtomicInteger(0);
	private static AtomicInteger maximizedWindowFrameThickness = new AtomicInteger(10);
	private static AtomicInteger frameResizeBorderThickness = new AtomicInteger(4);
	private static AtomicInteger frameBorderThickness = new AtomicInteger(1);

	public static int getControlBoxWidth()
	{
		return controlBoxWidth.get();
	}

	public static void setControlBoxWidth(int value)
	{
		controlBoxWidth.set(value);
	}

	public static int getIconWidth()
	{
		return iconWidth.get();
	}

	public static void setIconWidth(int value)
	{
		iconWidth.set(value);
	}

	public static int getExtraLeftReservedWidth()
	{
		return extraLeftReservedWidth.get();
	}

	public static void setExtraLeftReservedWidth(int value)
	{
		extraLeftReservedWidth.set(value);
	}

	public static int getExtraRightReservedWidth()
	{
		return extraRightReservedWidth.get();
	}

	public static void setExtraRightReservedWidth(int value)
	{
		extraRightReservedWidth.set(value);
	}

	public static int getMaximizedWindowFrameThickness()
	{
		return maximizedWindowFrameThickness.get();
	}

	public static void setMaximizedWindowFrameThickness(int maximizedWindowFrameThickness)
	{
		WindowParams.maximizedWindowFrameThickness.set(maximizedWindowFrameThickness);
	}

	public static int getTitleBarHeight()
	{
		return titleBarHeight.get();
	}

	public static void setTitleBarHeight(int value)
	{
		WindowParams.titleBarHeight.set(value);
	}

	public static int getFrameResizeBorderThickness()
	{
		return frameResizeBorderThickness.get();
	}

	public static void setFrameResizeBorderThickness(int value)
	{
		WindowParams.frameResizeBorderThickness.set(value);
	}

	public static int getFrameBorderThickness()
	{
		return frameBorderThickness.get();
	}

	public static void setFrameBorderThickness(int value)
	{
		WindowParams.frameBorderThickness.set(value);
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
}