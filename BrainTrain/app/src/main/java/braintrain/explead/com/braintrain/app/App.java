package braintrain.explead.com.braintrain.app;

import android.app.Application;

/**
 * Created by develop on 30.12.2016.
 */

public class App extends Application {

    private static float widthScreen;
    private static float heightScreen;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static float getWidthScreen() {
        return widthScreen;
    }

    public static void setWidthScreen(float widthScreen) {
        App.widthScreen = widthScreen;
    }

    public static float getHeightScreen() {
        return heightScreen;
    }

    public static void setHeightScreen(float heightScreen) {
        App.heightScreen = heightScreen;
    }

}
