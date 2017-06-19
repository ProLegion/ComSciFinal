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
    
    private int[] scores;
    private String[] scoreStr = new String[5];
    private JLabel title, score1, score2, score3, score4, score5;
    private static final Color LIME = new Color(153, 255, 51);
    
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
    }
    
    public void giveScoreTable(int[]scores ){
        this.scores = scores;
        refreshTable();
    }
    
    public void refreshTable(){
        String temp = "";
        for (int i = 0; i < scores.length; i++) {
           temp += scores[i];
            System.out.println("Temp = "+ temp);
            scoreStr[i] = temp;
            temp = "";
        }
        
        
            score1.setText("1. "+scores[1]);
        
    }

}
