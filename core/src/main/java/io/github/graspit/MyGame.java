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
        } else {
            setScreen(new HelloWorldApp());
        }
    }
}

//for testing
