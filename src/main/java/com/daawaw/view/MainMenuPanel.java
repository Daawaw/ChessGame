package com.daawaw.view;

import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class MainMenuPanel extends JPanel {

    private static final Dimension SIZE = new Dimension(840, 770);
    private JPanel MainMenu;
    private JLabel  northLabel1, northLabel2, northLabel3;
    public MainMenuPanel(){
        MainMenu = new JPanel();
        setSize(700, 50);
        setVisible(true);
        JButton Fuck = new JButton("New Game");
        JButton Fuck2 = new JButton("Load Game");

        FlowLayout mainLayout = new FlowLayout(FlowLayout.CENTER);
        //northLabel1 = new JLabel("label 1");
        //northLabel2 = new JLabel("label 2");
        //northLabel3 = new JLabel("label 3");
        MainMenu.setLayout(mainLayout);
        add(Fuck);
        add(Fuck2);
        //add(northLabel3);


    }
}
