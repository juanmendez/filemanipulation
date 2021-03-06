//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package info.juanmendez.filemanipulation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import info.juanmendez.filemanipulation.R.id;
import info.juanmendez.filemanipulation.R.layout;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FileActivity_
    extends FileActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    public final static String FILE_NAME_EXTRA = "fileName";
    private Handler handler_ = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.activity_file);
    }

    private void init_(Bundle savedInstanceState) {
        prefs = new DownloadPrefs_(this);
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        injectExtras_();
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

    public static FileActivity_.IntentBuilder_ intent(Context context) {
        return new FileActivity_.IntentBuilder_(context);
    }

    public static FileActivity_.IntentBuilder_ intent(Fragment supportFragment) {
        return new FileActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        btnSave = ((Button) hasViews.findViewById(id.button1));
        editText = ((EditText) hasViews.findViewById(id.editText1));
        {
            View view = hasViews.findViewById(id.button1);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        FileActivity_.this.saveButton();
                    }

                }
                );
            }
        }
        afterViews();
    }

    private void injectExtras_() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_!= null) {
            if (extras_.containsKey(FILE_NAME_EXTRA)) {
                fileName = extras_.getString(FILE_NAME_EXTRA);
            }
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        injectExtras_();
    }

    @Override
    public void confirmSave() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                FileActivity_.super.confirmSave();
            }

        }
        );
    }

    @Override
    public void showFile(final StringBuilder allText) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                FileActivity_.super.showFile(allText);
            }

        }
        );
    }

    @Override
    public void doSaveFile(final String content) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    FileActivity_.super.doSaveFile(content);
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void readFile() {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    FileActivity_.super.readFile();
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
            intent_ = new Intent(context, FileActivity_.class);
        }

        public IntentBuilder_(Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, FileActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public FileActivity_.IntentBuilder_ flags(int flags) {
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

        public FileActivity_.IntentBuilder_ fileName(String fileName) {
            intent_.putExtra(FILE_NAME_EXTRA, fileName);
            return this;
        }

    }

}
