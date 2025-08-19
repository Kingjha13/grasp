package io.github.graspit.android;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.AttributeSet;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.content.pm.PackageManager;

public class ArModel extends FrameLayout {

    private WebView webView;
    private String unitParam;

    @SuppressLint("SetJavaScriptEnabled")
    public ArModel(Context context) {
        super(context);
        init(context);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public ArModel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init(Context context) {
        webView = new WebView(context);
        addView(webView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setMediaPlaybackRequiresUserGesture(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (unitParam != null) {
                    webView.evaluateJavascript("javascript:loadChapterFromParam('" + unitParam + "')", null);
                }
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                if (request.getOrigin().toString().contains("8thwall.app")) {
                    request.grant(request.getResources());
                } else {
                    request.deny();
                }
            }
        });
    }

    public void loadModel(String modelFile) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Camera permission required", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                intent.setData(uri);
                getContext().startActivity(intent);
                return;
            }
        }

        webView.loadUrl("file:///android_asset/" + modelFile);
    }

    public void setUnitParam(String param) {
        this.unitParam = param;
    }

    public void onResume() {
        if (webView != null) webView.onResume();
    }

    public void onPause() {
        if (webView != null) webView.onPause();
    }

    public void onDestroy() {
        if (webView != null) webView.destroy();
    }
}
