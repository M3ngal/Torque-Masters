package org.example.Screens;

import javax.swing.*;

import org.example.Configurations.Client;
import org.example.Configurations.Music;


public class GameWindow extends JFrame {
    private GarageInterface garageInterface;
    private WarehouseInterface warehouseInterface;
    private DumpsterInterface dumpsterInterface;
    private LoginInterface loginInterface;
    private Client client;

    public GameWindow(Music music, Client client) {
        this.client = client;
        loginInterface = new LoginInterface(this, client, music);
        this.setContentPane(loginInterface);

        this.setSize(900, 700);
        this.setResizable(false);
        this.setTitle("Torque Masters");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public Runnable showGarageInterface(int userID, Client client, Music music) {
        this.client = client;
        garageInterface = new GarageInterface(userID, this, client, music);
        this.setContentPane(garageInterface);
        this.revalidate();
        this.repaint();
        return null;
    }

    public Runnable showWarehouseInterface(int userID, Client client, Music music) {
        this.client = client;
        warehouseInterface = new WarehouseInterface(userID, this, client, music);
        this.setContentPane(warehouseInterface);
        this.revalidate();
        this.repaint();
        return null;
    }

    public Runnable showDupsterInterface(int userID, Client client, Music music) {
        this.client = client;
        dumpsterInterface = new DumpsterInterface(userID, this, client, music);
        this.setContentPane(dumpsterInterface);
        this.revalidate();
        this.repaint();
        return null;
    }
}
