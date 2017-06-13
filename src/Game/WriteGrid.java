/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author canto5684
 */
public class WriteGrid {

    
        public void writeScore(Grid game){
        File scoreOut = new File("SCORES");
         try{
           /* Write Objects to the File*/
            FileOutputStream o = new FileOutputStream(scoreOut, true); 
            ObjectOutputStream writeGrid = new ObjectOutputStream(o);
                
                writeGrid.writeObject(game);                
            
            
            writeGrid.close();
            o.close();
        }catch(IOException e){
            System.out.println("I/O Error!!");
            System.out.println("Error: "+ e.getMessage());
        }
        
    }
}
