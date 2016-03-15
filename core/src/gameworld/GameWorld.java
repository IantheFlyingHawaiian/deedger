package gameworld;

/**
 * Created by Ian on 3/7/2016.
 */

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import dghelpers.AssetLoader;
import gameobjects.Deeg;
import gameobjects.ScrollHandler;

public class GameWorld {

    private Deeg deeg;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;

    private int midPointY;

    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        this.midPointY = midPointY;
        deeg = new Deeg(33, midPointY - 5, 17, 12);
        // The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);
    }

    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }

    private void updateReady(float delta) {
        // Do nothing for now
    }

    public void updateRunning(float delta) {
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

            if(score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
            currentState = GameState.GAMEOVER;
        }
    }

    public Deeg getDeeg() {
        return deeg;

    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        deeg.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }
}