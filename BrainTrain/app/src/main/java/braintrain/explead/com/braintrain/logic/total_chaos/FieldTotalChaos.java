package braintrain.explead.com.braintrain.logic.total_chaos;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by develop on 25.09.2017.
 */

public class FieldTotalChaos {

    public interface OnFieldListener {
        void onWin();
        void onError(CellTotal cellTotal);
        void onTrue(CellTotal cellTotal);
    }

    private OnFieldListener listener;
    private CellTotal[][] field;

    private ArrayList<Integer> values;

    private int size;

    private int count = 1;

    public FieldTotalChaos(int size) {
        this.size = size;
        fillNumber();
        create();
    }

    private void fillNumber() {
        values = new ArrayList<>();
        for(int i = 0; i < size*size; i++) {
            values.add(i+1);
        }
    }

    private void create() {
        field = new CellTotal[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                CellTotal cellTotal = new CellTotal(getRandomFromValues(), i, j);
                field[i][j] = cellTotal;
            }
        }
    }

    private int getRandomFromValues() {
        Random rand = new Random();
        int index = rand.nextInt(values.size());
        int value = values.get(index);
        values.remove(index);
        return value;
    }

    public void cellClick(CellTotal cellTotal) {
        if(cellTotal.getStatus() == CellTotal.NO_CLICKED) {
            if (cellTotal.getValue() == count) {
                listener.onTrue(cellTotal);
                count++;
                checkWin();
            } else {
                listener.onError(cellTotal);
            }
        }
    }

    private void checkWin() {
        if(count > size*size) {
            listener.onWin();
        }
    }

    public void setListener(OnFieldListener listener) {
        this.listener = listener;
    }

    public int getSize() {
        return field.length;
    }

    public CellTotal[][] getField() {
        return field;
    }

    public CellTotal getCell(int x, int y) {
        return field[x][y];
    }
}
