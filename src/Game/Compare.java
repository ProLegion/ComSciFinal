package Game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author IVXX_LeGioN
 */
public class Compare extends JPanel {

    private JLabel title, l1, l2, l3;
    private JButton b1, b2, b3;
    private File pat1, pat2, pat3;
    private int score1, score2, score3;
    private static final Color LIME = new Color(153, 255, 51);
    private static final Color STONE = new Color(128, 128, 128);

    public Compare() {
        setLayout(new GridLayout(0, 1, 10, 10));

        title = new JLabel("Compare 3 Files ");
        title.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        title.setForeground(LIME);
        add(title);

        l1 = new JLabel("Pattern One");
        l1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        l1.setForeground(LIME);
        add(l1);

        b1 = new JButton("Load");
        b1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        b1.setBackground(STONE);
        b1.setForeground(LIME);
        b1.setBorder(null);
        b1.setFocusPainted(false);
        b1.addActionListener(new BtnListener());
        b1.setActionCommand("load1");
        add(b1);

        l2 = new JLabel("Pattern Two");
        l2.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        l2.setForeground(LIME);
        add(l2);

        b2 = new JButton("Load");
        b2.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        b2.setBackground(STONE);
        b2.setForeground(LIME);
        b2.setBorder(null);
        b2.setFocusPainted(false);
        b2.addActionListener(new BtnListener());
        b2.setActionCommand("load2");
        add(b2);

        l3 = new JLabel("Pattern Three");
        l3.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        l3.setForeground(LIME);
        add(l3);

        b3 = new JButton("Load");
        b3.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        b3.setBackground(STONE);
        b3.setForeground(LIME);
        b3.setBorder(null);
        b3.setFocusPainted(false);
        b3.addActionListener(new BtnListener());
        b3.setActionCommand("load3");
        add(b3);
    }

}

class BtnListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String whichBtn = e.getActionCommand();
        Loader loader = new Loader();
        switch (whichBtn) {
            case "Load 1":
                loader.loadOne();
                
                break;
        }
    }
}

class Loader {

    private int[][] pattern1;
    private int[][] pattern2;
    private int[][] pattern3;
    private final JFileChooser fc = new JFileChooser();

    public void loadOne() {
            File pattern;
            int returnVal;
            
            returnVal = fc.showOpenDialog(fc);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            pattern = fc.getSelectedFile();
            FileReader inStream;
            Scanner filein;
            int cell;
            int[][] patternArray = new int[20][20];

            try {

                inStream = new FileReader(pattern); //set up stream
                filein = new Scanner(inStream); //set up reader

                for (int row = 0; row < patternArray.length; row++) {
                    for (int col = 0; col < patternArray.length; col++) {
                        patternArray[row][col] = filein.nextInt();
                    }
                }

                filein.close();
                inStream.close();
                System.out.println("File Loaded Sucessfully");

            } catch (FileNotFoundException e) {
                System.out.println("File does not exist or could not be found.");
                System.err.println("FileNotFoundException:" + e.getMessage());
            } catch (IOException e) {
                System.out.println("Problem reading file.");
                System.err.println("IOException" + e.getMessage());
            }

            for (int row = 0; row < 0; row++) {
                for (int col = 0; col < 0; col++) {

                }

            }

        } else if (returnVal == JFileChooser.CANCEL_OPTION) {

        } else if (returnVal == JFileChooser.ERROR_OPTION) {

        }
    }
}
