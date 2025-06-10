//package io.github.graspit.android;
//
//import android.os.Bundle;
//
//import com.badlogic.gdx.ApplicationListener;
//import com.badlogic.gdx.backends.android.AndroidApplication;
//import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
//
//import io.github.graspit.HelloWorldApp;
//
//
//public class AndroidLauncher extends AndroidApplication {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
//        configuration.useImmersiveMode = true;
//        initialize((ApplicationListener) new HelloWorldApp(), configuration);
//    }
//}

package io.github.graspit.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import io.github.graspit.AndroidLauncherAware;
import io.github.graspit.HelloWorldApp;

public class AndroidLauncher extends AndroidApplication implements AndroidLauncherAware {

    private FrameLayout layout;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    initialize(new HelloWorldApp(), config);
}

    @Override
    public void showWoGlb() {
        runOnUiThread(() -> {
            WoGlb viewer = new WoGlb(this);
            viewer.loadModel("model.html");
            setContentView(viewer);
        });
    }
    @Override
    public void openMain() {
        runOnUiThread(() -> {
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
        });
    }
}


//package io.github.graspit.android;
//
//import android.os.Bundle;
//import com.badlogic.gdx.backends.android.AndroidApplication;
//import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
//import io.github.graspit.HelloWorldApp;
//
//public class AndroidLauncher extends AndroidApplication {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        String subject = getIntent().getStringExtra("subject");
//        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//        config.useImmersiveMode = true;
//
//        initialize(new HelloWorldApp(subject), config); // pass subject to game
//    }
//}


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        layout = new FrameLayout(this);
//        setContentView(layout);
//
//        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//
//        HelloWorldApp helloWorldApp = new HelloWorldApp();
//        layout.addView(initializeForView(helloWorldApp, config));
//
