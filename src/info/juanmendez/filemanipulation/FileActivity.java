package info.juanmendez.filemanipulation;

import info.juanmendez.utils.Trace;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

@EActivity(R.layout.activity_file)
public class FileActivity extends Activity
{
	@Pref DownloadPrefs_ prefs;
	
	@Extra("fileName")
	String fileName;
	
	@ViewById(R.id.editText1) 
	EditText editText;
	
	@ViewById(R.id.button1)
	Button btnSave;
	
	@AfterInject
	void afterInject()
	{
		setTitle(fileName);
	}
	
	@AfterViews
	void afterViews()
	{
		if( fileName != null && prefs.unzipDir().get() != null )
		{
			readFile();
			btnSave.setEnabled(true);
		}
	}
	
	private String getLocation()
	{
		return prefs.unzipDir().get() + "/" +  fileName;
	}
	
	@Background
	void readFile()
	{
		FileReader reader = null;
		StringBuilder allText = new StringBuilder();
		
		try
		{
			reader = new FileReader( getLocation() );
			BufferedReader bReader = new BufferedReader( reader );
			String line;
			
			while( (line = bReader.readLine()) != null )
			{
				allText.append( line + "\n" );
			}
			
			bReader.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if( reader != null )
			{
				try
				{
					reader.close();
					showFile(allText);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}
	}
	
	@UiThread
	void showFile( StringBuilder allText )
	{
		editText.setText(allText);
	}
	
	@Click(R.id.button1)
	void saveButton()
	{
		String content = editText.getText().toString();
		doSaveFile(content);
	}
	
	@Background
	void doSaveFile( String content )
	{
		PrintWriter writer = null;
		
		try
		{
			writer = new PrintWriter(new FileWriter(getLocation()));
			writer.println(content);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if( writer != null )
			{
				writer.close();
				confirmSave();
			}
		}
	}
	
	@UiThread
	void confirmSave()
	{
		Trace.toast(this, fileName + " has been saved" );
	}
}