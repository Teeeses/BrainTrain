package braintrain.explead.com.braintrain.utils;


import android.content.Context;
import android.graphics.Typeface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import braintrain.explead.com.braintrain.ui.MainActivity;

public class Utils {

    public static final String APP_PREFERENCES = "mysettings";

    public final static String TIME_6_TOTAL_CHAOS = "time_6_total_chaos";
    public final static String TIME_7_TOTAL_CHAOS = "time_7_total_chaos";
    public final static String TIME_8_TOTAL_CHAOS = "time_8_total_chaos";
    public final static String TIME_9_TOTAL_CHAOS = "time_9_total_chaos";
    public final static String TIME_10_TOTAL_CHAOS = "time_10_total_chaos";


    public final static String REPEAT_BUNCH = "repeat_bunch";

    public final static String COUNTING_CELLS = "counting_cells";

    public final static String LEVEL_TOTAL_CHAOS = "level_total_chaos";


    public static Typeface getTypeFaceLevel(Context context) {
        return Typeface.createFromAsset(context.getAssets(),"fonts/level_personal.ttf");
    }

    public static Typeface getTypeFaceNumber(Context context) {
        return Typeface.createFromAsset(context.getAssets(),"fonts/number.ttf");
    }

    public static String millsToString(long mills) {
        Date date = new Date(mills);
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}
