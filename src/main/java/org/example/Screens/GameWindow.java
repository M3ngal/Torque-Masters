package org.example.Screens;

import javax.swing.*;

import org.example.Configurations.Client;
import org.example.Configurations.Music;

import java.util.ResourceBundle;

public class GameWindow extends JFrame {
    private GarageInterface garageInterface;
    private WarehouseInterface warehouseInterface;
    private DumpsterInterface dumpsterInterface;
    private LoginInterface loginInterface;
    private LanguageInterface languageInterface;
    private Client client;

    public GameWindow(Client client) {
        this.client = client;
        languageInterface = new LanguageInterface(this, client);
        this.setContentPane(languageInterface);

        this.setSize(900, 700);
        this.setResizable(false);
        this.setTitle("Torque Masters");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public Runnable showLoginInterface(Client client, ResourceBundle rb) {
        this.client = client;
        loginInterface = new LoginInterface(this, client, rb);
        this.setContentPane(loginInterface);
        this.revalidate();
        this.repaint();
        return null;
    }

    public Runnable showGarageInterface(int userID, Client client, Music music, ResourceBundle rb) {
        this.client = client;
        garageInterface = new GarageInterface(userID, this, client, music, rb);
        this.setContentPane(garageInterface);
        this.revalidate();
        this.repaint();
        return null;
    }

    public Runnable showWarehouseInterface(int userID, Client client, Music music, ResourceBundle rb) {
        this.client = client;
        warehouseInterface = new WarehouseInterface(userID, this, client, music, rb);
        this.setContentPane(warehouseInterface);
        this.revalidate();
        this.repaint();
        return null;
    }

    public Runnable showDupsterInterface(int userID, Client client, Music music, ResourceBundle rb) {
        this.client = client;
        dumpsterInterface = new DumpsterInterface(userID, this, client, music, rb);
        this.setContentPane(dumpsterInterface);
        this.revalidate();
        this.repaint();
        return null;
    }
}