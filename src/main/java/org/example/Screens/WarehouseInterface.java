package org.example.Screens;

import org.example.CustomComponents.CustomPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WarehouseInterface extends CustomPanel {
    private JLabel warehouseLabel;

    private JPanel userCarsPanel;

    Color titleColor = new Color(33, 33, 33);
    Color backgroundColor = new Color(249, 253, 221);
    Color warehouseColor = new Color(41, 40, 45, 210);

    WarehouseInterface() {
        super("images//warehouse.jpg");
        this.setLayout(null);

        //Warehouse Title Label
        try {
            File titleFontFile = new File("fonts//Satisfy-Regular.ttf");
            Font titleFont = Font.createFont(Font.TRUETYPE_FONT, titleFontFile).deriveFont(68f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(titleFont);

            // Title
            warehouseLabel = new JLabel("The Warehouse");
            warehouseLabel.setFont(titleFont);
            warehouseLabel.setOpaque(true);
            warehouseLabel.setForeground(backgroundColor);
            warehouseLabel.setBackground(titleColor);
            warehouseLabel.setBorder(new LineBorder(Color.DARK_GRAY, 6, true));
            warehouseLabel.setBounds(120, 10, 650, 90);
            warehouseLabel.setVerticalAlignment(JLabel.CENTER);
            warehouseLabel.setHorizontalAlignment(JLabel.CENTER);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //User Cars Panel
            userCarsPanel = new JPanel();
            userCarsPanel.setBackground(warehouseColor);
            userCarsPanel.setBounds(70, 140, 750, 450);

        //Adding Components
        this.add(warehouseLabel);
        this.add(userCarsPanel);
    }

}
