package info.juanmendez.filemanipulation;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import android.view.Menu;
import android.view.MenuItem;

@EBean
public class MenuPrefs
{
	private Menu _menu;
	private MenuItem restore;
	private MenuItem download;

	public void set_menu(Menu _menu)
	{
		this._menu = _menu;
		
		restore = _menu.findItem(R.id.restore);
		download = _menu.findItem(R.id.download);
	}
	
	@UiThread
	public void showRestore()
	{
		if( _menu != null )
		{
			restore.setVisible(true);
			download.setVisible(false);
		}
	}
	
	@UiThread
	public void showDownload()
	{
		if( _menu != null )
		{
			restore.setVisible(false);
			download.setVisible(true);
		}
	}
}