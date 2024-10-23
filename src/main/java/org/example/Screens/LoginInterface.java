package org.example.Screens;

import org.example.Conector;
import org.example.CustomComponents.*;
import org.example.Users;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginInterface extends CustomPanel {
    private String username;
    private String password;
    private int userID;
    private boolean userFound = false;
    private GameWindow gameWindow;

    private CardLayout cardLayout;
    private JPanel menuPanel;
    private JPanel loginPanel;
    private JPanel errorPanel;
    private JPanel userExistPanel;
    private JPanel userAddedPanel;

    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel errorLabel;
    private JLabel userExistLabel;
    private JLabel userAddedLabel;

    private CustomTextField usernameField;
    private CustomPasswordField passwordField;

    private PixelatedButton loginButton;
    private PixelatedButton signUpButton;

    Color backgroundColor = new Color(	249, 253, 221);
    Color loginColor = new Color(121, 105, 124);

    LoginInterface(GameWindow gameWindow) {
        super("images//Garagem_Login.png");
        this.gameWindow = gameWindow;
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

        // CardLayout
        cardLayout = new CardLayout();

        // Login Menu Panel
        menuPanel = new JPanel(cardLayout);
        menuPanel.setBackground(loginColor);
        menuPanel.setBounds(218, 200, 450, 350);
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Login Panel
        loginPanel = new JPanel(null);
        loginPanel.setBackground(loginColor);

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

        // Error Panel
        errorPanel = new JPanel();
        errorPanel.setBackground(loginColor);
        errorPanel.setLayout(new GridBagLayout());
        errorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Error Label
        errorLabel = new JLabel("404: User not found");
        errorLabel.setFont(new Font("Arial", Font.BOLD, 40));
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        errorLabel.setVerticalAlignment(JLabel.CENTER);

        errorPanel.add(errorLabel);

        // User Already Exist Panel
        userExistPanel = new JPanel();
        userExistPanel.setBackground(loginColor);
        userExistPanel.setLayout(new GridBagLayout());
        userExistPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // User Already Exist Label
        userExistLabel = new JLabel("403: User Already Exists");
        userExistLabel.setFont(new Font("Arial", Font.BOLD, 35));
        userExistLabel.setHorizontalAlignment(JLabel.CENTER);
        userExistLabel.setVerticalAlignment(JLabel.CENTER);

        userExistPanel.add(userExistLabel);

        // User Added Panel
        userAddedPanel = new JPanel();
        userAddedPanel.setBackground(loginColor);
        userAddedPanel.setLayout(new GridBagLayout());
        userAddedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // User Added Label
        userAddedLabel = new JLabel("201: User Added. Logging In...");
        userAddedLabel.setFont(new Font("Arial", Font.BOLD, 26));
        userAddedLabel.setHorizontalAlignment(JLabel.CENTER);
        userAddedLabel.setVerticalAlignment(JLabel.CENTER);

        userAddedPanel.add(userAddedLabel);

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
            username = usernameField.getText();
            password = passwordField.getText();

            for (Users u : user.readUser()) {
                if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                    setUserID(finalConn);
                    gameWindow.showGarageInterface(userID);
                    return;
                }
            }

            if (!userFound) {
                cardLayout.show(menuPanel, "errorPanel");
                waitCode(2, () -> cardLayout.show(menuPanel, "loginPanel"));
            }
        });

        signUpButton.addActionListener(event -> {
            Users user = new Users(usernameField.getText(), passwordField.getText());
            username = usernameField.getText();
            password = passwordField.getText();

            for (Users u : user.readUser()) {
                if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                    cardLayout.show(menuPanel, "userExistPanel");
                    waitCode(2, () -> cardLayout.show(menuPanel, "loginPanel"));
                    return;
                }
            }

            if (!userFound) {
                user.addUser(finalConn);
                setUserID(finalConn);
                cardLayout.show(menuPanel, "userAddedPanel");
                waitCode(4, () -> gameWindow.showGarageInterface(userID));
            }
        });

        // Add Components
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signUpButton);

        menuPanel.add(loginPanel, "loginPanel");
        menuPanel.add(errorPanel, "errorPanel");
        menuPanel.add(userExistPanel, "userExistPanel");
        menuPanel.add(userAddedPanel, "userAddedPanel");

        this.add(titleLabel);
        this.add(menuPanel);
    }

    public void setUserFound(boolean userFound) {
        this.userFound = userFound;
    }

    public void waitCode(int time, Runnable callback) {
        Timer timer = new Timer(time * 1000, e -> callback.run());
        timer.setRepeats(false);
        timer.start();
    }

    public void setUserID(Connection conn) {
        String sqlSelect = "SELECT user_id FROM login WHERE username = ? AND password = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sqlSelect);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();

            if (rs.next()) {
                this.userID = rs.getInt(1);
            }
        }

        catch(Exception e){
            e.printStackTrace();
            try{
                conn.rollback();
            }
            catch (SQLException e1){
                System.out.print(e1.getStackTrace());
            }
        }
        finally{
            if(rs != null){
                try{
                    rs.close();
                }
                catch (SQLException e1){
                    System.out.print(e1.getStackTrace());
                }
            }
            if(stm != null){
                try{
                    stm.close();
                }
                catch (SQLException e1){
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }

    public int getUserID() {
        return userID;
    }
}