package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import dghelpers.InputHandler;

/**
 * Created by Ian on 3/7/2016.
 */
public class GameScreen implements Screen {

    private gameworld.GameWorld world;
    private gameworld.GameRenderer renderer;

    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new gameworld.GameWorld(midPointY); //initialize a world
        renderer = new gameworld.GameRenderer(world); //initialize renderer

        Gdx.input.setInputProcessor(new InputHandler(world.getDeeg()));
    }

    @Override
    public void render(float delta) {
        /*//Sets a Color to Fill the Screen with (RGB=10,15,230), Opacity of 1 (100%)
        Gdx.gl.glClearColor(250/255.0f, 250/255.0f, 250/255.0f, 1f);
        // Fills the screen with the selected color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Covert Frame rate to String, print it
        Gdx.app.log("GameScreen FPS", (1/delta) + "");*/
        world.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
