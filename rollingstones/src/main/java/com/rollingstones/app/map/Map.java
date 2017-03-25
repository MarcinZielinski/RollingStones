package com.rollingstones.app.map;

import com.rollingstones.app.ApplicationUtils;
import com.rollingstones.app.data.DataHandler;
import org.openstreetmap.gui.jmapviewer.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
    //private HashMap<Line,Polygon> lines;
    private HashMap<String,ArrayList<String[]>> macs; // phone mac, list<device, signal, date>
    private HashMap<String,Object[]> macsStatistics; // how many times moved,
    private double lineMaker = 0.000005;
    public  MapPolygonImpl poly; //TODO: to stash
    public MapMarkerDot mapMarkerDot;
    private DataHandler dataHandler;
    private HashMap<Integer,String> hashes;

    public HashMap<String, Object[]> getMacsStatistics() {
        return macsStatistics;
    }

    public HashMap<Integer,String> getHashes() {
        return hashes;
    }

    public HashMap<String, ArrayList<String[]>> getMacs() {
        return macs;
    }

    public Map() {
        jMapViewer = new JMapViewer();
        krakowGroup = new LayerGroup("Krakow");
        krakowLayer = krakowGroup.addLayer("Krakow");
        mapDots = new HashMap<String, MapDot>();
        macsStatistics = new HashMap<>();
        macs = new HashMap<>();
        hashes = new HashMap<>();

        updateDevice(new Device("123123",50.021231D, 19.886560D)); // Hacknarok room
        updateDevice(new Device("1231233",50.020984D, 19.885857D)); // Game room
        drawLine(mapDots.get("123123").getDevice(),mapDots.get("1231233").getDevice());

    }

    public void simulate(int hashCode) {

    }

    private Coordinate estimateCoord(Device d1, Device d2) {
        return null;
    }

    public int countDevices() {
        Set<String> allMacs = macs.keySet();
        allMacs.forEach(this::checkSignal);
        return macsStatistics.values().stream().filter(v->(int)v[0]>1).mapToInt(v->(int)v[0]).sum();
    }
    void checkSignal(String mac) {
        ArrayList<String[]> arrayOfArrays = macs.get(mac);
        int epsilon = 1;
        int linearDownCounter = 0;
        int linearRaisingCounter = 0;
        boolean raising = false;
        boolean samePlace = false;
        boolean loosing = false;
        boolean change = false;
        boolean readyToGain = false;
        boolean readyToLoose = false;
        for(int i=0;i<arrayOfArrays.size()-1;++i) {
            String[] a1 = arrayOfArrays.get(i); // device,signal,date
            String[] a2 = arrayOfArrays.get(i+1);



            if((-Integer.parseInt(a1[1]))-epsilon < (-Integer.parseInt(a2[1]))+epsilon) { // if loosing signal
                if(!raising) {      // if weren't raising, we keep loosing signal
                    linearRaisingCounter=0;
                    linearDownCounter++;
                } else {
                    linearDownCounter++;
                    linearRaisingCounter--;
                    change = true;
                }
                if(linearDownCounter > 2 ) {
                    readyToGain = true;
                    if(readyToLoose) {
                        if(!(a1[0].equals(a2[0]))) { // we found a device that moved!!
                            Object[] o = macsStatistics.get(mac);
                            o[0] = (int)o[0] + 1;
                        }
                    }
                }
                raising = false;
                samePlace = true;
            }
            if ((-Integer.parseInt(a1[1]))+epsilon > (-Integer.parseInt(a2[1]))-epsilon) { // if gaining signal
                if(samePlace) { // if macs were at the particularly same place
                    if(change) {    // if status of raising changer
                        raising = true; // we need to turn it back then
                    }
                }

                if(raising) {   // if were raising, we keep gaining signal
                    linearDownCounter=0;
                    linearRaisingCounter++;
                    if(linearRaisingCounter>2) {
                        if(readyToGain) {
                            if(!(a1[0].equals(a2[0]))) { // we found a device that moved!!
                                Object[] o = macsStatistics.get(mac);
                                o[0] = (int)o[0] + 1;
                            }
                        }
                    }
                } else {
                    linearRaisingCounter++;
                    linearDownCounter--;
                }
                if(linearRaisingCounter>2) {
                    readyToLoose = true;
                }
                raising = true;
            }
            change = false;
            samePlace = false;
        }
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
        this.poly = mapPolygon; //TODO: why I deleted this?
        ApplicationUtils.getMainApp().map().addMapPolygon(mapPolygon);
    }

    public void updateDeviceUsers(String device, String mac, String signal, String date) {
        ArrayList<String[]> arrayOfArrays;
        if(macs.get(mac) == null) {
            macsStatistics.put(mac,new Object[]{0});
            arrayOfArrays = new ArrayList<>();
            macs.put(mac,arrayOfArrays);
        }
        arrayOfArrays = macs.get(mac);

        String[] a = new String[]{device,signal,date};
        arrayOfArrays.add(a);
    }

    public void calculateHashes() {
        for (String s : macs.keySet()) {
            hashes.put(s.hashCode(),s);
        }
    }
}
