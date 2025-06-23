package io.github.graspit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import java.util.*;

public class Chapter6Screen implements Screen {
    private final Game game;
    private final AndroidLauncherAware androidLauncher;
    private Stage stage;
    private Skin skin;

    private static final Map<String, String> chapterMap = Map.ofEntries(
        Map.entry("1. Food: Where Does It Come From?", "s6a"),
        Map.entry("2. Components of Food", "s6b"),
        Map.entry("3. Fibre to Fabric", "s6c"),
        Map.entry("4. Sorting Materials into Groups", "s6d"),
        Map.entry("5. Separation of Substances", "s6e"),
        Map.entry("6. Changes Around Us", "s6f"),
        Map.entry("7. Getting to Know Plants", "s6g"),
        Map.entry("8. Body Movements", "s6h"),
        Map.entry("9. The Living Organisms and Their Surroundings", "s6i"),
        Map.entry("10. Motion and Measurement of Distances", "s6j"),
        Map.entry("11. Light, Shadows and Reflections", "s6k"),
        Map.entry("12. Electricity and Circuits", "s6l"),
        Map.entry("13. Fun with Magnets", "s6m"),
        Map.entry("14. Water", "s6n"),
        Map.entry("15. Air Around Us", "s6o"),
        Map.entry("16. Garbage In, Garbage Out", "s6p")
    );

    public Chapter6Screen(Game game, AndroidLauncherAware androidLauncher) {
        this.game = game;
        this.androidLauncher = androidLauncher;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        ScrollPane scrollPane = new ScrollPane(table, skin);
        scrollPane.setFillParent(true);
        stage.addActor(scrollPane);

        for (String chapter : chapterMap.keySet()) {
            final String unit = chapterMap.get(chapter);
            TextButton button = new TextButton(chapter, skin);
            table.add(button).width(600).pad(5).row();
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    androidLauncher.openWebView(unit);
                }
            });
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
