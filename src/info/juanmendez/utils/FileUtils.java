package info.juanmendez.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.os.Environment;

/**
 * Helpful methods to know if we can read and write to external storage
 * @see <a href="http://developer.android.com/guide/topics/data/data-storage.html#filesExternal">External Files</a>
 * @author Juan
 *
 */
public class FileUtils
{

	/* Checks if external storage is available for read and write */
	public static boolean isExternalStorageWritable()
	{
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state))
		{
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public static boolean isExternalStorageReadable()
	{
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * taken from Commonsware Book
	 * @param src
	 * @param dest
	 * @param clear
	 * @throws IOException
	 */
	public static void unzip(File src, File dest, boolean clear) throws IOException
	{
		InputStream is = new FileInputStream(src);
		ZipInputStream zipInStream = new ZipInputStream(new BufferedInputStream(is));
		ZipEntry zipEntry;

		dest.mkdirs();
		
		if( clear && dest.isDirectory() )
		{
			for(File file: dest.listFiles()) file.delete();
		}

		while ((zipEntry = zipInStream.getNextEntry()) != null)
		{
			byte[] buffer = new byte[8192];
			int count;
			FileOutputStream fileOutStream = new FileOutputStream(new File(dest,
					zipEntry.getName()));
			BufferedOutputStream out = new BufferedOutputStream(fileOutStream);

			try
			{
				while ((count = zipInStream.read(buffer)) != -1)
				{
					out.write(buffer, 0, count);
				}

				out.flush();
			}
			finally
			{
				fileOutStream.getFD().sync();
				out.close();
			}

			zipInStream.closeEntry();
		}

		zipInStream.close();
	}

}
