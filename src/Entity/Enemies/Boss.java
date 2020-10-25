package Entity.Enemies;


import Entity.Animation;
import Entity.Enemy;
import TileMap.Tile;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss extends Enemy {

    private BufferedImage[] sprites;

    public boolean level2;
    public boolean level3;
    public boolean level4;

    public void setlevel2(){
        level2 = true;
        level3 = false;
        level4 = false;
    }

    public void setlevel3(){
        level3 = true;
        level2 = false;
        level4 = false;
    }

    public void setlevel4(){
        level3 = false;
        level2 = false;
        level4 = true;
    }

    public Boss(TileMap tm){

        super(tm);

        moveSpeed = 1;
        maxSpeed = 1;
        fallSpeed = 1;
        maxFallSpeed = 5.0;

        width = 78;
        height = 100;
        //hitbox
        cwidth = 45;
        cheight = 45;


        if(level2){
            health = maxHealth = 30;  //30
        }
        else if(level3){
            health = maxHealth = 50;  //50
        }
        else if(level4){
            health = maxHealth = 60;  //60
        }
        else{
            health = maxHealth = 25;  //25
        }
        damage = 2;


        //load sprites

        try{

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/Boss1.1.png"));

            sprites = new BufferedImage[5];
            for(int i = 0; i < sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i * width, 0,width,height);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300);

        right = true;
        facingRight = true;

    }
   // public int getHealth(){ return  health; }

    private void getNextPosition(){

        //movement
        if(left){
            dx -= moveSpeed;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }
        else if(right){
            dx += moveSpeed;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        }

        //falling
        if(falling){
            dy += fallSpeed;
        }

    }

    public void update(){

        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp,ytemp);

        //check flinching
        if(flinching){
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed > 400){
                flinching = false;
            }
        }

        //if it hits a wall, go other direction
        if(right && dx==0){
            right = false;
            left = true;
            facingRight = false;
        }
        else if (left && dx==0){
            right = true;
            left = false;
            facingRight = true;
        }

        //update animation
        animation.update();

    }

    public void draw(Graphics2D g){

        //if(notOnScreen()) return;

        setMapPosition();

        super.draw(g);


    }


}