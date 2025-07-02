package io.github.graspit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Abc implements Screen {
    private Stage stage;
    private Skin skin;
    private final AndroidLauncherAware launcher;

    public Abc(AndroidLauncherAware launcher) {
        this.launcher = launcher;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        BitmapFont font = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.YELLOW;

        skin.add("default", font);
        skin.add("default", labelStyle);
        skin.add("default", buttonStyle);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        Label title = new Label("Select Class", skin);
        table.add(title).padBottom(30).row();

        addClassButton(table, "Class 6", () -> launcher.openMain());
        addClassButton(table, "Class 7", () -> launcher.open7());
        addClassButton(table, "Class 8", () -> launcher.open8());
        addClassButton(table, "Class 9", () -> launcher.open9());
        addClassButton(table, "Class 10", () -> launcher.openMain());
        addClassButton(table, "Class 11", () -> launcher.open11());
        addClassButton(table, "Class 12", () -> launcher.open12());
    }

    private void addClassButton(Table table, String label, Runnable action) {
        TextButton button = new TextButton(label, skin);
        button.pad(10);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.run();
            }
        });

        table.add(button).width(300).pad(10).row();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
