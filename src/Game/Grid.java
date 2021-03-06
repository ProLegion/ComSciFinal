package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author IVXX_LeGioN
 */
public class Grid extends JPanel implements Serializable {

    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = GRID_WIDTH;
    private int gridSize, count = 0, round = 0;
    private static final Color SEAGREEN = new Color(153, 255, 51);
    private static final Color SEABLUE = new Color(128, 128, 128);
    private static final Color GRAY = new Color(64, 64, 64);
    private Life game;
    private GridIO gIO = new GridIO();
    private final JFileChooser fc = new JFileChooser();

    public Grid(final Life game) {
        this.game = game;
        gridSize = this.game.getSize();
        setBackground(SEABLUE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / GRID_WIDTH;
                int y = e.getY() / GRID_HEIGHT;
                if (game.getCell(y, x) == 1) {
                    game.setCell(y, x, 0);
                    System.out.println(x + " , " + y + " is now dead.");
                } else {
                    game.setCell(y, x, 1);
                    System.out.println(x + " , " + y + " is now alive.");
                }
                repaint();
            }
        });

    }

    /**
     * Sets up the tile painting
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (game.getCell(row, col) == 1) {
                    g.setColor(SEAGREEN);
                    g.fillRect(col * GRID_HEIGHT, row * GRID_WIDTH, GRID_WIDTH, GRID_HEIGHT);
                }
            }
        }

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                g.setColor(GRAY);
                g.drawRect(col * GRID_HEIGHT, row * GRID_WIDTH, GRID_WIDTH, GRID_HEIGHT);
            }
        }
    }

    /**
     * Returns the dimension of the grid.
     *
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gridSize * GRID_WIDTH + 1, gridSize * GRID_HEIGHT + 1);
    }

    /**
     * Apply the rules of life and repaint.
     */
    public void step() {
        game.takeStep();
        repaint();
    }

    /**
     * Clears the grid, sets all tiles to 0 or DEAD
     */
    public void clear() {
        game.killAllCells();
       clearScore();
        repaint();
    }

    /**
     * Checks to see if the grid is empty.
     *
     * @return
     */
    public boolean isEmpty() {
        for (int row = 0; row < game.getSize(); row++) {
            for (int col = 0; col < game.getSize(); col++) {
                if (game.getCell(row, col) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Opens a File Explorer and allows the user to load a pattern into the
     * grid.
     */
    public void loadPattern() {
        int[][] tempGrid = gIO.load(game);
        
        for (int row = 0; row < tempGrid.length; row++) {
            for (int col = 0; col < tempGrid.length; col++) {
                game.setCell(row, col, tempGrid[row][col]);
            }
        }
        
        repaint();
    }

    /**
     * Opens a File Explorer and allows the user to save the current pattern out
     * to a file.
     */
    public void saveGrid() {
        File output;
        int returnVal = fc.showSaveDialog(Grid.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) { //If file choice is valid
            output = fc.getSelectedFile();
            FileWriter out;
            BufferedWriter writeFile;

            try {
                out = new FileWriter(output, false); //Writes the state of each cell to the file as a 1 or a 0
                writeFile = new BufferedWriter(out);
                for (int row = 0; row < game.getSize(); row++) {
                    for (int col = 0; col < game.getSize(); col++) {
                        int temp = game.getCell(row, col);
                        System.out.println("Cell(" + row + "," + col + ") =  " + temp);
                        writeFile.write(temp + " ");
                    }
                    writeFile.newLine();

                }

                writeFile.close();
                out.close();
                JOptionPane.showMessageDialog(this, "File saved successfully!");
                System.out.println("Data written to file.");
            } catch (IOException e) {
                System.out.println("Problem writing to file.");
                System.err.println("IOException: " + e.getMessage());
            }
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "File not saved");
        } else if (returnVal == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(this, "File could not be saved");
        }

    }

    /**
     * Saves the current grid to the cache file, so the pattern can be recalled.
     */
    public void holdGrid() {
        File output = new File("CACHE"); // File set to null
        FileWriter out;
        BufferedWriter writeFile;

        try {
            out = new FileWriter(output, false); //Writes the state of each cell to the file as a 1 or a 0
            writeFile = new BufferedWriter(out);
            for (int row = 0; row < game.getSize(); row++) {
                for (int col = 0; col < game.getSize(); col++) {
                    int temp = game.getCell(row, col);
                    writeFile.write(temp + " ");
                }
                writeFile.newLine();

            }

            writeFile.close();
            out.close();
            System.out.println("Grid Saved to Temp.");
        } catch (IOException e) {
            System.out.println("Problem writing to file.");
            System.err.println("IOException: " + e.getMessage());
        }
    }

    /**
     * Recalls the grid that is held in the cache file, which is written when
     * the run button is clicked.
     */
    public void recallGrid() {
        File pattern = new File("CACHE");
        FileReader inStream;
        Scanner filein;
        int cell;
        int[][] patternArray = new int[gridSize][gridSize];

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

        for (int row = 0; row < game.getSize(); row++) {
            for (int col = 0; col < game.getSize(); col++) {
                game.setCell(row, col, patternArray[row][col]);
            }

        }
        repaint();
    }
    
    /**
     * Tracks the amount of alive cells and saves the highest amount alive and on which round to variables.
     * @param currentGen 
     */
    public void scoreGrid(int currentGen){
        int  alive = 0;
        for (int row = 0; row < game.getSize(); row++) {
            for (int col = 0; col < game.getSize(); col++) {
                int temp = game.getCell(row, col);
                
                if(temp == 1){
                    alive ++;
                   
                }
            }
        }
        System.out.println("Alive = " + alive) ;
        if(alive > count){
            count = alive;
            round = currentGen;
        }
        
        System.out.println("Best Score " + count + " on generation " + round);
    }
    
    /**
     *Returns a string the describes the grid.
     * @return 
     */
    @Override
    public String toString() {
        String objStr;
        objStr = "Grid{" + "gridSize=" + gridSize + ", highCount=" + count + ", highRound=" + round + ", game=" + game + ", fc=" + fc + '}';        
        return(objStr);
        
    }
    
    /**
     * Returns the current highest count variable
     * @return 
     */
    public int getHighCount(){
        return count;
    }
    
    /**
     * returns the current highest round
     * @return 
     */
    public int getHighRound(){
        return round;
    }
   
    /**
     * Resets the count and score variables
     */
    public void clearScore(){
        count = 0;
        round = 0;
    }
        
}
