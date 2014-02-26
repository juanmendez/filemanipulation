package info.juanmendez.filemanipulation;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EBean
public class FileControl
{
	@Pref DownloadPrefs_ prefs;
	
	boolean unzipDirExists()
	{
		return prefs.unzipDir().get() != null && new File( prefs.unzipDir().get() ).exists();
	}
	
	File[] getFiles()
	{
		String zipDir = prefs.unzipDir().get();
		File file = new File( zipDir );
		
		File[] files = file.listFiles( new FileFilter()
		{
			@Override
			public boolean accept(File pathname)
			{
				return pathname.getName().matches(".*?\\.txt");
			}
		});
		
		return files;
	}
	
	
	List<String> getFileNames()
	{
		File[] files = getFiles();
		
		List<String> fileNames = new ArrayList<String>();
		
		for( File f: files )
		{
			fileNames.add(f.getName());
		}
		
		return fileNames;
	}
	
	void deleteUnzipDir()
	{
		String unzipLoc = prefs.unzipDir().get();
		File unzipDir = new File( unzipLoc );
		unzipDir.delete();
		prefs.edit().unzipDir().put("").apply();
	}
	
	void clearUnzipDir()
	{
		File[] files = getFiles();
		
		for( File file: files )
		{
			if( file.exists() )
				file.delete();
		}
	}
	
	@Background
	void removeFile( String fileName, iFileInterface i )
	{
		File deleteFile = new File( prefs.unzipDir().get(), fileName );
		
		if( deleteFile.exists() )
		{
			deleteFile.delete();
			i.deleteUpdate( fileName );
		}
	}
	
	public interface iFileInterface
	{
		void deleteUpdate(String fileName );
	}
}
