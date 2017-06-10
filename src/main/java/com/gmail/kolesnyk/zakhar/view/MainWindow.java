package com.gmail.kolesnyk.zakhar.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.gmail.kolesnyk.zakhar.controller.Controller.*;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JLabel inputPathLabel;
    private JLabel outputPathLabel;
    private Font font;

    private MainWindow() throws HeadlessException {
        init();
    }

    private void init() {
        font = new Font("Verdana", Font.PLAIN, 20);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 400));
        add(mainPanel = mainPanel(getSize()));
    }

    private JPanel inputPathPanel(final int width) {
        return new JPanel() {{
            setPreferredSize(new Dimension(width, 50));
            setLocation(0, 0);
            add(new JLabel("INPUT:") {{
                setFont(font);
                setPreferredSize(new Dimension(100, 50));
                setLocation(25, 50);
                setVisible(true);
            }});
            add(inputPathLabel = new JLabel("select TXT file") {{
                setFont(font);
                setPreferredSize(new Dimension(width - 125, 50));
                setLocation(60, 50);
                setVisible(true);
            }});
        }};

    }

    private JPanel outputPathLabel(final int width) {
        return new JPanel() {{
            setPreferredSize(new Dimension(width, 50));
            setLocation(0, 150);
            add(new JLabel("OUTPUT:") {{
                setFont(font);
                setPreferredSize(new Dimension(100, 50));
                setLocation(25, 50);
                setVisible(true);
            }});
            add(outputPathLabel = new JLabel("select XLS file") {{
                setFont(font);
                setPreferredSize(new Dimension(width - 125, 50));
                setLocation(60, 50);
                setVisible(true);
            }});
        }};

    }

    private JPanel mainPanel(Dimension size) {
        JPanel mainPanel = new JPanel();
        mainPanel.setVisible(true);
        mainPanel.setSize(size);
        mainPanel.add(inputPathPanel(getSize().width));
        mainPanel.add(outputPathLabel(getSize().width));
        mainPanel.add(selectInputFileButton());
        mainPanel.add(selectOutputFileButton());
        mainPanel.add(moveResultToXLSButton());
        return mainPanel;
    }

    private JButton selectInputFileButton() {
        JButton button = new JButton("Select TXT");
        button.setPreferredSize(new Dimension(250, 100));
        button.setVisible(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(mainPanel);
                String path = loadTxtFile(fc.getSelectedFile());
                if (path != null) {
                    inputPathLabel.setText(path);
                }
            }
        });
        return button;
    }

    private JButton selectOutputFileButton() {
        JButton button = new JButton("Select XLS");
        button.setPreferredSize(new Dimension(250, 100));
        button.setVisible(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(mainPanel);
                String path = loadXlsFile(fc.getSelectedFile());
                if (path != null) {
                    outputPathLabel.setText(path);
                }
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
                    String result = moveResultToXLS();
                    if (result == null) {
                        inputPathLabel.setText("");
                        outputPathLabel.setText("");
                    }
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
