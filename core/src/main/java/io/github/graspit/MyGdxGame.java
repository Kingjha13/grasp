package io.github.graspit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGdxGame extends Game {
    private final AndroidLauncherAware androidLauncher;
    public Skin skin; // Declare the skin variable

    public MyGdxGame(AndroidLauncherAware androidLauncher) {
        this.androidLauncher = androidLauncher;
    }

    @Override
    public void create() {
        try {
            skin = new Skin(Gdx.files.internal("uiskin.json"));
            setScreen(new Subject6Screen(this, androidLauncher));
        } catch (Exception e) {
            Gdx.app.error("MyGdxGame", "Error loading skin", e);
        }
    }
}
