
package BrickBlocksGame;

import java.awt.Color;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        
        
        obj.setBounds(0, 0, 1000, 1000);
        obj.setBackground(Color.GRAY);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);



    }
}
