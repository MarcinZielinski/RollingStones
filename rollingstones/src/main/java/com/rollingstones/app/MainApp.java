package com.rollingstones.app;

import com.rollingstones.app.map.Map;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Somas3k on 24.03.2017.
 */
public class MainApp extends JFrame implements JMapViewerEventListener {
    private final JMapViewer map;
    /*private final JLabel zoomLabel;
    private final JLabel zoomValue;
    private final JLabel mperpLabelName;
    private final JLabel mperpLabelValue;*/


    public MainApp(){
        super("RollingStones");
        this.setSize(1920,1080);
        this.map=new JMapViewer();
        this.map.addJMVListener(this);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(6);
        //JPanel panel = new JPanel(new BorderLayout());



        this.add(this.map, "Center");

    }
    public JMapViewer map() {
        return this.map;
    }
    public static void main(String[] args) {
        ApplicationUtils.setMainApp(new MainApp());
        ApplicationUtils.getMainApp().setVisible(true);

        new Map();
    }

    private void updateZoomParameters() {


    }

    public void processCommand(JMVCommandEvent command) {
        if(command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) || command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
            this.updateZoomParameters();
        }
    }
}
