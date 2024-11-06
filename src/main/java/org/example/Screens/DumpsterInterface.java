package org.example.Screens;

import org.example.CarComponents.*;
import org.example.Configurations.Client;
import org.example.Configurations.Conector;
import org.example.CustomComponents.CustomPanel;
import org.example.CustomComponents.PixelatedDumpsterButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DumpsterInterface extends CustomPanel {
    private int y = 0;
    private Client client;
    private CardLayout cardLayout;
    private Car selectedCar;

    private JLabel dumpsterLabel;
    private JLabel deleteLabel;

    private JPanel mainPanel;
    private JPanel carsPanel;
    private JPanel deletePanel;

    private PixelatedDumpsterButton menuButton;
    private PixelatedDumpsterButton exitButton;
    private PixelatedDumpsterButton confirmButton;
    private PixelatedDumpsterButton returnButton;

    Color dumpColor = new Color(183,208,242);
    Color backgroundColor = new Color(29,22,62, 210);

    DumpsterInterface(int userId, GameWindow gameWindow, Client client) {
        super("images//dumpster.jpg");
        this.client = client;
        this.setLayout(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBounds(70, 140, 750, 490);
        mainPanel.setOpaque(false);

        // Dumpster Title Label
        try {
            File titleFontFile = new File("fonts//Satisfy-Regular.ttf");
            Font titleFont = Font.createFont(Font.TRUETYPE_FONT, titleFontFile).deriveFont(76f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(titleFont);

            dumpsterLabel = new JLabel("The Dumpster");
            dumpsterLabel.setFont(titleFont);
            dumpsterLabel.setForeground(dumpColor);
            dumpsterLabel.setBounds(120, 40, 650, 90);
            dumpsterLabel.setVerticalAlignment(JLabel.CENTER);
            dumpsterLabel.setHorizontalAlignment(JLabel.CENTER);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Cars stored panel
        carsPanel = new JPanel();
        carsPanel.setBackground(backgroundColor);
        carsPanel.setLayout(null);

        // Confirm car deletion panel
        deletePanel = new JPanel();
        deletePanel.setBackground(backgroundColor);
        deletePanel.setLayout(null);

        // Confirm car deletion label
        deleteLabel = new JLabel("Are you sure you want to delete this car?");
        deleteLabel.setFont(new Font("Arial", Font.BOLD, 34));
        deleteLabel.setForeground(dumpColor);
        deleteLabel.setBounds(45, 120, 700, 80);

        confirmButton = new PixelatedDumpsterButton("Confirm");
        confirmButton.setBounds(406, 240, 280, 70);

        returnButton = new PixelatedDumpsterButton("Return");
        returnButton.setBounds(63, 240, 280, 70);

        exitButton = new PixelatedDumpsterButton("Exit");
        exitButton.setBounds(740, 70, 80, 30);

        menuButton = new PixelatedDumpsterButton("Menu");
        menuButton.setBounds(65, 70, 80, 30);

        Connection conn = null;
        Conector bd = new Conector();

        try {
            conn = bd.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Connection finalConn = conn;

        for (Car c : readDatabase(finalConn, userId)) {
            PixelatedDumpsterButton dumpButton = new PixelatedDumpsterButton(c.getCarName());
            dumpButton.setBounds(25, 10 + y, 700, 70);
            y += 80;

            dumpButton.addActionListener(event -> {
                selectedCar = c;
                cardLayout.show(mainPanel, "DeletePanel");
                mainPanel.revalidate();
                mainPanel.repaint();
            });

            carsPanel.add(dumpButton);
        }

        confirmButton.addActionListener(event -> {
            if (selectedCar != null) {
                deleteCar(getEngID(finalConn, selectedCar.getCarName(), userId));
                sendUpdateMessageToClient(selectedCar);
                gameWindow.showGarageInterface(userId, client);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        returnButton.addActionListener(event -> {
            cardLayout.show(mainPanel, "CarsPanel");
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        exitButton.addActionListener(event -> System.exit(0));

        menuButton.addActionListener(event -> gameWindow.showGarageInterface(userId, client));

        returnButton.addActionListener(event -> cardLayout.show(mainPanel, "CarsPanel"));

        deletePanel.add(deleteLabel);
        deletePanel.add(confirmButton);
        deletePanel.add(returnButton);

        mainPanel.add(carsPanel, "CarsPanel");
        mainPanel.add(deletePanel, "DeletePanel");

        this.add(exitButton);
        this.add(menuButton);
        this.add(dumpsterLabel);
        this.add(mainPanel);

        cardLayout.show(mainPanel, "CarsPanel");
    }

    public List<Car> readDatabase(Connection conn, int userID) {
        String sqlSelect = "SELECT Engs.enginetype, Engs.cylamt, Engs.cyl, Engs.aspiration, Engs.fuel, Engs.enginematerial, Engs.traction, " +
                "Cars.brakes, Cars.tires, Cars.chassis, Cars.suspension, Cars.name " +
                "FROM Cars INNER JOIN Engs ON Cars.eng_id = Engs.eng_id WHERE Cars.user_id = ?";

        List<Car> storage = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sqlSelect);
            stm.setInt(1, userID);
            rs = stm.executeQuery();

            while (rs.next()) {
                Engine engine = new Engine(userID, rs.getString("enginetype"), rs.getInt("cylamt"), rs.getDouble("cyl"),
                        rs.getString("aspiration"), rs.getString("fuel"), rs.getString("enginematerial"),
                        rs.getString("traction"));
                Brakes brakes = new Brakes(rs.getString("brakes"));
                Tires tires = new Tires(rs.getString("tires"));
                Chassis chassis = new Chassis(rs.getString("chassis"));
                Suspension suspension = new Suspension(rs.getString("suspension"));
                BodyPaint bodyPaint = new BodyPaint("blue"); // Cor definida como "blue", substitua conforme necessário

                Car car = new Car(userID, engine, brakes, tires, chassis, suspension, bodyPaint, rs.getString("name"));
                storage.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.print(e1.getStackTrace());
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }

        return storage;
    }

    public int getEngID(Connection conn, String carName, int userId) {
        String sqlSelect = "SELECT eng_id FROM Cars WHERE name = ? AND user_id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        int engId = 0;

        try {
            stm = conn.prepareStatement(sqlSelect);
            stm.setString(1, carName);
            stm.setInt(2, userId);
            rs = stm.executeQuery();

            if (rs.next()) {
                engId = rs.getInt(1);
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

        return engId;
    }

    public void deleteCar(int eng_id) {
        String query1 = "DELETE FROM Cars WHERE eng_id = ?";
        String query2 = "DELETE FROM Engs WHERE eng_id = ?";
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            conn = Conector.conectar();

            ps1 = conn.prepareStatement(query1);
            ps1.setInt(1, eng_id);
            ps1.execute();

            ps2 = conn.prepareStatement(query2);
            ps2.setInt(1, eng_id);
            ps2.execute();

        } catch (Exception e) {
            System.out.println("Prepared statement error...");
        } finally {
            try {
                if (ps1 != null && ps2 != null) {
                    ps1.close();
                    ps2.close();
                }

                if (conn != null)
                    conn.close();

            } catch (Exception e) {
                System.out.println("Connections termination error...");
                e.printStackTrace();
            }
        }
    }

    public void sendUpdateMessageToClient(Car car) {
        String fixedMessage = "Removeu carro: " + car.getCarName();
        client.updateMessage(fixedMessage);  // Envia a mensagem para o Client
    }
}
