package com.rollingstones.app;

/**
 * Created by Somas3k on 24.03.2017.
 */
public class ApplicationUtils {
    public static MainApp mainApp;

    private ApplicationUtils(){};

    public static MainApp getMainApp() {
        return mainApp;
    }

    public static void setMainApp(MainApp mainApp) {
        ApplicationUtils.mainApp = mainApp;
    }
}
