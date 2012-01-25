package com.twelvetwentyseven.regdar.android.untility;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class miniLog {
	
	private static miniLog singleInstance = null;
	
	/*
	 * Get instance class for singleton
	 */
	public static miniLog getInstance() {
		if(null==singleInstance){
			singleInstance = new miniLog();
			Log("Created a new instance:");
		}
		return singleInstance;
	}

	
	/*
	 * A custom debugger for adding datetime stamps
	 */
	public static void Log(String mMessage) {
			Date dT = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:SSSS");
			Log.i("Regdar Debug", mMessage + " " + format.format(dT));//dT.toString());
	}
	

	
	

}

