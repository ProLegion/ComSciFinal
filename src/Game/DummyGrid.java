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
public class DummyGrid implements Serializable {

    private int highCount, highRound;
   
    
    public DummyGrid(int count, int round){
        highCount = count;
        highRound = round;
    }
    
    public void setHighCount(int count){
        highCount = count;
    }
    
    public void setHighRound(int round){
        highRound = round;
    }
    
    public void setVals(int round, int count){
        highCount = count;
        highRound = round;
    }
    
}
