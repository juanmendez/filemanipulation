package info.juanmendez.utils;

import android.annotation.TargetApi;
import android.os.AsyncTask;

public class AsyncUtils
{
	@TargetApi(11)
	static public <T> void execute(AsyncTask<T, ?, ?> task, T... params)
	{
		if (SDKSupport.honeyOrHigher())
		{
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
		}
		else
		{
			task.execute(params);
		}
	}

}
