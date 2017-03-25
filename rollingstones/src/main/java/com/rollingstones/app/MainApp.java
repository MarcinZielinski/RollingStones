package com.rollingstones.app;

import com.rollingstones.app.data.DataHandler;
import com.rollingstones.app.map.InfoPanel;
import com.rollingstones.app.map.Map;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Somas3k on 24.03.2017.
 */
public class MainApp extends JFrame implements JMapViewerEventListener, MouseListener {
    private final JMapViewer map;
    /*private final JLabel zoomLabel;
    private final JLabel zoomValue;
    private final JLabel mperpLabelName;
    private final JLabel mperpLabelValue;*/
    private static MapPolygonImpl poly;
    private MapMarkerDot mapMarkerDot;
    private InfoPanel infoPanel;
    private Map mapElements;
    private DataHandler dataHandler;
    private ScrollList list;



    public Map getMap() {
        return mapElements;
    }

    private MainApp(){
        super("RollingStones");
        this.setSize(1920,1080);
        this.map=new JMapViewer();
        this.map.addJMVListener(this);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(6);
        //JPanel panel = new JPanel(new BorderLayout());
        map.addMouseListener(this);

        infoPanel = new InfoPanel();
        map.add(infoPanel);


        infoPanel.setLocation(0,0);
        infoPanel.getComponent(0).setLocation(70,0);
        infoPanel.getComponent(0).setSize(30,30);
        this.add(this.map, "Center");
        this.pack();
    }
    private void run() {
        mapElements = new Map();
        this.dataHandler = new DataHandler();
        this.dataHandler.run();
        mapElements.calculateHashes();

        infoPanel.setCountOfDevices(mapElements.countDevices());
        //list = new ScrollList();

    }
    public JMapViewer map() {
        return this.map;
    }
    public static void main(String[] args) {
        ApplicationUtils.setMainApp(new MainApp());
        ApplicationUtils.getMainApp().setVisible(true);
        ApplicationUtils.getMainApp().run();

        poly = ApplicationUtils.getMainApp().getMap().poly;
        ApplicationUtils.getMainApp().mapMarkerDot= ApplicationUtils.getMainApp().getMap().mapMarkerDot;
    }

    private void updateZoomParameters() {


    }

    public void processCommand(JMVCommandEvent command) {
        if(command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) || command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
            this.updateZoomParameters();
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(poly.getPolygon().contains(e.getX(),e.getY())) {
            infoPanel.setLocation(e.getX(),e.getY());
            infoPanel.revalidate();
            infoPanel.repaint();

            infoPanel.setVisible(true);

        }

        if(((MapMarkerDot)(map().getMapMarkerList().get(0))).contains(e.getX(),e.getY())||((MapMarkerDot)(map().getMapMarkerList().get(1))).contains(e.getX(),e.getY())){ // node 1
            list = new ScrollList();
            //list.showList(mapElements);
            list.setVisible(true);
        }

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
