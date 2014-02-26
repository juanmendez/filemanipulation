package info.juanmendez.filemanipulation;

import info.juanmendez.utils.FileUtils;

import java.io.File;
import java.io.IOException;

import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.app.IntentService;
import android.content.Intent;

@EService
public class UnzipService extends IntentService
{
	@Pref DownloadPrefs_ prefs;
	public static final String UPDATE_ACTION = "info.juanmendez.action.update_action";
	
	public UnzipService()
	{
		super("unzipService");
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		String zipLoc = prefs.zipDir().get();
		String unzipLoc = prefs.unzipDir().get();
		
		try
		{
			File zip = new File( zipLoc );
			FileUtils.unzip( zip, new File( unzipLoc ), true );
			prefs.edit().zipDir().put( "" ).apply();
			zip.delete();
			
			
			Intent i = new Intent(UPDATE_ACTION);
			
			/*
			 * (Usually optional) Set an explicit application package name that limits 
			 * the components this Intent will resolve to. If left to the default value 
			 * of null, all components in all applications will considered. If non-null, 
			 * the Intent can only match the components in the given application package.
			 */
			i.setPackage(getPackageName());
			sendOrderedBroadcast(i, null);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}