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
public class CompareWindow extends JPanel {

    private int[][] pattern1, pattern2, pattern3;

    public CompareWindow() {
        pattern1 = new int[20][20];
        pattern2 = new int[20][20];
        pattern3 = new int[20][20];
    }

    public void addArrayOne(int[][] pattern) {
        for (int row = 0; row < pattern1.length; row++) {
            for (int col = 0; col < pattern1.length; col++) {
                pattern1[row][col] = pattern[row][col];
            }
        }
    }

    public void addArrayTwo(int[][] pattern) {
        for (int row = 0; row < pattern2.length; row++) {
            for (int col = 0; col < pattern2.length; col++) {
                pattern2[row][col] = pattern[row][col];
            }
        }
    }

    public void addArrayThree(int[][] pattern) {
        for (int row = 0; row < pattern3.length; row++) {
            for (int col = 0; col < pattern3.length; col++) {
                pattern3[row][col] = pattern[row][col];
            }
        }
    }
}
