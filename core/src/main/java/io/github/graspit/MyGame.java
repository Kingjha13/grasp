//package io.github.graspit;
//
//import com.badlogic.gdx.Game;
//
//public class MyGame extends Game {
//    public static String START_SCREEN = null;
//
//    @Override
//    public void create() {
//        if ("another".equals(START_SCREEN)) {
//            setScreen(new AnotherScreen());
//        } else {
//            setScreen(new HelloWorldApp());
//        }
//    }
//}


package io.github.graspit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGame extends Game {
    public static String START_SCREEN = null;
    private final AndroidLauncherAware launcher;

    public MyGame(AndroidLauncherAware launcher) {
        this.launcher = launcher;
    }

    @Override
    public void create() {
        if ("another".equals(START_SCREEN)) {
            setScreen(new AnotherScreen(launcher));
        }
        else if ("form".equals(START_SCREEN)){
            setScreen(new AnotherScreen(launcher));
        }
        else if ("abc".equals(START_SCREEN)) {
            setScreen(new Abc(launcher));
        }
        else {
            setScreen(new HelloWorldApp());
        }
    }
}
