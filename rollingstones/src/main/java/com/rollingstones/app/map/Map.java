package com.rollingstones.app.map;

import com.rollingstones.app.ApplicationUtils;
import org.openstreetmap.gui.jmapviewer.*;

import java.util.HashMap;

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
    private double lineMaker = 0.000005;
    public  MapPolygonImpl poly;
    public MapMarkerDot mapMarkerDot;

    public Map() {
        jMapViewer = new JMapViewer();
        krakowGroup = new LayerGroup("Krakow");
        krakowLayer = krakowGroup.addLayer("Krakow");
        mapDots = new HashMap<String, MapDot>();

        updateDevice(new Device("123123",50.00D,19.00D)); // test marker
        updateDevice(new Device("1231233",50.05D,19.19D)); // test marker
        drawLine(mapDots.get("123123").getDevice(),mapDots.get("1231233").getDevice());

    }

    public void updateDevice(Device device) {
        mapMarkerDot = new MapMarkerDot(krakowLayer,"",device.getLat(),device.getLon());
        MapDot mapDot = mapDots.get(device.getId());
        if(mapDot == null) {
            mapDot = new MapDot(device, mapMarkerDot);
        } else {
            ApplicationUtils.getMainApp().map().removeMapMarker(mapDot.getMarker());
        }
        mapDots.put(device.getId(),mapDot); // replaces old MapDot or creates new one
        ApplicationUtils.getMainApp().map().addMapMarker(mapMarkerDot);
    }

    private void drawLine(Device d1, Device d2) {
        MapPolygonImpl mapPolygon = new MapPolygonImpl(krakowLayer,"line",new Coordinate[] {
                new Coordinate(d1.getLat()-lineMaker,d1.getLon()), new Coordinate(d2.getLat()-lineMaker,d2.getLon()),
                new Coordinate(d2.getLat()+lineMaker,d2.getLon()), new Coordinate(d1.getLat()+lineMaker,d1.getLon())
        });
        mapPolygon.setBackColor(mapPolygon.getColor());
        this.poly = mapPolygon;
        ApplicationUtils.getMainApp().map().addMapPolygon(mapPolygon);
    }
}
