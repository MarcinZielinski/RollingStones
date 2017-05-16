package com.rollingstones.app.data;


import com.rollingstones.app.ApplicationUtils;
import com.rollingstones.app.map.Map;
import javafx.application.Platform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Marcin on 2017-03-25.
 */
public class DataHandler implements Runnable {
    private List<String> list;
    private Map map;

    private String dataPath = "res\\wifi_traces.txt";
    public DataHandler() {
        this.map = ApplicationUtils.getMainApp().getMap();
        list = new CopyOnWriteArrayList<>();
    }
    @Override
    public void run() {
        try (Stream<String> stream = Files.lines(Paths.get(dataPath))) {
            list = stream.collect(Collectors.toList());
            list.forEach(this::parseLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String s) {
        String[] line = s.split(","); // 1 : node, 2:mac, 3 signal strength, 4 time, 5 pretty-time
        map.updateDeviceUsers(line[0],line[1],line[2],line[3]); // node, mac, signal, time
    }

    private void loadFile() {

    }
}
