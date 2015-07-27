package com.awilliams.mywebapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Andrew on 7/26/2015.
 */
public class MyWebViewClient extends WebViewClient {

    private static final String TAG = MyWebViewClient.class.getSimpleName();

    private Activity activity = null;
    MyWebViewClient(Activity activity ){this.activity = activity;}

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i(TAG, "elj.url=" + url);
        Uri uri = Uri.parse(url);
        String scheme = uri.getScheme();
        if("tel".equals(scheme)) {
            try {
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                activity.startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Log.e(TAG, ex.getMessage(), ex);
            }
            return true;
        }
        if(url.endsWith(".pdf")) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Log.e(TAG, ex.getMessage(), ex);
            }
            return true;
        }
        view.getSettings().setUseWideViewPort(true);
        view.loadUrl(url);
        return true;
    }
}
