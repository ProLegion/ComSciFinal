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
public class GridIO {

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

    public int[] readScore() {
        File inFile = new File("SCORES");
        int[] scores = new int[5];

        try {
            FileInputStream in = new FileInputStream(inFile);
            ObjectInputStream readFile = new ObjectInputStream(in);

            DummyGrid s1 = (DummyGrid) readFile.readObject();
            DummyGrid s2 = (DummyGrid) readFile.readObject();
            DummyGrid s3 = (DummyGrid) readFile.readObject();
            DummyGrid s4 = (DummyGrid) readFile.readObject();
            DummyGrid s5 = (DummyGrid) readFile.readObject();

            scores[0] = s1.getCount();
            scores[1] = s2.getCount();
            scores[2] = s3.getCount();
            scores[3] = s4.getCount();
            scores[4] = s5.getCount();

            readFile.close();
            in.close();
            return scores;
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
            System.out.println(e.getMessage() + "\n");
            for (int arr = 0; arr < scores.length; arr++) {
                scores[arr] = 0;
            }
            return scores;
        } catch (IOException e) {
            System.out.println("I/O ERROR");
            System.out.println(e.getMessage() + "\n");
            for (int arr = 0; arr < scores.length; arr++) {
                scores[arr] = 0;
            }
            return scores;
        } catch (ClassNotFoundException e) {
            System.out.println("COULD NOT READ CLASS");
            System.out.println(e.getMessage() + "\n");
            for (int arr = 0; arr < scores.length; arr++) {
                scores[arr] = 0;
            }
            return scores;
        }

    }
}
