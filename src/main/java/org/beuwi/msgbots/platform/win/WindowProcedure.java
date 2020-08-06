package org.beuwi.msgbots.platform.win;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.win32.W32APIOptions;

import static com.sun.jna.platform.win32.WinUser.SWP_FRAMECHANGED;
import static com.sun.jna.platform.win32.WinUser.SWP_NOMOVE;
import static com.sun.jna.platform.win32.WinUser.SWP_NOSIZE;
import static com.sun.jna.platform.win32.WinUser.SWP_NOZORDER;
import static com.sun.jna.platform.win32.WinUser.WM_DESTROY;
import static com.sun.jna.platform.win32.WinUser.WindowProc;

// Window Procedure
public class WindowProcedure implements WindowProc
{
	final int WM_NCCALCSIZE = 0x0083;
	final int WM_NCHITTEST = 0x0084;
	final int TITLE_BAR_HEIGHT = 27;

	final User32Ex INSTANCEEx;
	WinDef.HWND hwnd = new WinDef.HWND();
	BaseTSD.LONG_PTR defWndProc;

    interface User32Ex extends User32
    {
        int GWLP_WNDPROC = -4;
        LONG_PTR SetWindowLongPtr(HWND hWnd, int nIndex, WindowProc wndProc);
        LONG_PTR SetWindowLongPtr(HWND hWnd, int nIndex, LONG_PTR wndProc);
        LRESULT CallWindowProc(LONG_PTR proc, HWND hWnd, int uMsg, WPARAM uParam, LPARAM lParam);
    }

	public WindowProcedure()
	{
		INSTANCEEx = (User32Ex) Native.load("User32", User32Ex.class, W32APIOptions.DEFAULT_OPTIONS);
	}

	public void init(WinDef.HWND hwnd)
	{
		this.hwnd = hwnd;
		defWndProc = INSTANCEEx.SetWindowLongPtr(hwnd, User32Ex.GWLP_WNDPROC, this);
		INSTANCEEx.SetWindowPos(hwnd, hwnd, 0, 0, 0, 0, SWP_NOMOVE | SWP_NOSIZE | SWP_NOZORDER | SWP_FRAMECHANGED);
	}

	@Override
	public LRESULT callback(HWND hwnd, int uMsg, WPARAM wparam, LPARAM lparam)
	{
		LRESULT lresult = null;

		switch (uMsg)
		{
			case WM_NCCALCSIZE:
				return new LRESULT(0);
            case WM_NCHITTEST:
                lresult = this.BorderLessHitTest(hwnd, uMsg, wparam, lparam);

                if (lresult.intValue() == new LRESULT(0).intValue())
                {
                    return INSTANCEEx.CallWindowProc(defWndProc, hwnd, uMsg, wparam, lparam);
                }
                return lresult;
            case WM_DESTROY:
                INSTANCEEx.SetWindowLongPtr(hwnd, User32Ex.GWLP_WNDPROC, defWndProc);
                return new LRESULT(0);
            default:
                lresult = INSTANCEEx.CallWindowProc(defWndProc, hwnd, uMsg, wparam, lparam);
                return lresult;
		}
	}

    LRESULT BorderLessHitTest(HWND hWnd, int message, WPARAM wParam, LPARAM lParam) {
        int borderOffset = WindowParams.getMaximizedWindowFrameThickness();
        int borderThickness = WindowParams.getFrameResizeBorderThickness();

        POINT ptMouse = new POINT();
        RECT rcWindow = new RECT();
        User32.INSTANCE.GetCursorPos(ptMouse);
        User32.INSTANCE.GetWindowRect(hWnd, rcWindow);

        int uRow = 1, uCol = 1;
        boolean fOnResizeBorder = false, fOnFrameDrag = false;

        int topOffset = WindowParams.getTitleBarHeight() == 0 ? borderThickness : WindowParams.getTitleBarHeight();
        if (ptMouse.y >= rcWindow.top && ptMouse.y < rcWindow.top + topOffset + borderOffset) {
            fOnResizeBorder = (ptMouse.y < (rcWindow.top + borderThickness));  // Top Resizing
            if (!fOnResizeBorder) {
                fOnFrameDrag = (ptMouse.y <= rcWindow.top + WindowParams.getTitleBarHeight() + borderOffset)
                        && (ptMouse.x < (rcWindow.right - (WindowParams.getControlBoxWidth()
                        + borderOffset + WindowParams.getExtraRightReservedWidth())))
                        && (ptMouse.x > (rcWindow.left + WindowParams.getIconWidth()
                        + borderOffset + WindowParams.getExtraLeftReservedWidth()));
            }
            uRow = 0; // Top Resizing or Caption Moving
        } else if (ptMouse.y < rcWindow.bottom && ptMouse.y >= rcWindow.bottom - borderThickness)
            uRow = 2; // Bottom Resizing
        if (ptMouse.x >= rcWindow.left && ptMouse.x < rcWindow.left + borderThickness)
            uCol = 0; // Left Resizing
        else if (ptMouse.x < rcWindow.right && ptMouse.x >= rcWindow.right - borderThickness)
            uCol = 2; // Right Resizing

        final int HTTOPLEFT = 13, HTTOP = 12, HTCAPTION = 2, HTTOPRIGHT = 14, HTLEFT = 10, HTNOWHERE = 0,
                HTRIGHT = 11, HTBOTTOMLEFT = 16, HTBOTTOM = 15, HTBOTTOMRIGHT = 17, HTSYSMENU = 3;

        int[][] hitTests = {
                {HTTOPLEFT, fOnResizeBorder ? HTTOP : fOnFrameDrag ? HTCAPTION : HTNOWHERE, HTTOPRIGHT},
                {HTLEFT, HTNOWHERE, HTRIGHT},
                {HTBOTTOMLEFT, HTBOTTOM, HTBOTTOMRIGHT},
        };

        return new LRESULT(hitTests[uRow][uCol]);
    }
}
