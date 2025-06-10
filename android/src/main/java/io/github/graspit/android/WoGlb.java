package io.github.graspit.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.util.Log;

//import androidx.annotation.NonNull;

// I create this class for 3d by using html file without using glb file

public class WoGlb extends FrameLayout {

    private static final String TAG = "WoGlb";
    private WebView webView;
    public WoGlb(Context context) {
        super(context);
        init(context);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init(Context context) {
        webView = new WebView(context);
        addView(webView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setBackgroundColor(Color.BLACK);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webView.setWebViewClient(new WebViewClient());
    }

    public void loadModel(String fileName) {
        String filePath = "file:///android_asset/" + fileName;
        try {
            webView.loadUrl(filePath);
            Log.d(TAG, "VR content loaded successfully: " + filePath);
        } catch (Exception e) {
            Log.e(TAG, "Failed to load VR content", e);
            Toast.makeText(getContext(), "Failed to load VR content", Toast.LENGTH_SHORT).show();
        }
    }

    public void onResume() {
        if (webView != null) {
            webView.onResume();
        }
    }

    public void onPause() {
        if (webView != null) {
            webView.onPause();
        }
    }

    public void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
    }
}
