package org.beuwi.msgbots.manager;

import javafx.css.PseudoClass;

import org.beuwi.msgbots.base.Manager;
import org.beuwi.msgbots.base.enums.ToastType;
import org.beuwi.msgbots.program.app.view.actions.ShowToastMessageAction;
import org.beuwi.msgbots.program.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.program.gui.control.ToastItem;
import org.beuwi.msgbots.setting.GlobalSettings;

public class MdbManager implements Manager {
	private static boolean power = false;
	private static final PseudoClass pseudoClass = PseudoClass.getPseudoClass("mdb");
	private static final String adbPath = GlobalSettings.getString("adb.filePath");
	/* private static final ADB adb = new ADB(adbPath);
	private static final MDB mdb = new MDB(adb);
	private static final Communicator communicator = new Communicator(adb);
	private static final DebugRoom debugRoom = communicator.getDebugRoom();
	private static final BotManager botManager = communicator.getBotManager(); */

	public static void init() {
		try {
			// System.out.println(mdb.setActivation(true));
			// debugRoom.setOnBadRequestListener();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void switching() {
		power = !power;
		if (power) {
            ShowToastMessageAction.getInstance().execute(new ToastItem(ToastType.INFO, "Mdb Connected", ""));
            // RefreshAllBotsAction.getInstance()
		}
		else {
		}
        StatusBarPart.getInstance().pseudoClassStateChanged(pseudoClass, power);
	}

	public static boolean getStatus() {
	    return power;
    }

    public static boolean getPower(String name) {
		// mdb.setBotPower()
		return true;
	}

	private static void execute(String command) {

	}
}
