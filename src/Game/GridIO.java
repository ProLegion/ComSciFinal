/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author canto5684
 */
public class GridIO {

    private final JFileChooser fc = new JFileChooser();
    /**
     * Writes the highest score out to a file in the form of integer.
     * @param score 
     */
    public void writeScore(int score) {
        File scoreOut = new File("SCORES.txt");
        FileWriter out;
        BufferedWriter writeFile;
        String output = "";
        System.out.println("Passed Score = " + score);
        output += score + " ";
        try {

            out = new FileWriter(scoreOut, true);
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
    
    /**
     * Reads the score from the file and returns an integer array with the scores
     * @return 
     */
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
                    } else {
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
    
    /**
     * Returns a 2d array loaded with a pattern that can be applied to the grid
     * @param game
     * @return 
     */
    public int[][] load(Life game) {
        int[][] grid = new int[game.getSize()][game.getSize()];
        File pattern;

        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            pattern = fc.getSelectedFile();
            FileReader inStream;
            Scanner filein;



            try {

                inStream = new FileReader(pattern); //set up stream
                filein = new Scanner(inStream); //set up reader

                for (int row = 0; row < grid.length; row++) {
                    for (int col = 0; col < grid.length; col++) {
                        grid[row][col] = filein.nextInt();
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

            for (int row = 0; row < game.getSize(); row++) {
                for (int col = 0; col < game.getSize(); col++) {
                    game.setCell(row, col, grid[row][col]);
                }

            }
            
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            
        } else if (returnVal == JFileChooser.ERROR_OPTION) {
            
            System.out.println(JFileChooser.ERROR);
        }
        return grid;
    }
}
