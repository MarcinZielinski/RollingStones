package com.rollingstones.app.map;

import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 * Created by Marcin on 2017-03-24.
 */
public class MapDot {

    private MapMarker marker;
    private Device device;


    public Device getDevice() {
        return device;
    }

    public MapDot(Device device, MapMarker marker) {
        this.device = device;
        this.marker = marker;

    }

    public String getId() {
        return id;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }
    public MapMarker getMarker() {
        return marker;
    }

    public void setMarker(MapMarker marker) {
        this.marker = marker;
    }
}
