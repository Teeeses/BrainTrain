package braintrain.explead.com.braintrain.logic.searching_element;

import java.util.ArrayList;
import java.util.Random;

import braintrain.explead.com.braintrain.utils.Utils;

/**
 * Created by develop on 29.09.2017.
 */

public class FieldSearchingElement {

    private int value;

    private ArrayList<Integer> cells;
    private int size;

    public FieldSearchingElement(int level) {
        this.size = level*12;
        this.value = Utils.randInt(1000, 9999);
        createCells();
        cells.set(Utils.randInt(0, cells.size()-1), value);
    }

    private void createCells() {
        cells = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            int value;
            do {
                value = Utils.randInt(1000, 9999);
            } while (value == this.value);
            cells.add(value);
        }
    }

    public int getValue() {
        return value;
    }

    public ArrayList<Integer> getCells() {
        return cells;
    }
}
