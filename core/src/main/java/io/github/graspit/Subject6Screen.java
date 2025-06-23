//package io.github.graspit;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.scenes.scene2d.ui.*;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//
//public class Subject6Screen implements Screen {
//    private final Game game;
//    private final AndroidLauncherAware androidLauncher;
//    private Stage stage;
//    private Skin skin;
//
//    public Subject6Screen(Game game, AndroidLauncherAware androidLauncher) {
//        this.game = game;
//        this.androidLauncher = androidLauncher;
//    }
//
//    @Override
//    public void show() {
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
//        skin = new Skin(Gdx.files.internal("uiskin.json"));
//
//        Table table = new Table();
//        table.setFillParent(true);
//        stage.addActor(table);
//
//        TextButton scienceButton = new TextButton("Science", skin);
//        table.add(scienceButton).width(300).height(80);
//
//        scienceButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new Chapter6Screen(game, androidLauncher));
//            }
//        });
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(1, 1, 1, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        stage.act(delta);
//        stage.draw();
//    }
//
//    @Override public void resize(int width, int height) {}
//    @Override public void pause() {}
//    @Override public void resume() {}
//    @Override public void hide() {}
//    @Override public void dispose() {
//        stage.dispose();
//        skin.dispose();
//    }
//}


package io.github.graspit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Subject6Screen implements Screen {
    private final Game game;
    private Stage stage;
    private Skin skin;

    public Subject6Screen(Game game, AndroidLauncherAware androidLauncher) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load default skin (make sure uiskin.json is in assets folder)
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Create a label with simple text
        Label label = new Label("Hello, GraspIt!", skin);
        label.setFontScale(2); // Make text bigger
        label.setPosition(
            Gdx.graphics.getWidth() / 2f - label.getWidth(),
            Gdx.graphics.getHeight() / 2f
        );

        stage.addActor(label);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
