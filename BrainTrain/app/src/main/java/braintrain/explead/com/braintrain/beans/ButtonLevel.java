package braintrain.explead.com.braintrain.beans;

import android.graphics.drawable.Drawable;
import android.widget.Button;

/**
 * Created by develop on 10.10.2017.
 */

public class ButtonLevel {
    private int size;
    private Button btn;
    private Button btnStart;
    private String time;
    private Drawable image;

    private boolean open;

    public ButtonLevel(boolean open, String time, int size, Button btn, Drawable image, Button btnStart) {
        this.open = open;
        this.time = time;
        this.size = size;
        this.btn = btn;
        this.image = image;
        this.btnStart = btnStart;
    }

    public int getSize() {
        return size;
    }

    public Button getBtn() {
        return btn;
    }

    public String getTime() {
        return time;
    }

    public boolean isOpen() {
        return open;
    }

    public Drawable getImage() {
        return image;
    }

    public void setColor(Drawable color) {
        btn.setBackgroundDrawable(color);
    }
}