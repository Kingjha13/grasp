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

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloWorldApp extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private GlyphLayout layout;
    private boolean triggered = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        layout = new GlyphLayout(font, "Open 3D Model");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, layout,
            (Gdx.graphics.getWidth() - layout.width) / 2f,
            (Gdx.graphics.getHeight() + layout.height) / 2f);
        batch.end();
        if (Gdx.input.justTouched() && !triggered) {
            triggered = true;

            Gdx.app.postRunnable(() -> {
                if (Gdx.app instanceof AndroidLauncherAware) {
                    ((AndroidLauncherAware) Gdx.app).openMain();
                }
            });
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}


