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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import io.github.graspit.Abc;
import io.github.graspit.AndroidLauncherAware;
import io.github.graspit.AnotherScreen;
import io.github.graspit.HelloWorldApp;
import io.github.graspit.MyGame;

public class AndroidLauncher extends AndroidApplication implements AndroidLauncherAware {

    private MyGame libGdxGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        String screen = getIntent().getStringExtra("open_screen");
        if (screen != null) {
            MyGame.START_SCREEN = screen;
        } else {
            MyGame.START_SCREEN = null;
        }

        libGdxGame = new MyGame(this);
        initialize(libGdxGame, config);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        checkIntentForScreen(intent);
    }

    private void checkIntentForScreen(Intent intent) {
        if (intent != null && intent.hasExtra("open_screen")) {
            String screen = intent.getStringExtra("open_screen");
            if ("another".equals(screen)) {
                Gdx.app.postRunnable(() -> libGdxGame.setScreen(new AnotherScreen(this)));
            } else if ("hell".equals(screen)) {
                Gdx.app.postRunnable(() -> libGdxGame.setScreen(new HelloWorldApp()));
            }
            else if ("form".equals(screen)){
                Gdx.app.postRunnable(() -> libGdxGame.setScreen(new Abc(this)));
            }

            intent.removeExtra("open_screen");
        }
    }

    @Override
    public void showWoGlb() {
        // Your implementation here
    }

    @Override
    public void openWebView(String unit) {
        // Your implementation here
    }

    @Override
    public void openMain() {
        runOnUiThread(() -> {
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
        });
    }

    @Override
    public void open12() {
        runOnUiThread(() -> {
            Intent intent = new Intent(this, Subject12.class);
            startActivity(intent);
        });
    }
    @Override
    public void openar() {
        runOnUiThread(() -> {
            Intent intent = new Intent(this, WebARActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void open11() {
        runOnUiThread(() -> {
            Intent intent = new Intent(this, Subject11.class);
            startActivity(intent);
        });
    }
    @Override
    public void open6() {
        runOnUiThread(() -> ChapterActivity.start(AndroidLauncher.this, 6));
    }

    public void open7() {
        Intent intent = new Intent(this, SubjectActivity.class);
        intent.putExtra(SubjectActivity.EXTRA_CLASS_NUMBER, 7);
        startActivity(intent);
    }



    @Override
    public void open8() {
        runOnUiThread(() -> ChapterActivity.start(this, 8));
    }

    @Override
    public void open9() {
        runOnUiThread(() -> ChapterActivity.start(this, 9));
    }

    @Override
    public void open10() {
        runOnUiThread(() -> ChapterActivity.start(this, 10));
    }


    @Override
    public void openAnother() {
        Gdx.app.postRunnable(() -> libGdxGame.setScreen(new AnotherScreen(this)));
    }
}



//public class AndroidLauncher extends AndroidApplication {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//
//        // Create a skin to use in TestScreen
//        Skin skin = new Skin();
//        BitmapFont font = new BitmapFont();
//        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
//        skin.add("default", labelStyle);
//
//        // Create an inline Game to load the TestScreen
//        Game game = new Game() {
//            @Override
//            public void create() {
//                setScreen(new TestScreen(this, skin));
//            }
//        };
//
//        initialize(game, config);
//    }
//}
//public class AndroidLauncher extends AndroidApplication {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//
//        Skin skin = new Skin();
//        BitmapFont font = new BitmapFont();
//        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
//        skin.add("default", style);
//
//        Game game = new Game() {
//            @Override
//            public void create() {
//                setScreen(new io.github.graspit.TestScreen(this, skin));
//            }
//        };
//
//        initialize(game, config);
//    }
//}



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
