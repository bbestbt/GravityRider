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

public class Level4State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;
    private Boss b1;
    private Boss b2;
    private Boss b3;
    private Boss b4;
    private Boss b5;
    private Boss2 b6;
    private Boss2 b7;
    private Boss2 b8;
    private Boss2 b9;
    private Boss2 b10;
    private Boss2 b11;
    private Boss2 b12;
    private Boss2 b13;
    private Boss2 b14;
    private Boss2 b15;
    private Boss2 b16;
    private Boss2 b17;
    private Boss2 b18;
    private Boss2 b19;
    private Boss2 b20;
    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> enemies2;
    private ArrayList<Enemy> enemies3;
    private ArrayList<Explosion> explosions;

    private HUD hud;
    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private int count5 = 0;


    public Level4State(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public void init(){

        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset2.gif");
        tileMap.loadMap("/Maps/level4-4.map");
        tileMap.setPosition(0,0);
        tileMap.setTween(0.07);



        bg = new Background("/Backgrounds/DarkCity2.png", 0.1);

        player = new Player(tileMap);
        player.setPosition(100, 100);

        populateEnemies();
        populateEnemies2();
        populateEnemies3();



        explosions = new ArrayList<Explosion>();

        hud = new HUD(player);

        player.setlevel4();


    }

    private void populateEnemies(){
        enemies = new ArrayList<Enemy>();
        Mon1 s;
        Point[] points = new Point[]{
                new Point(150, 200),
                new Point(720, 510),new Point(750, 510),new Point(780, 510),new Point(810, 510),new Point(840, 200),
                new Point(750, 660),new Point(810, 660),new Point(870, 660),new Point(930, 660),new Point(990, 660),

                new Point(1530, 120),new Point(1560, 120),new Point(1590, 120),new Point(1620, 120),new Point(1650, 120),new Point(1680, 120),new Point(1710, 120),new Point(1740, 120),


                //new Point(860, 200), new Point(1525, 200), new Point(1680, 200), new Point(1800, 200)

        };
        for(int i = 0; i < points.length; i++){
            s = new Mon1(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);
        }
        // Boss บน;
        b1 = new Boss(tileMap);
        b1.setPosition(3000,100);
        enemies.add(b1);



        //Boss กลาง
        b2 = new Boss(tileMap);
        b2.setPosition(2880,420);
        enemies.add(b2);
        b3 = new Boss(tileMap);
        b3.setPosition(3060,420);
        enemies.add(b3);
        b4 = new Boss(tileMap);
        b4.setPosition(2730,660);
        enemies.add(b4);
        b5 = new Boss(tileMap);
        b5.setPosition(2970,660);
        enemies.add(b5);

        b1.setlevel4();
        b2.setlevel4();
        b3.setlevel4();
        b4.setlevel4();
        b5.setlevel4();




        b6 = new Boss2(tileMap);
        b6.setPosition(60,420);
        enemies.add(b6);
        b7 = new Boss2(tileMap);
        b7.setPosition(60,420);
        enemies.add(b7);
        b8 = new Boss2(tileMap);
        b8.setPosition(60,420);
        enemies.add(b8);
        b9 = new Boss2(tileMap);
        b9.setPosition(60,420);
        enemies.add(b9);
        b10 = new Boss2(tileMap);
        b10.setPosition(60,420);
        enemies.add(b10);
        b11 = new Boss2(tileMap);
        b11.setPosition(60,420);
        enemies.add(b11);
        b12 = new Boss2(tileMap);
        b12.setPosition(60,420);
        enemies.add(b12);
        b13 = new Boss2(tileMap);
        b13.setPosition(60,420);
        enemies.add(b13);
        b14 = new Boss2(tileMap);
        b14.setPosition(60,420);
        enemies.add(b14);
        b15 = new Boss2(tileMap);
        b15.setPosition(60,420);
        enemies.add(b15);
        b16 = new Boss2(tileMap);
        b16.setPosition(60,420);
        enemies.add(b16);
        b17 = new Boss2(tileMap);
        b17.setPosition(60,420);
        enemies.add(b17);
        b18 = new Boss2(tileMap);
        b18.setPosition(60,420);
        enemies.add(b18);
        b19 = new Boss2(tileMap);
        b19.setPosition(60,420);
        enemies.add(b19);
        b20 = new Boss2(tileMap);
        b20.setPosition(60,420);
        enemies.add(b20);




    }

    private void populateEnemies2(){
        enemies2 = new ArrayList<Enemy>();
        Mon2 s2;
        Point[] points = new Point[]{
                //ล่างสุด ประตูวาปซ้าย
                new Point(120, 710),new Point(120, 680),new Point(150, 710),new Point(150, 680),new Point(180, 710),new Point(180, 680),
                //ทางลงประตูวาปซ้าย
                new Point(220, 450),new Point(220, 480),new Point(220, 510),new Point(220, 540),new Point(290, 450),new Point(290, 480),new Point(290, 510),new Point(290, 540),
                //ทางลงประตูวาปกลาง
                new Point(380, 450),new Point(380, 480),new Point(380, 510),new Point(380, 540),new Point(430, 450),new Point(430, 480),new Point(430, 510),new Point(430, 540),
                //
                new Point(690, 380),new Point(690, 410),new Point(690, 440), new Point(810, 420),new Point(900, 360),new Point(810, 300),new Point(1050, 270),
                new Point(1260, 630),new Point(1320, 570),new Point(1500, 630),
                new Point(1260, 90),new Point(1230, 90),new Point(1200, 120),new Point(1230, 120),new Point(1350, 120),new Point(1350, 150),new Point(1410, 120),new Point(1410, 150),

                //บินกลาง
                new Point(1530, 120),new Point(1560, 120),new Point(1590, 120),new Point(1620, 120),new Point(1650, 120),new Point(1680, 120),new Point(1710, 120),new Point(1740, 120),

                new Point(2500, 130),new Point(2500, 150),new Point(2600, 150),new Point(2600, 130),new Point(2600, 70),new Point(2600, 40),new Point(2800, 140)
        };
        for(int i = 0; i < points.length; i++){
            s2 = new Mon2(tileMap);
            s2.setPosition(points[i].x, points[i].y);
            enemies2.add(s2);
        }

    }

    private void populateEnemies3(){
        enemies3 = new ArrayList<Enemy>();
        Mon3 s;
        Point[] points = new Point[]{
                new Point(195, 170),new Point(800, 200),new Point(850, 200),new Point(1000, 200),new Point(1700, 200),
                new Point(2400, 200),new Point(150, 710),new Point(950, 680),new Point(750, 530),new Point(880, 500),
                new Point(2706, 470),new Point(3056, 470),new Point(1793, 680)
                //new Point(860, 200), new Point(1525, 200), new Point(1680, 200), new Point(1800, 200)

        };
        for(int i = 0; i < points.length; i++){
            s = new Mon3(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies3.add(s);
        }


    }

    public void update() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        //update player
        player.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());

        /// 15 <
        if(player.gety() > 730){
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
        player.checkAttack(enemies3);


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

        for(int i = 0; i < enemies2.size(); i++) {
            Enemy e = enemies2.get(i);
            e.update();

            if(e.gety() > GamePanel.HEIGHT - 20){
                e.isDead();
            }

            if (e.isDead()) {
                explodeSound();
                enemies2.remove(i);
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

        for(int i = 0; i < enemies3.size(); i++) {
            Enemy e = enemies3.get(i);
            e.update();

            if(e.gety() > GamePanel.HEIGHT - 20){
                e.isDead();
            }

            if (e.isDead()) {
                explodeSound();
                enemies3.remove(i);
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

        //boss 1 // 1 6 7 8
        if(b1.isDead()){
            if(count1 == 0){
                b6.setPosition(2900,50);
                count1+= 1;
            }
            else if(count1 == 500){
                b7.setPosition(2900,50);
                count1+= 1;
            }
            else if(count1 == 1000){
                b8.setPosition(2900,50);
                count1+= 1;
            }
            else{
                count1+= 1;
            }
        }

        //boss 2 3 // 2 3 9 10 11 12 13 14
        if(b2.isDead()){
            if(count2 == 0){
                b9.setPosition(2420,260);
                count2+= 1;
            }
            else if(count2 == 500){
                b10.setPosition(2420,260);
                count2+= 1;
            }
            else if(count2 == 1000){
                b11.setPosition(2420,260);
                count2+= 1;
            }
            else{
                count2+= 1;
            }
        }
        if(b3.isDead()){
            if(count3 == 0){
                b12.setPosition(2420,260);
                count3+= 1;
            }
            else if(count3 == 500){
                b13.setPosition(2420,260);
                count3+= 1;
            }
            else if(count3 == 1000){
                b14.setPosition(2420,260);
                count3+= 1;
            }
            else{
                count3+= 1;
            }
        }

        //boss 4 5 // 4 5 15 16 17 18 19 20
        if(b4.isDead()){
            System.out.println("Boss 4");
            if(count4 == 0){
                b15.setPosition(3170,590);
                count4+= 1;
            }
            else if(count4 == 500){
                b16.setPosition(3170,590);
                count4+= 1;
            }
            else if(count4 == 1000){
                b17.setPosition(3170,590);
                count4+= 1;
            }
            else{
                count4+= 1;
            }
        }
        if(b5.isDead()){
            System.out.println("Boss 5");
            if(count5 == 0){
                b18.setPosition(3170,590);
                count5+= 1;
            }
            else if(count5 == 500){
                b19.setPosition(3170,590);
                count5+= 1;
            }
            else if(count5 == 1000){
                b20.setPosition(3170,590);
                count5+= 1;
            }
            else{
                count5+= 1;
            }
        }

        if((player.getx() < 60 && player.gety() > 690 && player.gety() < 750) || (player.getx() > 1080 && player.getx() < 1110 && player.gety() > 480 && player.gety() < 540)){
            player.setPosition(680, 650);
        }


        if(player.getx() < 1710 && player.getx() > 1680 && player.gety() > 300 && player.gety() < 360){
            player.setPosition(2330, 470);
        }

        if(player.getx() == 3170 && player.gety() > 660 && player.gety() < 720 && b4.isDead() && b5.isDead()
                && b15.isDead() && b16.isDead() && b17.isDead() && b18.isDead() && b19.isDead() && b20.isDead()){
            player.setPosition(100, 190);
        }

        if(player.getx() == 3170 && player.gety() > 270 && player.gety() < 330 && b2.isDead() && b3.isDead()
                && b9.isDead() && b10.isDead() && b11.isDead() && b12.isDead() && b13.isDead() && b14.isDead()){
            player.setPosition(100, 190);
        }
        //boss 1 // 1 6 7 8
        //boss 2 3 // 2 3 9 10 11 12 13 14
        //boss 4 5 // 4 5 15 16 17 18 19 20

        if (player.getx() == 3170 && player.gety() > 150 && player.gety() < 210
                && b1.isDead()==true && b2.isDead()==true && b3.isDead()==true && b4.isDead()==true && b5.isDead()==true
                && b6.isDead()==true && b7.isDead()==true && b8.isDead()==true && b9.isDead()==true && b10.isDead()==true
                && b11.isDead()==true && b12.isDead()==true && b13.isDead()==true && b14.isDead()==true && b15.isDead()==true
                && b16.isDead()==true && b17.isDead()==true && b18.isDead()==true && b19.isDead()==true && b20.isDead()==true) {
            gsm.setState(GameStateManager.WINSCREEN);
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

        for(int i = 0; i < enemies3.size(); i++){
            enemies3.get(i).draw(g);
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
