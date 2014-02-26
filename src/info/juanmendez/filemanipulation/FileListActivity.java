package info.juanmendez.filemanipulation;

import info.juanmendez.utils.Trace;

import java.io.File;
import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@EActivity(R.layout.activity_filelist)
@OptionsMenu(R.menu.file)
public class FileListActivity extends Activity implements FileControl.iFileInterface
{	
	public static final String URL_PARAM = "download_zippy";
	public static final String ADOPTION_FILENAME = "zyppy.zip";
	public static final String UNZIP_DIR = "unzip";
	@Pref DownloadPrefs_ prefs;
	
	@ViewById(R.id.listView1) ListView textFileList;
	@StringArrayRes(R.array.item_dialog_list) String[] dialog_list;
	
	private UpdateReceiver update = new UpdateReceiver();
	
	@Bean MenuPrefs menuPrefs;
	@Bean FileControl fileControl;
	
	@AfterInject
	void afterInject()
	{	
		if( fileControl.unzipDirExists() )
		{
			this.loadFiles();
		}	
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
		menuPrefs.set_menu(menu);
		
		if( fileControl.unzipDirExists() )
		{
			menuPrefs.showRestore();
		}	
		else
		{
			menuPrefs.showDownload();
		}
		
        return super.onCreateOptionsMenu(menu);
    }
	
	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		
		IntentFilter f = new IntentFilter( UnzipService.UPDATE_ACTION );
		f.setPriority(1000);
		registerReceiver(update, f);
	}
	
	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(update);
		
	}
	
	@OptionsItem(R.id.download)
	void downloadMe()
	{
		Intent i = new Intent( this, DownloadService_.class );
		i.putExtra( URL_PARAM, "http://juanmendez.info/source/tutorial/android/zippy.zip");
		prefs.edit().unzipDir().put( new File( this.getFilesDir(), UNZIP_DIR ).getAbsolutePath() ).apply();
		
		if( prefs.zipDir().get().length() == 0 )
		{
			this.startService(i);
		}
	}
	
	@Background
	@OptionsItem(R.id.restore)
	void restoreMe()
	{
		fileControl.clearUnzipDir();
		fileControl.deleteUnzipDir();
		downloadMe();
	}
	
	@Background
	void loadFiles()
	{
		List<String> fileNames = fileControl.getFileNames();
		feedList( fileNames );
		
		if( fileNames.size() == 0 )
		{
			fileControl.deleteUnzipDir();
			menuPrefs.showDownload();
		}
		else
		{
			menuPrefs.showRestore();
		}
	}
	
	@UiThread
	public void feedList(List<String> fileNames)
	{
		textFileList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fileNames) );
	}
	
	
	@ItemClick(R.id.listView1)
    void itemClicked(String fileName) 
	{
        Intent i = new Intent(this, FileActivity_.class );
        i.putExtra( "fileName", fileName);
        this.startActivity(i);
    }
	
	@ItemLongClick(R.id.listView1)
	void itemLongClicked( final String fileName )
	{
		Context context = FileListActivity.this;
		
		AlertDialog.Builder ad = new AlertDialog.Builder(context);
		ad.setTitle(fileName);
		ad.setItems(dialog_list, new OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				switch( which )
				{
					case 0: 
						itemClicked( fileName );
					break;
					case 1: 
						fileControl.removeFile(fileName, FileListActivity.this );
					break;
				}
			}
		} );
		
		ad.show();
	}

	public class UpdateReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			FileListActivity.this.loadFiles();
		}
	}

	@UiThread
	@Override
	public void deleteUpdate(String fileName )
	{
		loadFiles();
		Trace.toast(this, fileName + " deleted");
	}

}