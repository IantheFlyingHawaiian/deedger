package gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import dghelpers.AssetLoader;
import gameobjects.Deeg;
import gameobjects.ScrollHandler;

/**
 * Created by Ian on 3/7/2016.
 */
public class GameWorld {

    private Deeg deeg;
   /* private Rectangle rect = new Rectangle(0,0,17,12);*/
    private ScrollHandler scroller;

    private Rectangle ground;

    private int score = 0;


    public GameWorld(int midPointY) {
        //Initialize Deeg here
        deeg = new Deeg(33, midPointY-5,17,12);

        // The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(this,midPointY + 66);

        ground = new Rectangle(0,midPointY+66,136,11);
    }

    public void update(float delta) {
        /*Gdx.app.log("GameWorld", "update");
        rect.x++;
        if(rect.x > 137) {
            rect.x = 0;
        }*/

        if (delta > .15f) {
            delta = .15f;
        }
        deeg.update(delta);
        scroller.update(delta);

        if (scroller.collides(deeg) && deeg.isAlive()) {
            scroller.stop();
            deeg.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(deeg.getBoundingCircle(), ground)) {
            scroller.stop();
            deeg.die();
            deeg.decelerate();
        }

    /*public Rectangle getRect() {
        return rect;
    }*/
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score+= increment;
    }
    public Deeg getDeeg(){
        return deeg;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

}
