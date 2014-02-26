//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package info.juanmendez.filemanipulation;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import info.juanmendez.filemanipulation.R.array;
import info.juanmendez.filemanipulation.R.layout;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FileListActivity_
    extends FileListActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private Handler handler_ = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.activity_filelist);
    }

    private void init_(Bundle savedInstanceState) {
        prefs = new DownloadPrefs_(this);
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        Resources resources_ = this.getResources();
        dialog_list = resources_.getStringArray(array.item_dialog_list);
        fileControl = FileControl_.getInstance_(this);
        menuPrefs = MenuPrefs_.getInstance_(this);
        afterInject();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static FileListActivity_.IntentBuilder_ intent(Context context) {
        return new FileListActivity_.IntentBuilder_(context);
    }

    public static FileListActivity_.IntentBuilder_ intent(Fragment supportFragment) {
        return new FileListActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        textFileList = ((ListView) hasViews.findViewById(info.juanmendez.filemanipulation.R.id.listView1));
        {
            AdapterView<?> view = ((AdapterView<?> ) hasViews.findViewById(info.juanmendez.filemanipulation.R.id.listView1));
            if (view!= null) {
                view.setOnItemClickListener(new OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        FileListActivity_.this.itemClicked(((String) parent.getAdapter().getItem(position)));
                    }

                }
                );
            }
        }
        {
            AdapterView<?> view = ((AdapterView<?> ) hasViews.findViewById(info.juanmendez.filemanipulation.R.id.listView1));
            if (view!= null) {
                view.setOnItemLongClickListener(new OnItemLongClickListener() {


                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        FileListActivity_.this.itemLongClicked(((String) parent.getAdapter().getItem(position)));
                        return true;
                    }

                }
                );
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(info.juanmendez.filemanipulation.R.menu.file, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = super.onOptionsItemSelected(item);
        if (handled) {
            return true;
        }
        int itemId_ = item.getItemId();
        if (itemId_ == info.juanmendez.filemanipulation.R.id.restore) {
            restoreMe();
            return true;
        }
        if (itemId_ == info.juanmendez.filemanipulation.R.id.download) {
            downloadMe();
            return true;
        }
        return false;
    }

    @Override
    public void feedList(final List<String> fileNames) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                FileListActivity_.super.feedList(fileNames);
            }

        }
        );
    }

    @Override
    public void deleteUpdate(final String fileName) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                FileListActivity_.super.deleteUpdate(fileName);
            }

        }
        );
    }

    @Override
    public void loadFiles() {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    FileListActivity_.super.loadFiles();
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void restoreMe() {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    FileListActivity_.super.restoreMe();
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, FileListActivity_.class);
        }

        public IntentBuilder_(Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, FileListActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public FileListActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent_, requestCode);
            } else {
                if (context_ instanceof Activity) {
                    ((Activity) context_).startActivityForResult(intent_, requestCode);
                } else {
                    context_.startActivity(intent_);
                }
            }
        }

    }

}