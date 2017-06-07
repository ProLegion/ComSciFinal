package Game;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author IVXX_LeGioN
 */
public class GUI {
    private static void BuildGUI(){
        Life life = new Life(20);
        JPanel panel = new JPanel();
        Grid lifeGrid = new Grid(life);
        Control gameControl = new Control(lifeGrid);
        Color DARKGREY = new Color(32, 32, 32);
        
        JFrame frame = new JFrame("Game O' Life");
        ImageIcon img = new ImageIcon("ico_test.png");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        panel.setBackground(DARKGREY);
        panel.add(gameControl);
        panel.add(lifeGrid);
        
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);        
    }
    
    public static void run(){
        BuildGUI();
    }
    
    public static void main(String[] args) {
        run();
    }    
}
