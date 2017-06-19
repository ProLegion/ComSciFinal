/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author canto5684
 */
public class GridIO {

    public void writeScore(int score) {
        File scoreOut = new File("SCORES.txt");
        FileWriter out;
        BufferedWriter writeFile;
        String output = "";
        System.out.println("Passed Score = " + score);
        output += score + " ";
        try {

            out = new FileWriter(scoreOut, false);
            writeFile = new BufferedWriter(out);

            writeFile.write(output);
            System.out.println("scored");
            writeFile.newLine();

            writeFile.close();
            out.close();

            System.out.println("Data written to file.");
        } catch (IOException e) {
            System.out.println("I/O Error!!");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public int[] readScore() {
        File inFile = new File("SCORES.txt");
        int[] scores = new int[5];
        FileReader inStream;
        Scanner filein;


        try {

            inStream = new FileReader(inFile); //set up stream
            filein = new Scanner(inStream); //set up reader

            for (int s = 0; s < scores.length; s++) {
                if (filein.hasNextInt()) {
                    if (scores[s] == 0) {
                        scores[s] = filein.nextInt();
                    }else{
                        System.out.println("List Full");
                    }
                } else {
                    scores[s] = 0;
                }
            }

            filein.close();
            inStream.close();
            return scores;
        } catch (IOException e) {
            System.out.println("I/O ERROR");
            System.out.println(e.getMessage() + "\n");
            e.printStackTrace();
            for (int arr = 0; arr < scores.length; arr++) {
                scores[arr] = 0;
            }
            return scores;
        }
    }
}
