//package io.github.graspit;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.GlyphLayout;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.MathUtils;
//
//public class HelloWorldApp extends ApplicationAdapter {
//    private SpriteBatch batch;
//    private BitmapFont font;
//    private float fontScale;
//    private GlyphLayout layout;
//
//    @Override
//    public void create() {
//        batch = new SpriteBatch();
//        font = new BitmapFont();
//        font.setColor(1, 1, 0, 1); // Yellow
//
//        layout = new GlyphLayout();
//        updateFontScale();
//    }
//
//    private void updateFontScale() {
//        int minScreenDim = Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        fontScale = MathUtils.clamp(minScreenDim / 480f, 1f, 2f);  // Keeps font readable
//        font.getData().setScale(fontScale);
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        updateFontScale();
//    }
//
//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(0, 0, 0, 1); // Black background
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        String text = "Hello World!";
//        layout.setText(font, text);
//
//        float x = (Gdx.graphics.getWidth() - layout.width) / 2f;
//        float y = (Gdx.graphics.getHeight() + layout.height) / 2f;
//
//        batch.begin();
//        font.draw(batch, layout, x, y);
//        batch.end();
//    }
//    @Override
//    public void dispose() {
//        batch.dispose();
//        font.dispose();
//    }
//}


//package io.github.graspit;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.GlyphLayout;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//
//public class HelloWorldApp extends ApplicationAdapter {
//    private SpriteBatch batch;
//    private BitmapFont font;
//    private GlyphLayout layout;
//    private boolean triggered = false;
//
//    @Override
//    public void create() {
//        batch = new SpriteBatch();
//        font = new BitmapFont();
//        font.getData().setScale(2f);
//        layout = new GlyphLayout(font, "Open 3D Model");
//    }
//
//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        font.draw(batch, layout,
//            (Gdx.graphics.getWidth() - layout.width) / 2f,
//            (Gdx.graphics.getHeight() + layout.height) / 2f);
//        batch.end();
//
//        if (Gdx.input.justTouched() && !triggered) {
//            triggered = true;
//
//            Gdx.app.postRunnable(() -> {
//                if (Gdx.app instanceof AndroidLauncherAware) {
//                    ((AndroidLauncherAware) Gdx.app).showWoGlb();
//                }
//            });
//        }
//    }
//
//    @Override
//    public void dispose() {
//        batch.dispose();
//        font.dispose();
//    }
//}


package io.github.graspit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HelloWorldApp implements Screen {
    private Stage stage;
    private Skin skin;
    public HelloWorldApp() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        BitmapFont font = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        skin.add("default", font);
        skin.add("default", labelStyle);
        skin.add("default", buttonStyle);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton class11Button = new TextButton("Class 11", skin);
        TextButton class12Button = new TextButton("Class 12", skin);

        class11Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Gdx.app instanceof AndroidLauncherAware) {
                    ((AndroidLauncherAware) Gdx.app).openMain();
                }
            }
        });

        class12Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Gdx.app instanceof AndroidLauncherAware) {
                    ((AndroidLauncherAware) Gdx.app).openMain();
                }
            }
        });

        table.add(class11Button).pad(20).row();
        table.add(class12Button).pad(20);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}





//
//

//public class HelloWorldApp extends Game {
//    private SpriteBatch batch;
//    private BitmapFont font;
//    private GlyphLayout layout;
//    private boolean triggered = false;
//    private Skin skin;
//
//    @Override
//    public void create() {
//        batch = new SpriteBatch();
//        font = new BitmapFont();
//        font.getData().setScale(2f);
//        layout = new GlyphLayout(font, "Open 3D Model");
//
//        // Create a skin with default label style
//        skin = new Skin();
//        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
//        skin.add("default", labelStyle);
//    }
//
//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        font.draw(batch, layout,
//            (Gdx.graphics.getWidth() - layout.width) / 2f,
//            (Gdx.graphics.getHeight() + layout.height) / 2f);
//        batch.end();
//
//        if (Gdx.input.justTouched() && !triggered) {
//            triggered = true;
//
//            Gdx.app.postRunnable(() -> {
//                setScreen(new TestScreen(this, skin));
//            });
//        }
//    }
//    @Override
//    public void dispose() {
//        batch.dispose();
//        font.dispose();
//        skin.dispose();
//    }
//}
