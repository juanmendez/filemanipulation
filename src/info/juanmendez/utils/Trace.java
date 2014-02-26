package info.juanmendez.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Trace
{
	private static String TAG = "jm";

	public static String getTAG()
	{
		return TAG;
	}

	public static void setTAG(String tag)
	{
		TAG = tag;
	}

	public static void warn(String msg )
	{
		Log.w(TAG, 	msg	);
	}
	
	public static void warn( String msg, Object g )
	{
		Log.w(TAG, g.getClass().getName() + ": "+ msg  );
	}
	
	
	public static void warn( String msg, Class<?> g )
	{
		Log.w(TAG, g.getName() + ": "+ msg  );
	}
	
	public static void error( String msg )
	{
		Log.e(TAG, msg );
	}
	
	public static void error( String msg, Object g )
	{
		Log.e(TAG, g.getClass().getName() + ": "+ msg  );
	}
	
	public static void error( String msg, Class<?> g )
	{
		Log.e(TAG, g.getName() + ": "+ msg  );
	}
	
	public static void toast( Context ctxt, String message )
	{
		Toast.makeText(ctxt, message, Toast.LENGTH_LONG ).show();
	}
}
