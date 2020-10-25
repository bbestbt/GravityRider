package GameState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    public static final int LEVEL2STATE = 2;
    public static final int LEVEL3STATE = 3;
    public static final int LEVEL4STATE = 4;
    public static final int LOSESCREEN = 5;
    public static final int WINSCREEN = 6;


    public GameStateManager() {

        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Level1State(this));
        gameStates.add(new Level2State(this));
        gameStates.add(new Level3State(this));
        gameStates.add(new Level4State(this));
        gameStates.add(new loseScreen(this));
        gameStates.add(new winScreen(this));

    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }


    public void update() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }


}
