//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package info.juanmendez.filemanipulation;

import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public final class DownloadService_
    extends DownloadService
{


    public static DownloadService_.IntentBuilder_ intent(Context context) {
        return new DownloadService_.IntentBuilder_(context);
    }

    public static DownloadService_.IntentBuilder_ intent(Fragment supportFragment) {
        return new DownloadService_.IntentBuilder_(supportFragment);
    }

    private void init_() {
        prefs = new DownloadPrefs_(this);
        manager = ((DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE));
    }

    @Override
    public void onCreate() {
        init_();
        super.onCreate();
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, DownloadService_.class);
        }

        public IntentBuilder_(Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, DownloadService_.class);
        }

        public Intent get() {
            return intent_;
        }

        public DownloadService_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public ComponentName start() {
            return context_.startService(intent_);
        }

        public boolean stop() {
            return context_.stopService(intent_);
        }

    }

}