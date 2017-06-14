/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.*;

/**
 *
 * @author canto5684
 */
public class WriteGrid {

    public void writeScore(DummyGrid game) {
        File scoreOut = new File("SCORES");
        try {
            /* Write Objects to the File*/
            FileOutputStream o = new FileOutputStream(scoreOut, true);
            ObjectOutputStream writeGrid = new ObjectOutputStream(o);

            writeGrid.writeObject(game);
            System.out.println("Score save to scoreboard");
            writeGrid.close();
            o.close();
        } catch (IOException e) {
            System.out.println("I/O Error!!");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
