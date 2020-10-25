package Entity;

import java.applet.*;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;





public class FireBall extends MapObject {

    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;


    public  FireBall(TileMap tm, boolean right){

        super(tm);

        facingRight = right;

        moveSpeed = 3.8;
        if(right) dx = moveSpeed;
        else dx = -moveSpeed;

        width = 30;
        height = 30;
        cwidth = 14;
        cheight = 14;

        //load Sprites
        try{

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/fireball2.gif"));

            sprites = new BufferedImage[4];
            for(int i = 0; i < sprites.length; i++){
                if(i == 0){
                    shotSound();
                }
                sprites[i] = spritesheet.getSubimage(i*width, 0, width, height);
            }

            hitSprites = new BufferedImage[3];
            for(int i = 0; i < hitSprites.length; i++){
                hitSprites[i] = spritesheet.getSubimage(i*width,height,width,height);
            }

            animation = new Animation();
            animation.setFrames(sprites);
            animation.setDelay(70);


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public void setHit(){
        if(hit) return;
        hit = true;
        animation.setFrames(hitSprites);
        animation.setDelay(70);
        dx = 0;
    }

    public boolean shouldRomove(){ return remove; }

    public void update(){

        checkTileMapCollision();
        setPosition(xtemp, ytemp);


        if(dx == 0 && !hit){
            setHit();
        }

        animation.update();
        if(hit && animation.hasPlayedOnce()){
            remove = true;
        }

    }

    public void draw(Graphics2D g){

        setMapPosition();

        super.draw(g);



    }

    @Override
    public void setLeft() {

    }

    public static void shotSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream;
        Clip clip;
        audioInputStream = AudioSystem.getAudioInputStream(new File("Shot.aifc").getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }





}
