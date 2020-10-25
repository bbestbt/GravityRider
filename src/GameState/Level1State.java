package GameState;

import java.io.File;
import java.io.IOException;

import Entity.*;
import Entity.Enemies.*;
import Main.GamePanel;
import TileMap.*;


import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static Entity.Player.deadSound;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;
    private Boss b;
    private Boss2 b2;
    private Boss2 b3;
    private Boss2 b4;
    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;

    private HUD hud;
    private int count = 0;


    public Level1State(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public void init(){

        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset2.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0,0);
        tileMap.setTween(0.07);


        bg = new Background("/Backgrounds/DarkCity.png", 0.1);

        player = new Player(tileMap);
        player.setPosition(100, 100);

        populateEnemies();


        explosions = new ArrayList<Explosion>();

        hud = new HUD(player);


    }

    private void populateEnemies(){
        enemies = new ArrayList<Enemy>();
        Mon1 s;
        Point[] points = new Point[]{
                new Point(200, 200), new Point(860, 200), new Point(1525, 200), new Point(1680, 200), new Point(1800, 200)
                //


        };
        for(int i = 0; i < points.length; i++){
            s = new Mon1(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);
        }
       // Boss b;
        b = new Boss(tileMap);
        b.setPosition(3000,100);
        enemies.add(b);




    }

    public void update() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        //update player
        player.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());

        /// 15 <
        if(player.gety() > GamePanel.HEIGHT - 20){
            deadSound();
            gsm.setState(GameStateManager.LOSESCREEN);
        }


        //System.out.println(player.getHealth());

        // System.out.println(player.gety());
        //System.out.println(player.getx());
        //set background
        bg.setPosition(player.getx()+20, player.gety() );

        //attack enemies
        player.checkAttack(enemies);


        //update all enemies
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();

            if(e.gety() > GamePanel.HEIGHT - 20){
                e.isDead();
            }

            if (e.isDead()) {
                explodeSound();
                enemies.remove(i);
                i--;
                explosions.add(new Explosion(e.getx(), e.gety()));
            }


            //update explosions
            for (int j = 0; j < explosions.size(); j++) {
                explosions.get(j).update();
                if (explosions.get(j).shouldRemove()) {
                    explosions.remove(j);
                    j--;
                }
            }

        }

        if(player.getHealth() == 0){
            deadSound();
            gsm.setState(GameStateManager.LOSESCREEN);
        }

        if(b.isDead()){
            if(count == 0){
                b2 = new Boss2(tileMap);
                b2.setPosition(2900,45);
                b2.setLeft();
                enemies.add(b2);
                count+= 1;
            }
            else if(count == 100){
                b3 = new Boss2(tileMap);
                b3.setPosition(2900,45);
                enemies.add(b3);
                count+= 1;
            }
            else if(count == 200){
                b4 = new Boss2(tileMap);
                b4.setPosition(2900,45);
                b4.setLeft();
                enemies.add(b4);
                count+= 1;
            }
            else{
                count+= 1;
            }


        }

        if (player.getx() == 3170 && b.isDead()==true && b2.isDead()==true && b3.isDead()==true && b4.isDead()==true) {
            gsm.setState(GameStateManager.LEVEL2STATE);
        }

    }

    public static void explodeSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream;
        Clip clip;
        audioInputStream = AudioSystem.getAudioInputStream(new File("Explosion.aifc").getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public void draw(Graphics2D g){

        //draw bg
        bg.draw(g);

        //draw tilemap
        tileMap.draw(g);

        //draw player
        player.draw(g);

        //draw enemies
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).draw(g);
        }

        //draw explosions
        for(int i = 0; i < explosions.size(); i++){
            explosions.get(i).setMapPosition((int) tileMap.getx(), (int) tileMap.gety());
            explosions.get(i).draw(g);
        }

        //draw hud
        hud.draw(g);

    }
    public void keyPressed(int k) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if(k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setUp(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
        if(k == KeyEvent.VK_UP ) player.setJumping(true);
        if(k == KeyEvent.VK_SPACE) player.setGliding(true);
        if(k == KeyEvent.VK_X) player.setSlashing();
        if(k == KeyEvent.VK_Z) player.setFiring();
        //System.out.println("x= "+player.getx());
        //System.out.println("y= "+player.gety());
    }
    public void keyReleased(int k){
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setUp(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
        if(k == KeyEvent.VK_UP) player.setJumping(false);
        if(k == KeyEvent.VK_SPACE) player.setGliding(false);
    }

}
