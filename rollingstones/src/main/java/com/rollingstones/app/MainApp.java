package com.rollingstones.app;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.JMapViewerTree;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Somas3k on 24.03.2017.
 */
public class MainApp extends JFrame implements JMapViewerEventListener {
    private final JMapViewerTree treeMap;
    /*private final JLabel zoomLabel;
    private final JLabel zoomValue;
    private final JLabel mperpLabelName;
    private final JLabel mperpLabelValue;*/


    public MainApp(){
        super("RollingStones");
        this.setSize(1920,1080);
        this.treeMap=new JMapViewerTree("Zones");
        this.map().addJMVListener(this);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(6);
        //JPanel panel = new JPanel(new BorderLayout());



        this.add(this.treeMap, "Center");

    }
    public JMapViewer map() {
        return this.treeMap.getViewer();
    }
    public static void main(String[] args) {
        new MainApp().setVisible(true);
    }

    private void updateZoomParameters() {


    }

    public void processCommand(JMVCommandEvent command) {
        if(command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) || command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
            this.updateZoomParameters();
        }
    }
}
