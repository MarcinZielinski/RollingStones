package com.rollingstones.app.map;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Somas3k on 25.03.2017.
 */
public class InfoPanel extends JPanel implements MouseListener {
    private JButton button;
    private JLabel numOfDevices;
    private int countOfDevices;

    public void setCountOfDevices(int countOfDevices) {
        this.countOfDevices = countOfDevices;
        numOfDevices.setText(Integer.toString(countOfDevices));
    }

    public InfoPanel(){
        this.setBackground(Color.DARK_GRAY);
        this.setSize(120,50);
        this.setLayout(new MigLayout());
        initButton();
        JLabel devices = new JLabel("Devices: ");
        devices.setForeground(Color.white);

        numOfDevices = new JLabel("0");
        numOfDevices.setForeground(Color.white);
        this.add(devices);
        this.add(numOfDevices);
        this.add(button,"width 20!, height 20!");

        this.setVisible(false);
    }

    private void initButton() {
        button = new JButton();
        button.addMouseListener(this);
        button.setFont(new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,5));
        button.setSize(20,20);
        button.setLocation(80,0);
        button.setText("X");
        button.revalidate();
        button.repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setVisible(false);
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
}
