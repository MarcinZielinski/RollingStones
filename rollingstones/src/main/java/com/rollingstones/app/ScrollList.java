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

    JScrollPane scrollpane;
    JButton showButton;
    JList list;
    Map map;

    public ScrollList(Map map, String) {
        super("JScrollPane Demonstration");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        showButton = new JButton("Show");
        showButton.setEnabled(false);
        this.map = map;



        ListModel listModel = new DefaultListModel();

        Integer deviceList[] = {1,568,848,8442};
        list = new JList(deviceList);
        scrollpane = new JScrollPane(list);


        getContentPane().add(scrollpane, BorderLayout.CENTER);
        getContentPane().add(showButton,BorderLayout.SOUTH);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
