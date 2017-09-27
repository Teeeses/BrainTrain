package braintrain.explead.com.braintrain.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import braintrain.explead.com.braintrain.utils.Utils;

/**
 * Created by develop on 27.09.2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected SharedPreferences sPref;

    public SharedPreferences getPref() {
        return sPref;
    }

    public void setTotalChaosResult(long time, int level) {
        SharedPreferences.Editor editor = sPref.edit();
        if(level == 6) {
            if(time < sPref.getLong(Utils.TIME_6_TOTAL_CHAOS, Long.MAX_VALUE)) {
                editor.putLong(Utils.TIME_6_TOTAL_CHAOS, time);
                if(time > 31000) {
                    editor.putInt(Utils.LEVEL_TOTAL_CHAOS, level + 1);
                }
            }
        } else if(level == 7) {
            if(time < sPref.getLong(Utils.TIME_7_TOTAL_CHAOS, Long.MAX_VALUE)) {
                editor.putLong(Utils.TIME_7_TOTAL_CHAOS, time);
                if(time > 55000) {
                    editor.putInt(Utils.LEVEL_TOTAL_CHAOS, level + 1);
                }
            }
        } else if(level == 8) {
            if(time < sPref.getLong(Utils.TIME_8_TOTAL_CHAOS, Long.MAX_VALUE)) {
                editor.putLong(Utils.TIME_8_TOTAL_CHAOS, time);
                if(time > 91000) {
                    editor.putInt(Utils.LEVEL_TOTAL_CHAOS, level + 1);
                }
            }
        } else if(level == 9) {
            if(time < sPref.getLong(Utils.TIME_9_TOTAL_CHAOS, Long.MAX_VALUE)) {
                editor.putLong(Utils.TIME_9_TOTAL_CHAOS, time);
                if(time > 141000) {
                    editor.putInt(Utils.LEVEL_TOTAL_CHAOS, level + 1);
                }
            }
        } else if(level == 10) {
            if(time < sPref.getLong(Utils.TIME_10_TOTAL_CHAOS, Long.MAX_VALUE)) {
                editor.putLong(Utils.TIME_10_TOTAL_CHAOS, time);
                if(time > 180000) {
                    editor.putInt(Utils.LEVEL_TOTAL_CHAOS, level + 1);
                }
            }
        }
        editor.apply();
    }

    public long getTimeTotalChaos(int size) {
        long result = 0;
        if(size == 6) {
            result = sPref.getLong(Utils.TIME_6_TOTAL_CHAOS, 0);
        } else if(size == 7) {
            result = sPref.getLong(Utils.TIME_7_TOTAL_CHAOS, 0);
        } else if(size == 8) {
            result = sPref.getLong(Utils.TIME_8_TOTAL_CHAOS, 0);
        } else if(size == 9) {
            result = sPref.getLong(Utils.TIME_9_TOTAL_CHAOS, 0);
        } else if(size == 10) {
            result = sPref.getLong(Utils.TIME_10_TOTAL_CHAOS, 0);
        }
        return result;
    }

    public int getLevelTotalChaos() {
        return sPref.getInt(Utils.LEVEL_TOTAL_CHAOS, 6);
    }
}
