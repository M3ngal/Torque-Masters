package org.example.Screens;

import org.example.Conector;
import org.example.CustomComponents.*;
import org.example.Users;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginInterface extends CustomPanel {
    private JPanel loginPanel;

    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private CustomTextField usernameField;
    private CustomPasswordField passwordField;

    private PixelatedButton loginButton;
    private PixelatedButton signUpButton;

    Color backgroundColor = new Color(	249, 253, 221);
    Color loginColor = new Color(121, 105, 124);

    LoginInterface() {
        super("images//Garagem_Login.jpg");
        setLayout(null);

        // Title
        try {
            File titleFontFile = new File("fonts//Satisfy-Regular.ttf");
            Font titleFont = Font.createFont(Font.TRUETYPE_FONT, titleFontFile).deriveFont(64f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(titleFont);

            // Title
            titleLabel = new JLabel("TORQUE MASTERS");
            titleLabel.setFont(titleFont);
            titleLabel.setForeground(backgroundColor);
            titleLabel.setBounds(215, 135, 525, 75);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Login Panel
        loginPanel = new JPanel();
        loginPanel.setBackground(loginColor);
        loginPanel.setLayout(null);
        loginPanel.setBounds(218, 200, 450, 350);
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Username Components
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        usernameLabel.setBounds(40, 30, 120, 40);

        usernameField = new CustomTextField(20);
        usernameField.setBounds(160, 30, 250, 40);

        // Password Components
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        passwordLabel.setBounds(40, 100, 120, 40);

        passwordField = new CustomPasswordField(20);
        passwordField.setBounds(160, 100, 250, 40);

        // Buttons
        loginButton = new PixelatedButton("Login");
        loginButton.setBounds(105, 180, 240, 50);

        signUpButton = new PixelatedButton("Sign Up");
        signUpButton.setBounds(105, 260, 240, 50);

        //Database Connection
        Connection conn = null;
        Conector bd = new Conector();

        try {
            conn = bd.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Connection finalConn = conn;

        // ActionListeners
        loginButton.addActionListener(event -> {
            Users user = new Users();
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean userFound = false;

            for (Users u : user.readUser()) {
                if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                    userFound = true;
                    System.out.println("User Found!");
                }
            }

            if (!userFound)
                System.out.println("User Not Found!");
        });

        signUpButton.addActionListener(event -> {
            Users user = new Users(usernameField.getText(), passwordField.getText());
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean userFound = true;

            for (Users u : user.readUser()) {
                System.out.println("Running...");
                if (!username.equals(u.getUsername()) && !password.equals(u.getPassword())) {
                    userFound = false;
                }
            }

            if (userFound == false)
                user.addUser(finalConn);
        });

        // Add Components
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signUpButton);

        add(titleLabel);
        add(loginPanel);
    }


}
