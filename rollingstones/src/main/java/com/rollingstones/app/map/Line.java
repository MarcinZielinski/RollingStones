package com.rollingstones.app.map;

import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

/**
 * Created by Marcin on 2017-03-25.
 */
public class Line extends MapPolygonImpl implements MouseListener{

    MapPolygonImpl poly;
    int x;
    int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    @Override
    public void paint(Graphics g, java.util.List<Point> points) {
        this.x = points.get(0).x;
        this.y = points.get(1).y;


        Polygon polygon = new Polygon();
        Iterator var4 = points.iterator();

        while(var4.hasNext()) {
            Point p = (Point)var4.next();
            polygon.addPoint(p.x, p.y);
        }

        this.paint(g, polygon);
    }

    public Line(MapPolygonImpl mapPolygon) {
        this.poly = mapPolygon;
    }
    public MapPolygonImpl getPoly() {
        return poly;
    }

    public void setPoly(MapPolygonImpl poly) {
        this.poly = poly;
    }
    public void mouseClicked(MouseEvent e) {
        System.out.println("hej");

    }

    public void mousePressed(MouseEvent e) {
        System.out.println("hej");

    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("hej");

    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("hej");

    }

    public void mouseExited(MouseEvent e) {
        System.out.println("hej");

    }
}
