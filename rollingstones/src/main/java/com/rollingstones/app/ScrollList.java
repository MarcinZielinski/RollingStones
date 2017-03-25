package com.rollingstones.app;

import com.rollingstones.app.map.Map;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Somas3k on 25.03.2017.
 */
public class ScrollList extends JFrame implements MouseListener,ListSelectionListener {

    private JScrollPane scrollpane;
    private JButton showButton;
    private JList list;
    private Map map;
    private String[] deviceList;


    ScrollList() {
        super("Devices");
        setBounds(1620,0,300,1050);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        showButton = new JButton("Show");
        showButton.setEnabled(false);
        showButton.addMouseListener(this);
        //this.map = map;
        this.setVisible(false);

        deviceList = new String[ApplicationUtils.getMainApp().getMap().getHashes().keySet().size()];
        int i=0;

        for(String t : ApplicationUtils.getMainApp().getMap().getHashes().values()){
            deviceList[i]=Integer.toString(t.hashCode());
            deviceList[i]=deviceList[i] + ", " + ApplicationUtils.getMainApp().getMap().getMacsStatistics().get(t)[0];
            i++;
        }
        list = new JList(deviceList);

        list.addListSelectionListener(this);


        //ListModel listModel = new DefaultListModel();


        showList(ApplicationUtils.getMainApp().getMap());

    }
    void showList(Map map){
        this.map = map;

        //deviceList = map.getHashes().keySet().stream().mapToInt(v->v).toArray();
        //list = new JList(Arrays.stream(deviceList).boxed().collect(Collectors.toList()).toArray());
        scrollpane = new JScrollPane(list);

        getContentPane().add(scrollpane, BorderLayout.CENTER);
        getContentPane().add(showButton,BorderLayout.SOUTH);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int index = list.getSelectedIndex();
        map.simulate(Integer.parseInt(deviceList[index].split(",")[0]));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(list.getSelectedIndex()==-1){
            showButton.setEnabled(false);
        }
        else showButton.setEnabled(true);
    }
}
