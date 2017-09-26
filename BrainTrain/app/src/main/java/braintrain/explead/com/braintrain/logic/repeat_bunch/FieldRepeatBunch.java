package braintrain.explead.com.braintrain.logic.repeat_bunch;

/**
 * Created by develop on 25.09.2017.
 */

public class FieldRepeatBunch {

    public interface OnFieldListener {
        void onWin();
        void onError(CellRepeat cellRepeat);
        void onTrue(CellRepeat cellRepeat);
    }

    private OnFieldListener listener;
    private CellRepeat[][] field;

    private int size;

    private int count = 3;

    public FieldRepeatBunch(int size) {
        this.size = size;
        create();
    }


    private void create() {
        field = new CellRepeat[size][size];
    }

    public void cellClick(CellRepeat cellRepeat) {

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
