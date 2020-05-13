package org.beuwi.simulator.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

@SuppressWarnings("serial")
public class Device extends ScriptableObject
{
	public Device(ScriptableObject object)
	{
		super(object, object.getPrototype());
	}

    @Override
    public String getClassName() 
	{
        return "Device";
    }
    
    @JSFunction
	public Object getBuild()
	{
		return null;
	}

	@JSFunction
	public int getAndroidVersionCode()
	{
		return 28;
	}

	@JSFunction
	public String getAndroidVersionName()
	{
		return "9";
	}

	@JSFunction
	public String getPhoneBrand()
	{
		return "samsung";
	}

	@JSFunction
	public String getPhoneModel()
	{
		return "greatlteks";
	}

    @JSFunction
	public Boolean isCharging()
	{
		return true;
	}
	
    @JSFunction
	public String getPlugType()
	{
		return "usb";
	}
	
    @JSFunction
	public int getBatteryLevel()
	{
		return 100;
	}
	
    @JSFunction
	public int getBatteryHealth()
	{
		// BATTERY_HEALTH_GOOD
		return 3;
	}
	
    @JSFunction
	public int getBatteryTemperature()
	{
		return 1000;
	}
	
    @JSFunction
	public int getBatteryVoltage()
	{
		return 10000;
	}
	
    @JSFunction
	public int getBatteryStatus()
	{
		return 5;
	}
	
    @JSFunction
	public Object getBatteryIntent()
	{
		return null;
	}
}
