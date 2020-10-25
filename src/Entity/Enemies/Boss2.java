package Entity.Enemies;


import Entity.Animation;
import Entity.Enemy;
import TileMap.Tile;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss2 extends Enemy {

    private BufferedImage[] sprites;

    public Boss2(TileMap tm){

        super(tm);

        moveSpeed = 2;
        maxSpeed = 3;
        fallSpeed = 1;
        maxFallSpeed = 5.0;

        width = 30;
        height = 30;
        //hitbox
        cwidth = 30;
        cheight = 20;

        health = maxHealth = 15; //15
        damage = 1;

        //load sprites

        try{

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/Boss1.2.png"));

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

    public void setleft(){
        right = false;
        left = true;
        facingRight = false;
    }


}