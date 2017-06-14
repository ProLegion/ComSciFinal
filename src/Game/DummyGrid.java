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

    private int count, round;

    public DummyGrid(int count, int round) {
        this.count = count;
        this.round = round;
    }

    public void setVals(int round, int count) {
        this.count = count;
        this.round = round;
    }

    public int getCount() {
        return count;
    }

    public int getRound() {
        return round;
    }
}
