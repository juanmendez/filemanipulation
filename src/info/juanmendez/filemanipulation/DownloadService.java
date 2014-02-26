package info.juanmendez.filemanipulation;

import info.juanmendez.utils.FileUtils;
import info.juanmendez.utils.SDKSupport;

import java.io.File;

import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

@EService
public class DownloadService extends IntentService
{
	@SystemService DownloadManager manager;
	@Pref DownloadPrefs_ prefs;
	
	public DownloadService()
	{
		super("downloadservice");
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		String url = intent.getStringExtra(FileListActivity.URL_PARAM);
		
		//confirm directory exist
		Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS).mkdirs();
		downloadFile(url);		
	}
	
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void downloadFile(String url)
	{
		if( FileUtils.isExternalStorageWritable() )
		{
			//Setting up object to request to Android
			DownloadManager.Request req = new DownloadManager.Request(Uri.parse(url));
			
			req.setAllowedNetworkTypes(
					DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
					.setAllowedOverRoaming(false)
					.setTitle(getString(R.string.download_title))
					.setDescription(getString(R.string.download_description))
					.setDestinationInExternalPublicDir(
							Environment.DIRECTORY_DOWNLOADS, FileListActivity.ADOPTION_FILENAME);
			
			
			if( SDKSupport.honeyOrHigher())
			{
				req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
			}
			else
			{
				req.setShowRunningNotification(false);
			}
			
			prefs.edit().zipDir().put(  new File( Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), FileListActivity.ADOPTION_FILENAME ).getAbsolutePath() )
					.apply();
			
			//append request to download manager
			manager.enqueue(req);
		}
		
		
	}
}
