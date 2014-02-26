package info.juanmendez.filemanipulation;

import info.juanmendez.utils.Trace;

import java.io.File;

import org.androidannotations.annotations.EReceiver;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

@EReceiver
public class DownloadReceiver extends BroadcastReceiver
{
	@Pref DownloadPrefs_ prefs;
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		// Environment.DIRECTORY_DOWNLOADS, UPDATE_FILENAME
		
		String zipLoc = prefs.zipDir().get();
		
		if( zipLoc != null )
		{
			File update = new File( zipLoc );

			if (update.exists())
			{
				Intent i = new Intent( context, UnzipService_.class );
				context.startService(i);
			}
			else
			{
				Trace.warn( "can't unzip file as it doesn't exist", this );
			}

		}
	}
}
