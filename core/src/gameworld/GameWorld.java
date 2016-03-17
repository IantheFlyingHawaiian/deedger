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
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;
    private float runTime = 0;
    private int midPointY;
    private GameRenderer renderer;

    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int midPointY) {
        currentState = GameState.MENU;
        this.midPointY = midPointY;
        deeg = new Deeg(33, midPointY - 5, 17, 12);
        //The grass should start at 66 pixels below the midPoint Y
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);
    }

    public void update(float delta) {
        runTime += delta;

        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }

    public void updateReady(float delta) {
        deeg.updateReady(runTime);
        scroller.updateReady(delta);
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        deeg.update(delta);
        scroller.update(delta);

        //If Deeg deeg collides with Pipe, stop the game
        if (scroller.collides(deeg) && deeg.isAlive()) {
            scroller.stop();
            deeg.die();
            AssetLoader.dead.play();
            renderer.prepareTransition(255, 255, 255, .3f);

            AssetLoader.fall.play();
        }

        //If Deeg deeg hits ground, stop the game
        if (Intersector.overlaps(deeg.getBoundingCircle(), ground)) {

            if(deeg.isAlive()) {
                AssetLoader.dead.play();
                renderer.prepareTransition(255,255,255,.3f);

                deeg.die();
            }
            scroller.stop();
            deeg.decelerate();
            currentState = GameState.GAMEOVER;

            //If the CurrrentScore is > Highscore,
            //set gameState to HIGHSCORE
            if (score > AssetLoader.getHighScore()){
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    public Deeg getDeeg() {
        return deeg;
    }

    public int getMidPointY() {
        return midPointY;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score+= increment;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void ready() {
        currentState = GameState.READY;
        renderer.prepareTransition(0,0,0,1f);
    }

    public void restart() {
        score = 0;
        deeg.onRestart(midPointY - 5);
        scroller.onRestart();
        ready();
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public void setRenderer(GameRenderer renderer) {
        this.renderer = renderer;
    }

}