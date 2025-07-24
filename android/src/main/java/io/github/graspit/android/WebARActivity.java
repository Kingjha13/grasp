package io.github.graspit.android;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
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
import android.widget.Toast;

import androidx.annotation.NonNull;


//not not work proper but i save
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


//this work proper but in two device show issue
//public class WebARActivity extends Activity {
//
//    WebView webView;
//
//    @SuppressLint("SetJavaScriptEnabled")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web_ar);
//
//        webView = findViewById(R.id.we_view);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
//            }
//        }
//        WebSettings settings = webView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setDomStorageEnabled(true);
//        settings.setMediaPlaybackRequiresUserGesture(false);
//        settings.setAllowFileAccess(true);
//
//        webView.setWebViewClient(new WebViewClient());
//
//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onPermissionRequest(final PermissionRequest request) {
//                runOnUiThread(() -> {
//                    if (request.getOrigin().toString().contains("8thwall.app")) {
//                        request.grant(request.getResources());
//                    }
//                });
//            }
//        });
//
//        webView.loadUrl("https://avanishjha.8thwall.app/hear/");
//    }
//}





public class WebARActivity extends Activity {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1;
//    private static final String WEB_AR_URL = "https://avanishjha.8thwall.app/hear/";
//private static final String WEB_AR_URL = "https://avnishjha.8thwall.app/cactus/";
private static final String WEB_AR_URL = "https://avnishjha.8thwall.app/tree/";

    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_ar);

        webView = findViewById(R.id.we_view);
        setupWebView();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.CAMERA},
//                    CAMERA_PERMISSION_REQUEST_CODE);
//            } else {
//                webView.loadUrl(WEB_AR_URL);
//            }
//        } else {
//            webView.loadUrl(WEB_AR_URL);
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
            } else {
                webView.loadUrl(WEB_AR_URL);
            }
        } else {
            webView.loadUrl(WEB_AR_URL);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
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
                    } else {
                        request.deny();
                    }
                });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                webView.loadUrl(WEB_AR_URL);
            } else {
                Toast.makeText(this, "Camera permission is required for AR experience.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        }
    }
}
