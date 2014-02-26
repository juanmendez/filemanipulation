package info.juanmendez.filemanipulation;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;


/**
 * This shared preference uses Application's default. So we can share
 * it among other services and activities.
 */

@SharedPref( value = SharedPref.Scope.APPLICATION_DEFAULT)
public interface DownloadPrefs
{
	@DefaultString("")
	String unzipDir();

	@DefaultString("")
	String zipDir();
}