package Entity.Enemies;

import Entity.Animation;
import Entity.Enemy;
import TileMap.Tile;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Mon2 extends Enemy {

    private BufferedImage[] sprites;

    public Mon2(TileMap tm){

        super(tm);

        width = 31;
        height = 30;
        cwidth = 21;
        cheight = 10;

        health = maxHealth = 10;
        damage = 2;

        //load sprites
        try{

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/Mon2.png"));

            sprites = new BufferedImage[5];
            for(int i = 0; i < sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i*width, 0,width,height);
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


    public void update(){

        //update position
        checkTileMapCollision();
        setPosition(xtemp,ytemp);

        //check flinching
        if(flinching){
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed > 400){
                flinching = false;
            }
        }


        //update animation
        animation.update();

    }

    public void draw(Graphics2D g){

        setMapPosition();

        super.draw(g);


    }


}
