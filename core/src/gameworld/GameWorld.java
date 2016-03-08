package gameworld;

import gameobjects.Deeg;

/**
 * Created by Ian on 3/7/2016.
 */
public class GameWorld {

    private Deeg deeg;
   /* private Rectangle rect = new Rectangle(0,0,17,12);*/
    public GameWorld(int midPointY) {
        //Initialize Deeg here
        deeg = new Deeg(33, midPointY-5,17,12);
    }

    public void update(float delta) {
        /*Gdx.app.log("GameWorld", "update");
        rect.x++;
        if(rect.x > 137) {
            rect.x = 0;
        }*/
        deeg.update(delta);

    /*public Rectangle getRect() {
        return rect;
    }*/
    }
    public Deeg getDeeg()
    {
        return deeg;
    }

}
