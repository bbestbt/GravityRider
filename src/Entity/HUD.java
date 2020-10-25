package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HUD {

    private Player player;
    private BufferedImage HUD;
    private BufferedImage life;
    private Font font;

    public HUD(Player p){
        player = p;
        try{
            HUD = ImageIO.read(getClass().getResourceAsStream("/HUD/HUD 2.0.png"));



            //font = new Font("Arial", Font.PLAIN, 10);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g){
        int a = 96;
        if(player.getHealth() == 5) {
            life = HUD.getSubimage(0, 0, a, a);
        }
        if(player.getHealth() == 4) {
            life = HUD.getSubimage(a, 0, a,a);
        }
        if(player.getHealth() == 3) {
            life = HUD.getSubimage(a*2, 0, a, a);
        }
        if(player.getHealth() == 2) {
            life = HUD.getSubimage(a*3, 0, a, a);
        }
        if(player.getHealth() == 1) {
            life = HUD.getSubimage(a*4, 0, a, a);
        }

        g.drawImage(life, 0, -40, null);



        //g.setFont(font);
        //g.setColor(Color.WHITE);
        //g.drawString(player.getHealth() + "/" + player.getMaxHealth(), 30, 20);
        //g.drawString(player.getFire() / 100 + "/" + player.getMaxFire()/100, 30, 45);



    }



}
