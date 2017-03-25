package com.rollingstones.app;

import com.rollingstones.app.map.Map;

/**
 * Created by Somas3k on 24.03.2017.
 */
public class ApplicationUtils {
    public static MainApp mainApp;
    public static Map map;

    private ApplicationUtils(){};

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        ApplicationUtils.map = map;
    }

    public static MainApp getMainApp() {
        return mainApp;
    }

    public static void setMainApp(MainApp mainApp) {
        ApplicationUtils.mainApp = mainApp;
    }
}
