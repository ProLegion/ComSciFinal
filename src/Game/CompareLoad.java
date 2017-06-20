package Game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author IVXX_LeGioN
 */
public class CompareLoad extends JPanel {
    
   private  int[] scores = new int[5];
    
    private JLabel title, l1, l2, l3;
    private JButton b1, b2, b3, compare;
    private File pat1, pat2, pat3;
    private int score1, score2, score3;
    private static final Color LIME = new Color(153, 255, 51);
    private static final Color STONE = new Color(128, 128, 128);
    private GridIO io = new GridIO();

    public CompareLoad() {
        setLayout(new GridLayout(0, 1, 10, 10));

        title = new JLabel("Menu");
        title.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        title.setForeground(LIME);
        add(title);

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
    
    public int[] getScoreTable(){
        return scores;
    }

    class BtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String whichBtn = e.getActionCommand();
            int[] scores = new int[5];
            switch (whichBtn) {
                case "refresh":
                    scores = io.readScore();
                    
                    for (int i = 0; i < scores.length; i++) {
                        System.out.println(scores[i]);
                    }
                    
                    break;
            }

        }
    }
}
