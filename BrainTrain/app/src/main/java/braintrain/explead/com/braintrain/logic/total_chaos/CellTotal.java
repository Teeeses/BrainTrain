package braintrain.explead.com.braintrain.logic.total_chaos;

/**
 * Created by develop on 25.09.2017.
 */

public class CellTotal {

    public interface OnClickedCell {
        void onClickTrue();
        void onClickError();
    }

    private OnClickedCell listener;

    private int value;
    private int x;
    private int y;

    public static int CLICKED = 0, NO_CLICKED = 1;
    private int status = NO_CLICKED;

    public CellTotal(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }


    public void setStatusTrue() {
        status = CLICKED;
        listener.onClickTrue();
    }

    public void error() {
        listener.onClickError();
    }

    public void setListener(OnClickedCell listener) {
        this.listener = listener;
    }

    public int getStatus() {
        return status;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
