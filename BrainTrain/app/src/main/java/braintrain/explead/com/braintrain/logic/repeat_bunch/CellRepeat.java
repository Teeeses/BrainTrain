package braintrain.explead.com.braintrain.logic.repeat_bunch;

/**
 * Created by develop on 25.09.2017.
 */

public class CellRepeat {

    public interface OnClickedCell {
        void onError();
        void onExtinguish();
        void onLight();
    }

    private OnClickedCell listener;

    private int value;
    private int x;
    private int y;

    public static int NORMAL = 0, CLICKED = 1;
    private int status = NORMAL;

    public CellRepeat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void light() {
        status = CLICKED;
        listener.onLight();
    }

    public void extinguish() {
        status = NORMAL;
        listener.onExtinguish();
    }


    public void error() {
        listener.onError();
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
