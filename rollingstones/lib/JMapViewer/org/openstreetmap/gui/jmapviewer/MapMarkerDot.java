// License: GPL. For details, see Readme.txt file.
package org.openstreetmap.gui.jmapviewer;

import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import java.awt.*;

/**
 * A simple implementation of the {@link MapMarker} interface. Each map marker
 * is painted as a circle with a black border line and filled with a specified
 * color.
 *
 * @author Jan Peter Stotz
 *
 */
public class MapMarkerDot extends MapMarkerCircle {

    public static final int DOT_RADIUS = 5;

    public MapMarkerDot(Coordinate coord) {
        this(null, null, coord);
    }

    public MapMarkerDot(String name, Coordinate coord) {
        this(null, name, coord);
    }

    public MapMarkerDot(Layer layer, Coordinate coord) {
        this(layer, null, coord);
    }

    public MapMarkerDot(Layer layer, String name, Coordinate coord) {
        this(layer, name, coord, getDefaultStyle());
    }

    public MapMarkerDot(Color color, double lat, double lon) {
        this(null, null, lat, lon);
        setColor(color);
    }

    public MapMarkerDot(double lat, double lon) {
        this(null, null, lat, lon);
    }

    public MapMarkerDot(Layer layer, double lat, double lon) {
        this(layer, null, lat, lon);
    }

    public MapMarkerDot(Layer layer, String name, double lat, double lon) {
        this(layer, name, new Coordinate(lat, lon), getDefaultStyle());
    }

    public MapMarkerDot(Layer layer, String name, Coordinate coord, Style style) {
        super(layer, name, coord, DOT_RADIUS, STYLE.FIXED, style);
    }

    public boolean contains(int x, int y){
        double xP = this.getPosition().getX();
        double yP = this.getPosition().getY();
        double radius = this.getRadius();
        if(x>=(int)(xP-radius)&&x<=Math.ceil(xP+radius)&&y>=(int)(yP-radius)&&y<=Math.ceil(yP+radius)) return true;
        else return false;
    }

    public static Style getDefaultStyle() {
        return new Style(Color.BLACK, Color.YELLOW, null, getDefaultFont());
    }
}
