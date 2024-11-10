package org.example.Screens;

import org.example.Configurations.Client;
import org.example.Configurations.Music;
import org.example.CustomComponents.PixelatedButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ResourceBundle;

public class LanguageInterface extends JPanel {
    private GameWindow gameWindow;
    private Client client;
    private ResourceBundle rb;

    private JPanel languagesPanel;

    private JLabel languageLabel;

    private PixelatedButton portugueseButton;
    private PixelatedButton englishButton;
    private PixelatedButton deutschButton;
    private PixelatedButton espanolButton;
    private PixelatedButton italianoButton;
    private PixelatedButton exitButtom;

    LanguageInterface (GameWindow gameWindow, Client client) {
        this.gameWindow = gameWindow;
        this.client = client;

        // Panel to choose languages
        languagesPanel = new JPanel();
        languagesPanel.setBounds(100, 100, 700, 500);
        languagesPanel.setBackground(new Color(41, 40, 45));
        languagesPanel.setLayout(null);
        languagesPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));

        // Top Label
        languageLabel = new JLabel("Idioma/Language/Sprache/Lingua:");
        languageLabel.setBounds(110, 30, 500, 50);
        languageLabel.setForeground(new Color(249, 253, 221));
        languageLabel.setFont(new Font("Arial", Font.BOLD, 30));

        // Languages Buttons
        portugueseButton = new PixelatedButton("Português");
        portugueseButton.setBounds(200, 100, 300, 50);

        englishButton = new PixelatedButton("English");
        englishButton.setBounds(200, 165, 300, 50);

        deutschButton = new PixelatedButton("Deutsch");
        deutschButton.setBounds(200, 230, 300, 50);

        espanolButton = new PixelatedButton("Español");
        espanolButton.setBounds(200, 295, 300, 50);

        italianoButton = new PixelatedButton("Italiano");
        italianoButton.setBounds(200, 360, 300, 50);

        exitButtom = new PixelatedButton("Sair/Exit/Ausgehen/Salir/Uscire");
        exitButtom.setBounds(150, 430, 400, 50);

        // Action Listeners
        portugueseButton.addActionListener(event -> {
            rb = ResourceBundle.getBundle("languages.portugues");
            sendUpdateMessageToClient("português");
            gameWindow.showLoginInterface(client, rb);
        });

        englishButton.addActionListener(event -> {
            rb = ResourceBundle.getBundle("languages.english");
            sendUpdateMessageToClient("english");
            gameWindow.showLoginInterface(client, rb);
        });

        deutschButton.addActionListener(event -> {
            rb = ResourceBundle.getBundle("languages.deutsch");
            sendUpdateMessageToClient("deutsch");
            gameWindow.showLoginInterface(client, rb);
        });

        espanolButton.addActionListener(event -> {
            rb = ResourceBundle.getBundle("languages.espanol");
            sendUpdateMessageToClient("español");
            gameWindow.showLoginInterface(client, rb);
        });

        italianoButton.addActionListener(event -> {
            rb = ResourceBundle.getBundle("languages.italiano");
            sendUpdateMessageToClient("italiano");
            gameWindow.showLoginInterface(client, rb);
        });

        exitButtom.addActionListener(event -> {
            System.exit(1);
        });

        this.setBackground(Color.BLACK);
        this.setLayout(null);

        // Adding Components
        languagesPanel.add(languageLabel);
        languagesPanel.add(portugueseButton);
        languagesPanel.add(englishButton);
        languagesPanel.add(deutschButton);
        languagesPanel.add(espanolButton);
        languagesPanel.add(italianoButton);
        languagesPanel.add(exitButtom);

        this.add(languagesPanel, BorderLayout.CENTER);
    }

    public void sendUpdateMessageToClient(String language) {
        String fixedMessage = "Selecionou idioma: " + language;
        client.updateMessage(fixedMessage);  // Envia a mensagem para o Client
    }
}
