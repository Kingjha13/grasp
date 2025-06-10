package io.github.graspit.android;

import android.app.Activity;
import android.os.Bundle;

public class ModelActivity extends Activity {

    private WoGlb woGlb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        woGlb = new WoGlb(this);
        setContentView(woGlb);

        // Load your model.html from assets
        woGlb.loadModel("model.html");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (woGlb != null) {
            woGlb.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (woGlb != null) {
            woGlb.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (woGlb != null) {
            woGlb.onDestroy();
        }
        super.onDestroy();
    }
}
