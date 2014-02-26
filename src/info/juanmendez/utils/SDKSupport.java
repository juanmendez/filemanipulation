package info.juanmendez.utils;

import android.os.Build;

public class SDKSupport
{
	/**
	 * api 11 or greater
	 * @return
	 */
	public static boolean honeyOrHigher()
	{
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
	}
	
	/**
	 * api 9 or greater
	 * @return
	 */
	public static boolean gingerOrHigher()
	{
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD; 
	}
	
	public static boolean kitkatOrHigher()
	{
		return Build.VERSION.SDK_INT >= 19;
	}
	
}
