package braintrain.explead.com.braintrain.logic.counting_cells;

import java.util.ArrayList;
import java.util.Random;

import braintrain.explead.com.braintrain.utils.Utils;

/**
 * Created by develop on 02.10.2017.
 */

public class FieldCountingCells {

    private int size;
    private int endSize;
    private CellCountingCells[][] field;
    private ArrayList<Integer> helper;

    private int lastId = -1;

    public FieldCountingCells(int size) {
        this.size = size;
        endSize = size*size;
        createHelper();
        createCells();
    }

    private void createHelper() {
        helper = new ArrayList<>();
        int min = endSize/size;
        int max = endSize - (int)(endSize/(size/1.5));
        int count = Utils.randInt(min, max);
        for(int i = 0; i < count; i++) {
            helper.add(0);
        }

        for(int i = count; i < endSize; i++) {
            int index;
            do {
                index = Utils.randInt(1, 4);
            } while (lastId == index);
            helper.add(index);
        }
    }

    private void createCells() {
        field = new CellCountingCells[size][size];
        Random rand = new Random();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                int index = rand.nextInt(helper.size());
                CellCountingCells cell = new CellCountingCells(i, j, helper.get(index));
                field[i][j] = cell;
                helper.remove(index);
            }

        }
    }

    public CellCountingCells getCell(int x, int y) {
        return field[x][y];
    }

    public int getSize() {
        return size;
    }

    public int getEndSize() {
        return endSize;
    }

    public CellCountingCells[][] getField() {
        return field;
    }
}
