package io.github.graspit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class WebViewScreen implements Screen {

    private final String unitParam;

    public WebViewScreen(Game game, String unitParam) {
        this.unitParam = unitParam;

        // Immediately open Android WebViewActivity
        openWebView(unitParam);
    }

    private void openWebView(String unitParam) {
        if (Gdx.app.getType() == com.badlogic.gdx.Application.ApplicationType.Android) {
            // Use reflection to call Android code from core module
            try {
                Class<?> clazz = Class.forName("com.graspit.AndroidLauncher");
                clazz.getMethod("openWebView", String.class).invoke(null, unitParam);
            } catch (Exception e) {
                Gdx.app.error("WebViewScreen", "Error launching WebView", e);
            }
        } else {
            Gdx.app.log("WebViewScreen", "WebView only available on Android.");
        }
    }

    @Override public void show() {}
    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
