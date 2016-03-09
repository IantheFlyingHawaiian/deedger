package dghelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Ian on 3/7/2016.
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation deegAnimation;
    public static TextureRegion deeg, deegDown,deegUp;

    public static TextureRegion skullUp, skullDown, bar;

    public static void load() {
        texture = new Texture(Gdx.files.internal("texture.png"));
        texture.setFilter(TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false,true);

        grass = new TextureRegion(texture,0,43,143,11);
        grass.flip(false,true);

        deegDown = new TextureRegion(texture, 136, 0, 17, 12);
        deegDown.flip(false,true);

        deeg = new TextureRegion(texture, 153, 0, 17, 12);
        deeg.flip(false,true);

        deegUp = new TextureRegion(texture, 170, 0, 17, 12);
        deegUp.flip(false,true);

        TextureRegion[] deegs = {deegDown, deeg, deegUp};
        deegAnimation = new Animation(0.06f, deegs);
        deegAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture,192,0,24,14);
        // Create by flipping existing deegUp
        skullDown = new TextureRegion(deegUp);
        skullDown.flip(false,true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false,true);
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
    }
}
