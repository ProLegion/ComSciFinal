/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author canto5684
 */
public class CompareScore extends JPanel {
    
    private JLabel title;
    private static final Color LIME = new Color(153, 255, 51);
    
    public CompareScore() {
        
        title = new JLabel("Scoreboard");
        
        title.setFont(new Font("Century Gothic", Font.PLAIN,12));
        title.setForeground(LIME);
        add(title);
    }

}
