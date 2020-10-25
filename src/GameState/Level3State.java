package GameState;



import java.io.File;
import java.io.IOException;
import java.util.*;
import Entity.*;
import Entity.Enemies.*;
import Main.GamePanel;
import TileMap.*;


import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Entity.Player.deadSound;

public class Level3State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;

    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> enemies2;
    private ArrayList<Explosion> explosions;

    private Boss b1;
    private Boss b2;
    private Boss2 b3;
    private Boss2 b4;
    private Boss2 b5;
    private Boss2 b6;
    private Boss2 b7;
    private Boss2 b8;
    private Boss2 b9;
    private Boss2 b10;
    private Boss2 b11;
    private Boss2 b12;
    private Boss2 b13;
    private Boss2 b14;

    private int count1 = 0;
    private int count2 = 0;

    private HUD hud;

    public Level3State(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public void init(){

        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset2.gif");
        tileMap.loadMap("/Maps/level3-3.map");
        tileMap.setPosition(0,0);
        tileMap.setTween(0.07);

        bg = new Background("/Backgrounds/DarkCity.png", 0.1);



        player = new Player(tileMap);
        player.setPosition(100, 100);

        populateEnemies();
        populateEnemies2();

        explosions = new ArrayList<Explosion>();

        hud = new HUD(player);

        player.setlevel3();

    }

    private void populateEnemies(){
        enemies = new ArrayList<Enemy>();
        Mon1 s;
        Point[] points = new Point[]{

                new Point(720, 200),new Point(860, 200), new Point(960, 200), new Point(1500, 200), new Point(1580, 200),new Point(1650, 200), new Point(2370,200),new Point(2400, 200)

                //

        };
        for(int i = 0; i < points.length; i++){
            s = new Mon1(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);
        }
        b1 = new Boss(tileMap);
        b1.setPosition(3100,100);
        enemies.add(b1);
        b1.setlevel3();


    }

    private void populateEnemies2(){
        enemies2 = new ArrayList<Enemy>();
        Mon2 s2;
        Point[] points = new Point[]{
                new Point(830, 100),new Point(830, 70),new Point(915, 75),new Point(915, 40),new Point(1010, 100),new Point(1010, 50),
                new Point(2160, 70),new Point(1218, 75),new Point(1400, 170),new Point(1400, 140),new Point(1400, 70),new Point(1350, 135),
                new Point(1570, 130),new Point(1610, 130),new Point(1650, 140),new Point(1750, 75),new Point(1800, 45),
                new Point(2400, 180),new Point(2400, 80),new Point(2400, 110),new Point(2500, 110),new Point(2500, 130),new Point(2500, 150),new Point(2600, 150),new Point(2600, 130),new Point(2600, 70),new Point(2600, 40),new Point(2800, 140)
        };
        for(int i = 0; i < points.length; i++){
            s2 = new Mon2(tileMap);
            s2.setPosition(points[i].x, points[i].y);
            enemies2.add(s2);
        }

        b2 = new Boss(tileMap);
        b2.setPosition(2900,100);
        enemies2.add(b2);
        b2.setlevel3();

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
        player.checkAttack(enemies2);

        System.out.println("x " + player.getx());
        System.out.println("y " + player.gety());


        //update all enemies
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
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
            deadSound();
            gsm.setState(GameStateManager.LOSESCREEN);
        }

        if(b1.isDead()){
            if(count1 == 0){
                b3 = new Boss2(tileMap);
                b3.setPosition(3170,190);
                enemies.add(b3);
                count1+= 1;
            }
            else if(count1 == 500){
                b4 = new Boss2(tileMap);
                b4.setPosition(3170,190);
                enemies.add(b4);
                count1+= 1;
            }
            else if(count1 == 1000){
                b5 = new Boss2(tileMap);
                b5.setPosition(3170,190);
                enemies.add(b5);
                count1+= 1;
            }
            else if(count1 == 1500){
                b6 = new Boss2(tileMap);
                b6.setPosition(3170,190);
                enemies.add(b6);
                count1+= 1;
            }
            else if(count1 == 2000){
                b7 = new Boss2(tileMap);
                b7.setPosition(3170,190);
                enemies.add(b7);
                count1+= 1;
            }
            else if(count1 == 2500){
                b8 = new Boss2(tileMap);
                b8.setPosition(3170,190);
                enemies.add(b8);
                count1+= 1;
            }
            else{
                count1+= 1;
            }


        }

        if(b2.isDead()) {
            if (count2 == 0) {
                b9 = new Boss2(tileMap);
                b9.setPosition(3170, 190);
                enemies.add(b9);
                count2 += 1;
            } else if (count2 == 500) {
                b10 = new Boss2(tileMap);
                b10.setPosition(3170, 190);
                enemies.add(b10);
                count2 += 1;
            } else if (count2 == 1000) {
                b11 = new Boss2(tileMap);
                b11.setPosition(3170, 190);
                enemies.add(b11);
                count2 += 1;
            } else if (count2 == 1500) {
                b12 = new Boss2(tileMap);
                b12.setPosition(3170, 190);
                enemies.add(b12);
                count2 += 1;
            } else if (count2 == 2000) {
                b13 = new Boss2(tileMap);
                b13.setPosition(3170, 190);
                enemies.add(b13);
                count2 += 1;
            } else if (count2 == 2500) {
                b14 = new Boss2(tileMap);
                b14.setPosition(3170, 190);
                enemies.add(b14);
                count2 += 1;
            } else {
                count2 += 1;
            }
        }

        if (player.getx() == 3170 && player.gety() > 150 && player.gety() < 200 && b1.isDead()==true && b2.isDead()==true && b3.isDead()==true && b4.isDead()==true && b5.isDead()==true
                && b6.isDead()==true && b7.isDead()==true  && b8.isDead()==true && b9.isDead()==true && b10.isDead()==true && b11.isDead()==true && b12.isDead()==true && b13.isDead()==true
                && b14.isDead()==true) {
            gsm.setState(GameStateManager.LEVEL4STATE);
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
