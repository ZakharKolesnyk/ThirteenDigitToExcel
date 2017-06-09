package com.gmail.kolesnyk.zakhar.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.gmail.kolesnyk.zakhar.controller.Controller.loadFile;
import static com.gmail.kolesnyk.zakhar.controller.Controller.moveResultToXLS;

public class MainWindow extends JFrame {
    private JPanel mainPanel;

    private MainWindow() throws HeadlessException {
        init();
    }

    private void init() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 200));
        mainPanel = mainPanel(getSize());
        add(mainPanel);
    }

    private JPanel mainPanel(Dimension size) {
        JPanel mainPanel = new JPanel();
        mainPanel.setVisible(true);
        mainPanel.setSize(size);
        mainPanel.add(selectFileButton());
        mainPanel.add(moveResultToXLSButton());
        return mainPanel;
    }

    private JButton selectFileButton() {
        JButton button = new JButton("select file");
        button.setPreferredSize(new Dimension(250, 100));
        button.setVisible(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(mainPanel);
                loadFile(fc.getSelectedFile());
            }
        });
        return button;
    }

    private JButton moveResultToXLSButton() {
        JButton button = new JButton("Move result to XLS");
        button.setPreferredSize(new Dimension(250, 100));
        button.setVisible(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    moveResultToXLS();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        return button;
    }


    public static void main(String[] args) {
        new MainWindow().setVisible(true);
    }
}
