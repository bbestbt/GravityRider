package GameState;
import java.io.File;
import java.io.IOException;
import java.util.*;
import Entity.*;
import Entity.Enemies.*;
import Main.GamePanel;
import TileMap.*;
import Entity.Player;


import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Level2State extends GameState  {
    private TileMap tileMap;
    private Background bg;

    private Player player;
    private Boss b1;
    private Boss b2;
    private Boss2 b3;
    private Boss2 b4;
    private Boss2 b5;
    private Boss2 b6;
    private Boss2 b7;
    private Boss2 b8;


    private int count = 0;
    private int count2 = 0;

    private ArrayList<Enemy> enemies1;
    private ArrayList<Enemy> enemies2;
    private ArrayList<Enemy> enemies3;
    private ArrayList<Explosion> explosions;

    private HUD hud;

    public Level2State(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public void init(){

        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset2.gif");
        tileMap.loadMap("/Maps/level2-2.map");
        tileMap.setPosition(0,0);
        tileMap.setTween(0.07);

        bg = new Background("/Backgrounds/DarkCity.png", 0.1);



        player = new Player(tileMap);
        player.setPosition(1500, 100);

        populateEnemies1();
        populateEnemies2();

        explosions = new ArrayList<Explosion>();

        hud = new HUD(player);

        player.setlevel2();

    }

    private void populateEnemies1(){
        enemies1 = new ArrayList<Enemy>();
        Mon1 s;
        Point[] points = new Point[]{
                new Point(860, 200), new Point(960, 200), new Point(1500, 200), new Point(1500, 200), new Point(2370,200), new Point(3000,200)
                //

        };
        for(int i = 0; i < points.length; i++){
            s = new Mon1(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies1.add(s);
        }
        // Boss b;
        b1 = new Boss(tileMap);
        b1.setPosition(3000,100);
        enemies1.add(b1);

        b1.setlevel2();


    }

    private void populateEnemies2(){
        enemies2 = new ArrayList<Enemy>();
        Mon2 s2;
        Point[] points = new Point[]{
                new Point(300, 150),new Point(1218, 80),new Point(1400, 170),new Point(1400, 140),new Point(1400, 110),new Point(1350, 135),
                new Point(1570, 130),new Point(1610, 130),new Point(1650, 140),new Point(1750, 125),new Point(1800, 130),
                new Point(2400, 180),new Point(2400, 80),new Point(2400, 110),new Point(2500, 110),new Point(2500, 130),new Point(2500, 150),new Point(2600, 150),new Point(2600, 130),new Point(2600, 70),new Point(2600, 40),new Point(2800, 140)
        };
        for(int i = 0; i < points.length; i++){
            s2 = new Mon2(tileMap);
            s2.setPosition(points[i].x, points[i].y);
            enemies2.add(s2);
        }

        b2 = new Boss(tileMap);
        b2.setPosition(3100,100);
        enemies2.add(b2);
        b2.setlevel2();

    }

    public void update() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        //update player
        player.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());

        /// 12 <
        if(player.gety() > GamePanel.HEIGHT - 12){

            gsm.setState(GameStateManager.LOSESCREEN);
        }

        System.out.println("x " + player.getx());
        System.out.println("y " + player.gety());


        //System.out.println(player.gety());

        //set background
        bg.setPosition(player.getx()+20, player.gety() );

        //attack enemies
        player.checkAttack(enemies1);
        player.checkAttack(enemies2);


        //update all enemies
        for(int i = 0; i < enemies1.size(); i++) {
            Enemy e1 = enemies1.get(i);
            e1.update();

            if(e1.gety() > GamePanel.HEIGHT - 20){
                e1.isDead();
            }

            if (e1.isDead()) {
                explodeSound();
                enemies1.remove(i);
                i--;
                explosions.add(new Explosion(e1.getx(), e1.gety()));
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

        for(int i = 0; i < enemies2.size(); i++) {
            Enemy e2 = enemies2.get(i);
            e2.update();

            if(e2.gety() > GamePanel.HEIGHT - 20){
                e2.isDead();
            }

            if (e2.isDead()) {
                explodeSound();
                enemies2.remove(i);
                i--;
                explosions.add(new Explosion(e2.getx(), e2.gety()));
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
            gsm.setState(GameStateManager.LOSESCREEN);
        }

        if(b1.isDead()){
            if(count == 0){
                b3 = new Boss2(tileMap);
                b3.setPosition(2900,45);
                enemies1.add(b3);
                count+= 1;
            }
            else if(count == 500){
                b4 = new Boss2(tileMap);
                b4.setPosition(2900,45);
                enemies1.add(b4);
                count+= 1;
            }
            else if(count == 1000){
                b5 = new Boss2(tileMap);
                b5.setPosition(2900,45);
                enemies1.add(b5);
                count+= 1;
            }
            else{
                count+= 1;
            }


        }

        if(b2.isDead()){
            if(count2 == 0){
                b6 = new Boss2(tileMap);
                b6.setPosition(2900,45);
                enemies1.add(b6);
                count2+= 1;
            }
            else if(count2 == 500){
                b7 = new Boss2(tileMap);
                b7.setPosition(2900,45);
                enemies1.add(b7);
                count2+= 1;
            }
            else if(count2 == 1000){
                b8 = new Boss2(tileMap);
                b8.setPosition(2900,45);
                enemies1.add(b8);
                count2+= 1;
            }
            else{
                count2+= 1;
            }


        }


        if (player.getx() == 3170 && player.gety() > 150 && player.gety() < 200 && b1.isDead()==true && b2.isDead()==true && b3.isDead()==true && b4.isDead()==true && b5.isDead()==true && b6.isDead()==true && b7.isDead()==true && b8.isDead()==true) {
            gsm.setState(GameStateManager.LEVEL3STATE);
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
        for(int i = 0; i < enemies1.size(); i++){
            enemies1.get(i).draw(g);
        }

        for(int i = 0; i < enemies2.size(); i++){
            enemies2.get(i).draw(g);
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
        //System.out.print("x="+player.getx());
        //System.out.println("y="+player.gety());

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
