package Main;

import javax.swing.JFrame;
import java.io.*;

public class Game {

    public static void main(String[] args)
    {
        System.out.println("");
        JFrame frame = new JFrame("App");
        frame.setContentPane(new GamePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }

}