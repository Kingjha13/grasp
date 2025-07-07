package io.github.graspit.android;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import io.github.graspit.R;
import android.webkit.WebView;
import android.Manifest;
import android.os.Bundle;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.pm.PackageManager;

import io.github.graspit.R;

import android.webkit.WebViewClient;

//public class WebARActivity extends Activity {
//
//    WebView webView;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web_ar);
//
//        webView = findViewById(R.id.we_view);
//
//        WebSettings settings = webView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setDomStorageEnabled(true);
//        settings.setMediaPlaybackRequiresUserGesture(false);
//        settings.setAllowFileAccess(true);
//
//        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebViewClient(new WebViewClient());
//
////        webView.loadUrl("https://avanishjha.8thwall.app/arvrd/");
//        webView.loadUrl("https://avanishjha.8thwall.app/hear/");
//    }
//}



public class WebARActivity extends Activity {

    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_ar);

        webView = findViewById(R.id.we_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setAllowFileAccess(true);

        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                runOnUiThread(() -> {
                    if (request.getOrigin().toString().contains("8thwall.app")) {
                        request.grant(request.getResources());
                    }
                });
            }
        });

        webView.loadUrl("https://avanishjha.8thwall.app/hear/");
    }
}
