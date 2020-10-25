package GameState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class GameState {

    protected GameStateManager gsm;

    public abstract void init();
    public abstract void update() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void keyPressed(int k) throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract void keyReleased(int k);


}
