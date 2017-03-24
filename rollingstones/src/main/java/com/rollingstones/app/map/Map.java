package com.rollingstones.app.map;

import com.rollingstones.app.ApplicationUtils;
import org.openstreetmap.gui.jmapviewer.*;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

import javax.swing.*;
import java.util.HashMap;

import com.rollingstones.app.MainApp;

/**
 * Created by Marcin on 2017-03-24.
 */
public class Map {
    private static final long serialVersionUID = 1L;
    private JMapViewer jMapViewer;
     //HashMap<String,Device> mapDots; // String = device.id, device = object which contains coordinates
    private LayerGroup krakowGroup;
    private Layer krakowLayer;
    private HashMap<String,MapDot> mapDots;


    public Map() {
        jMapViewer = new JMapViewer();
        krakowGroup = new LayerGroup("Krakow");
        krakowLayer = krakowGroup.addLayer("Krakow");
        mapDots = new HashMap<String, MapDot>();

        updateDevice(new Device("123123",50.00D,19.00D)); // test marker
    }

    public void updateDevice(Device device) {
        MapMarkerDot mapMarkerDot = new MapMarkerDot(krakowLayer,"",device.getLat(),device.getLon());
        MapDot mapDot = mapDots.get(device.getId());
        if(mapDot == null) {
            mapDot = new MapDot(device, mapMarkerDot);
        } else {
            ApplicationUtils.getMainApp().map().removeMapMarker(mapDot.getMarker());
        }
        mapDots.put(device.getId(),mapDot); // replaces old MapDot or creates new one
        ApplicationUtils.getMainApp().map().addMapMarker(mapMarkerDot);
    }
}
