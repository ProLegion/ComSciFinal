/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author canto5684
 */
public class CompareScore extends JPanel {
    
    private int[] scores;
    private String[] scoreStr = new String[5];
    private JButton b1;
    private JLabel title, score1, score2, score3, score4, score5;
    private static final Color LIME = new Color(153, 255, 51);private static final Color STONE = new Color(128, 128, 128);private GridIO io = new GridIO();
    
    
    
    public CompareScore() {
        setLayout(new GridLayout(0, 1, 10, 10));
        
        title = new JLabel("Scoreboard");
        title.setFont(new Font("Century Gothic", Font.PLAIN,12));
        title.setForeground(LIME);
        add(title);
        
        score1 = new JLabel("1. ----------");
        score1.setFont(new Font("Century Gothic", Font.PLAIN,12));
        score1.setForeground(LIME);
        add(score1);
        
        score2 = new JLabel("2. ----------");
        score2.setFont(new Font("Century Gothic", Font.PLAIN,12));
        score2.setForeground(LIME);
        add(score2);
        
        score3 = new JLabel("3. ----------");
        score3.setFont(new Font("Century Gothic", Font.PLAIN,12));
        score3.setForeground(LIME);
        add(score3);
        
        score4 = new JLabel("4. ----------");
        score4.setFont(new Font("Century Gothic", Font.PLAIN,12));
        score4.setForeground(LIME);
        add(score4);
        
        score5 = new JLabel("5. ----------");
        score5.setFont(new Font("Century Gothic", Font.PLAIN,12));
        score5.setForeground(LIME);
        add(score5);
        
        b1 = new JButton("Refresh Scores");
        b1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        b1.setBackground(STONE);
        b1.setForeground(LIME);
        b1.setBorder(null);
        b1.setFocusPainted(false);
        b1.addActionListener(new BtnListener());
        b1.setActionCommand("refresh");
        add(b1);
    }
        

    class BtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String whichBtn = e.getActionCommand();
            int[] scores = new int[5];
            switch (whichBtn) {
                case "refresh":
                    scores = io.readScore();
                    score1.setText("1. "+ scores[0]);
                    score2.setText("2. " + scores[1]);
                    score3.setText("3. " + scores[2]);
                    score4.setText("4. " + scores[3]);
                    score5.setText("5. " + scores[4]);
                    for (int i = 0; i < scores.length; i++) {
                        System.out.println(scores[i]);
                    }
                    
                    break;
            }

        }
    }
}
