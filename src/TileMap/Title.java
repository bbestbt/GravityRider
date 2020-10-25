package TileMap;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Title {

    private BufferedImage image;
    private double x,y;


    public Title(String s) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }







    public void draw(Graphics2D g) {



        g.drawImage(image,GamePanel.WIDTH/10,-GamePanel.HEIGHT/4, null);




    }

}

