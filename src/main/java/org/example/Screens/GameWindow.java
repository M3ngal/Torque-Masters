package org.example.Screens;

import javax.swing.*;

public class GameWindow extends JFrame {
    private GarageInterface garageInterface;
    private LoginInterface loginInterface;

    GameWindow() {
        loginInterface = new LoginInterface(this);
        this.setContentPane(loginInterface);

        this.setSize(900, 700);
        this.setResizable(false);
        this.setTitle("Torque Masters");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public Runnable showGarageInterface(int userID) {
        garageInterface = new GarageInterface(userID);
        this.setContentPane(garageInterface);
        this.revalidate();
        this.repaint();
        return null;
    }

    public static void main(String[] args) {
        new GameWindow();
    }
}
