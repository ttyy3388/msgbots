package org.beuwi.msgbots.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

@SuppressWarnings("serial")
public class Device extends ScriptableObject
{
    @Override
    public String getClassName() 
	{
        return "Device";
    }

	public Device(ScriptableObject object)
	{
		super(object, object.getPrototype());
	}
    
    @JSStaticFunction
	public static Object getBuild()
	{
		return null;
	}

	@JSStaticFunction
	public static int getAndroidVersionCode()
	{
		return 28;
	}

	@JSStaticFunction
	public static String getAndroidVersionName()
	{
		return "9";
	}

	@JSStaticFunction
	public static String getPhoneBrand()
	{
		return "samsung";
	}

	@JSStaticFunction
	public static String getPhoneModel()
	{
		return "greatlteks";
	}

    @JSStaticFunction
	public static Boolean isCharging()
	{
		return true;
	}
	
    @JSStaticFunction
	public static String getPlugType()
	{
		return "usb";
	}
	
    @JSStaticFunction
	public static int getBatteryLevel()
	{
		return 100;
	}
	
    @JSStaticFunction
	public static int getBatteryHealth()
	{
		// BATTERY_HEALTH_GOOD
		return 3;
	}
	
    @JSStaticFunction
	public static int getBatteryTemperature()
	{
		return 1000;
	}
	
    @JSStaticFunction
	public static int getBatteryVoltage()
	{
		return 10000;
	}
	
    @JSStaticFunction
	public static int getBatteryStatus()
	{
		return 5;
	}
	
    @JSStaticFunction
	public static Object getBatteryIntent()
	{
		return null;
	}
}
