package gameLoader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import dghelpers.AssetLoader;
import screens.SplashScreen;

/**
 * Created by Ian on 3/7/2016. Hello
 */
public class DeegGame extends Game {

    @Override
    public void create() {
        Gdx.app.log("DeegGame", "created");
        AssetLoader.load();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
