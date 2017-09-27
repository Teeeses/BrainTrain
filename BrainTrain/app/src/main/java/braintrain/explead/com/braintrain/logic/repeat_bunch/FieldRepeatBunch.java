package braintrain.explead.com.braintrain.logic.repeat_bunch;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by develop on 25.09.2017.
 */
public class FieldRepeatBunch {

    public interface OnFieldListener {
        void onWin();
        void onError(CellRepeat cellRepeat);
        void onLight();
        void onExtinguish();
    }

    private OnFieldListener listener;
    private CellRepeat[][] field;

    private int size;

    private int count;
    private ArrayList<CellRepeat> light = new ArrayList<>();

    private ArrayList<CellRepeat> choice = new ArrayList<>();

    private int countLight = 0;


    public FieldRepeatBunch(int count) {
        this.count = count;
        countSize();
        create();
    }

    private void create() {
        createField();
        createChoise();
        createLight();
    }

    private void createField() {
        field = new CellRepeat[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                CellRepeat cellRepeat = new CellRepeat(i, j);
                field[i][j] = cellRepeat;
            }
        }
    }

    private void createChoise() {
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field.length; j++) {
                choice.add(field[i][j]);
            }
        }
    }

    private void createLight() {
        for(int i = 0; i < count; i++) {
            Random rand = new Random();
            int index = rand.nextInt(choice.size());
            light.add(choice.get(index));
            choice.remove(index);
        }
    }

    //Зажечь клетки
    public void lightCells() {
        for(int i = 0; i < light.size(); i++) {
            light.get(i).light();
        }
        listener.onLight();
    }

    //Погасить клекти
    public void extinguishCells() {
        for(int i = 0; i < light.size(); i++) {
            light.get(i).extinguish();
        }
        listener.onExtinguish();
    }

    public void cellClick(CellRepeat cellRepeat) {
        if(cellRepeat.getStatus() == CellRepeat.NORMAL) {
            if (isInLight(cellRepeat)) {
                cellRepeat.light();
                countLight++;
                checkWin();
            } else {
                cellRepeat.error();
                listener.onError(cellRepeat);
            }
        }
    }

    //Есть в массиве такая клетка или нет
    private boolean isInLight(CellRepeat cellRepeat) {
        for(int i = 0; i < light.size(); i++) {
            if(cellRepeat.equals(light.get(i))) {
                cellRepeat.light();
                return true;
            }
        }
        return false;
    }

    private void checkWin() {
        if(countLight == count) {
            listener.onWin();
        }
    }

    private void countSize() {
        size = 4;
        if(count > 6 && count <= 10) {
            size = 5;
        } else if(count > 10 && count <= 16) {
            size = 6;
        } else if(count > 16 && count <= 22) {
            size = 7;
        } else if(count > 22 && count <= 30) {
            size = 8;
        } else if(count > 30 && count <= 38) {
            size = 9;
        } else if(count > 38 && count <= 50) {
            size = 10;
        }
    }

    public void setListener(OnFieldListener listener) {
        this.listener = listener;
    }

    public int getSize() {
        return field.length;
    }

    public CellRepeat[][] getField() {
        return field;
    }

    public CellRepeat getCell(int x, int y) {
        return field[x][y];
    }
}
