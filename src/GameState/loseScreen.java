package GameState;

import TileMap.Background;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static GameState.MenuState.selectSound;

public class loseScreen extends GameState {

        private Background bg;

        private int currentChoice = 0;
        private String[] options = {"Again", "Quit"};

        private Color titleColor;
        private Font titleFont;

        private Font font;

        public loseScreen(GameStateManager gsm) {


            this.gsm = gsm;

            try {
                bg = new Background("/Backgrounds/DarkCity.png", 1);
                bg.setVector(-0.1,  0);;

                titleColor = new Color(250, 250, 0);
                titleFont = new Font("Century Gothic", Font.PLAIN, 28);

                font = new Font("Arial", Font.PLAIN, 12);
            }
            catch(Exception e) {
                e.printStackTrace();
            }



        }


        public void init() {}
        public void update() { bg.update(); }
        public void draw(Graphics2D g) {

            //draw bg
            bg.draw(g);

            //draw title
            g.setColor(titleColor);
            g.setFont(titleFont);
            g.drawString("  You Lose",  80,  70);;

            //draw menu options
            g.setFont(font);
            for(int i = 0; i < options.length; i++) {
                if(i == currentChoice) {
                    g.setColor(Color.YELLOW);
                }
                else {
                    g.setColor(Color.WHITE);;
                }
                g.drawString(options[i],  145,  140+i*15);;
            }
        }

        private void select() {
            if(currentChoice == 0) {
                gsm.setState(GameStateManager.LEVEL1STATE);
            }

            if(currentChoice == 1) {
                System.exit(0);
            }
        }

        public void keyPressed(int k) {
            if(k == KeyEvent.VK_ENTER) {
                try {
                    selectSound();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
                select();
            }
            if(k == KeyEvent.VK_UP) {
                currentChoice--;
                if(currentChoice == -1) {
                    currentChoice = options.length -1;
                }
            }
            if(k == KeyEvent.VK_DOWN) {
                currentChoice++;
                if(currentChoice == options.length) {
                    currentChoice = 0;
                }
            }
        }
        public void keyReleased(int k) {}


    }



