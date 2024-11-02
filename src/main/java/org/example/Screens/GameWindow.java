package org.example.Screens;

import javax.swing.*;

public class GameWindow extends JFrame {
    private GarageInterface garageInterface;
    private WarehouseInterface warehouseInterface;
    private LoginInterface loginInterface;

    public GameWindow() {
        loginInterface = new LoginInterface(this);
        this.setContentPane(loginInterface);

        this.setSize(900, 700);
        this.setResizable(false);
        this.setTitle("Torque Masters");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public Runnable showGarageInterface(int userID) {
        garageInterface = new GarageInterface(userID, this);
        this.setContentPane(garageInterface);
        this.revalidate();
        this.repaint();
        return null;
    }

    public Runnable showWarehouseInterface(int userID) {
        warehouseInterface = new WarehouseInterface(userID, this);
        this.setContentPane(warehouseInterface);
        this.revalidate();
        this.repaint();
        return null;
    }
}
