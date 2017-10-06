package braintrain.explead.com.braintrain.logic.counting_cells;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import braintrain.explead.com.braintrain.utils.Utils;
import braintrain.explead.com.braintrain.views.counting_cells.FieldCountingCellsView;

/**
 * Created by develop on 02.10.2017.
 */

public class FieldCountingCells {

    private int size;
    private int endSize;
    private CellCountingCells[][] field;

    private ArrayList<Integer> choices;

    private int count;
    private int index;
    private int lastId = -1;

    public FieldCountingCells(int size) {
        this.size = size;
        endSize = size*size;
        index = new Random().nextInt(4);
        ArrayList<Integer> helper = createHelper();
        createCells(helper);
        createChoices(5);
    }

    private ArrayList<Integer> createHelper() {
        ArrayList<Integer> helper = new ArrayList<>();
        int min = endSize/size;
        int max = endSize - (int)(endSize/(size/1.8f));
        count = Utils.randInt(min, max);
        Log.d("TAG", "Count - " + Integer.toString(count));
        for(int i = 0; i < count; i++) {
            helper.add(index);
        }
        for(int i = count; i < endSize; i++) {
            int currentIndex;
            do {
                currentIndex = Utils.randInt(0, FieldCountingCellsView.colors.length-1);
            } while (index == currentIndex);
            helper.add(currentIndex);
        }
        Collections.shuffle(helper);
        return helper;
    }

    private void createCells(ArrayList<Integer> helper) {
        field = new CellCountingCells[size][size];
        int ind = 0;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                CellCountingCells cell = new CellCountingCells(i, j, helper.get(ind));
                field[i][j] = cell;
                ind++;
            }
        }
    }

    private void createChoices(int number) {
        choices = new ArrayList<>();
        int place = Utils.randInt(0, 4);
        for(int i = 0; i < number; i++) {
            choices.add(count - place + i);
        }
    }

    public ArrayList<Integer> getChoices() {
        return choices;
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

    public int getCount() {
        return count;
    }

    public int getIndex() {
        return index;
    }
}
