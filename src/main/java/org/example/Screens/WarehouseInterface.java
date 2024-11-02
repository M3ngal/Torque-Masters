package org.example.Screens;

import org.example.Configurations.Client;
import org.example.CarComponents.*;
import org.example.Configurations.Conector;
import org.example.CustomComponents.CustomPanel;
import org.example.CustomComponents.PixelatedWarehouseButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseInterface extends CustomPanel {
    private int y = 0;
    private Client client;

    private JLabel warehouseLabel;
    private JLabel carNameLabel;
    private JLabel engineComponentsLabel;
    private JLabel carComponentsLabel;
    private JLabel statsLabel;
    private JLabel typeLabel;
    private JLabel cylAmtLabel;
    private JLabel cylindersLabel;
    private JLabel aspirationLabel;
    private JLabel fuelLabel;
    private JLabel materialLabel;
    private JLabel tractionLabel;
    private JLabel brakesLabel;
    private JLabel tiresLabel;
    private JLabel chassisLabel;
    private JLabel suspensionLabel;

    private JLabel costLabel;
    private JLabel consumptionLabel;
    private JLabel weightLabel;
    private JLabel maxSpeedLabel;
    private JLabel accelerationLabel;
    private JLabel torqueLabel;
    private JLabel powerLabel;
    private JLabel handlingLabel;
    private JLabel brakesPowerLabel;

    private JPanel userCarsPanel;
    private JPanel carInfoPanel;

    private PixelatedWarehouseButton returnButton;
    private PixelatedWarehouseButton exitButton;
    private PixelatedWarehouseButton backButton;

    Color titleColor = new Color(33, 33, 33);
    Color backgroundColor = new Color(249, 253, 221);
    Color warehouseColor = new Color(41, 40, 45, 210);
    Color statsColor = new Color(26, 71, 133);

    Font statsFont = new Font("Arial", Font.BOLD, 14);
    Font carFont = new Font("Arial", Font.BOLD, 20);
    Font engineFont = new Font("Arial", Font.BOLD, 16);
    Font titleLabelFont = new Font("Arial", Font.BOLD, 26);

    WarehouseInterface(int userId, GameWindow gameWindow, Client client) {
        super("images//warehouse.jpg");
        this.client = client;
        this.setLayout(null);

        // Warehouse Title Label
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

        // User Cars Panel
        userCarsPanel = new JPanel();
        userCarsPanel.setLayout(null);
        userCarsPanel.setBackground(warehouseColor);
        userCarsPanel.setBounds(70, 140, 750, 455);
        userCarsPanel.setVisible(true);

        // Return Button
        returnButton = new PixelatedWarehouseButton("Menu");
        returnButton.setBounds(20, 40, 80, 30);

        // Exit Button
        exitButton = new PixelatedWarehouseButton("Exit");
        exitButton.setBounds(785, 40, 80, 30);

        // Database Connection
        Connection conn = null;
        Conector bd = new Conector();

        try {
            conn = bd.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Connection finalConn = conn;

        // Display Cars in Database through carInfosPanel
        for (Car c : readDatabase(finalConn, userId)) {
            PixelatedWarehouseButton carButton = new PixelatedWarehouseButton(c.getCarName());
            carButton.setBounds(25, 5 + y, 700, 70);
            y += 75;

            // Set Car Stats
            c.setStats();

            // Car Infos Panel
            carInfoPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2D = (Graphics2D) g;

                    g2D.setPaint(titleColor);
                    g2D.fillRect(20, 80, 710, 100);
                    g2D.fillRect(20, 200, 335, 235);
                    g2D.fillRect(395, 200, 335, 235);

                    // Components Rectangles
                    g2D.setPaint(Color.BLACK);
                    g2D.setStroke(new BasicStroke(5));
                    g2D.drawRect(20, 80, 710, 100);
                    g2D.drawRect(20, 200, 335, 235);
                    g2D.drawLine(375, 200, 375, 435);
                    g2D.drawRect(395, 200, 335, 235);

                    // Stats Rectangles Border
                    g2D.setStroke(new BasicStroke(2));
                    g2D.drawRect(520, 250, 200, 10);
                    g2D.drawRect(520, 270, 200, 10);
                    g2D.drawRect(520, 290, 200, 10);
                    g2D.drawRect(520, 310, 200, 10);
                    g2D.drawRect(520, 330, 200, 10);
                    g2D.drawRect(520, 350, 200, 10);
                    g2D.drawRect(520, 370, 200, 10);
                    g2D.drawRect(520, 390, 200, 10);
                    g2D.drawRect(520, 410, 200, 10);

                    // Stats Fill Rectangles
                    g2D.setPaint(statsColor);
                    g2D.fillRect(520, 250, Math.min((int)(c.getCost() * 0.0013), 200), 10);
                    g2D.fillRect(520, 270, Math.min((int)(c.getConsumption() * 9.09), 200), 10);
                    g2D.fillRect(520, 290, Math.min((int)(c.getWeight() * 0.1052), 200), 10);
                    g2D.fillRect(520, 310, Math.min((int)(c.getMaxSpeed() * 0.333), 200), 10);
                    g2D.fillRect(520, 330, Math.min((int)(c.getAcceleration() * 22.222), 200), 10);
                    g2D.fillRect(520, 350, Math.min((int)(c.getHandling() * 66.667), 200), 10);
                    g2D.fillRect(520, 370, Math.min((int)(c.getPower() * 0.181), 200), 10);
                    g2D.fillRect(520, 390, Math.min((int)(c.getTorque() * 0.1052), 200), 10);
                    g2D.fillRect(520, 410, Math.min((int)(c.getBrakesPower() * 50), 200), 10);
                }
            };
            carInfoPanel.setLayout(null);
            carInfoPanel.setBackground(warehouseColor);
            carInfoPanel.setBounds(70, 140, 750, 455);
            carInfoPanel.setVisible(false);

            carButton.addActionListener(event -> {
                sendUpdateMessageToClient(c);
                userCarsPanel.setVisible(false);
                carInfoPanel.setVisible(true);
                carInfoPanel.removeAll();
                revalidate();
                repaint();

                // Car Name Label
                try {
                    File titleFontFile = new File("fonts//Satisfy-Regular.ttf");
                    Font titleFont = Font.createFont(Font.TRUETYPE_FONT, titleFontFile).deriveFont(34f);
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(titleFont);

                    // Title
                    carNameLabel = new JLabel(c.getCarName());
                    carNameLabel.setFont(titleFont);
                    carNameLabel.setOpaque(true);
                    carNameLabel.setForeground(backgroundColor);
                    carNameLabel.setBackground(titleColor);
                    carNameLabel.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
                    carNameLabel.setBounds(10, 10, carNameLabel.getPreferredSize().width + 20, 50);
                    carNameLabel.setVerticalAlignment(JLabel.CENTER);
                    carNameLabel.setHorizontalAlignment(JLabel.CENTER);

                } catch (IOException | FontFormatException e) {
                    e.printStackTrace();
                }

                // Back Button
                backButton = new PixelatedWarehouseButton("Back");
                backButton.setBounds(655, 15, 80, 30);
                backButton.addActionListener(e -> {
                    userCarsPanel.setVisible(true);
                    carInfoPanel.setVisible(false);
                });

                // Engine Labels
                engineComponentsLabel = new JLabel("Engine");
                engineComponentsLabel.setForeground(backgroundColor);
                engineComponentsLabel.setBounds(18, 78, 110, 40);
                engineComponentsLabel.setFont(titleLabelFont);
                engineComponentsLabel.setOpaque(true);
                engineComponentsLabel.setVerticalAlignment(JLabel.CENTER);
                engineComponentsLabel.setHorizontalAlignment(JLabel.CENTER);
                engineComponentsLabel.setBackground(Color.DARK_GRAY);
                engineComponentsLabel.setBorder(new LineBorder(Color.BLACK, 5));

                typeLabel = new JLabel("Type: " + c.getEngineType());
                typeLabel.setForeground(backgroundColor);
                typeLabel.setBounds(30, 140, 230, 30);
                typeLabel.setFont(engineFont);

                cylAmtLabel = new JLabel("Cyl Amt: " + c.getCylinderAmount());
                cylAmtLabel.setForeground(backgroundColor);
                cylAmtLabel.setBounds(430, 85, 210, 30);
                cylAmtLabel.setFont(engineFont);

                cylindersLabel = new JLabel("Cylinders: " + c.getCylinders());
                cylindersLabel.setForeground(backgroundColor);
                cylindersLabel.setBounds(590, 85, 210, 30);
                cylindersLabel.setFont(engineFont);

                aspirationLabel = new JLabel("Asp: " + c.getAspiration());
                aspirationLabel.setForeground(backgroundColor);
                aspirationLabel.setBounds(155, 85, 250, 30);
                aspirationLabel.setFont(engineFont);

                fuelLabel = new JLabel("Fuel: " + c.getFuel());
                fuelLabel.setForeground(backgroundColor);
                fuelLabel.setBounds(195, 140, 210, 30);
                fuelLabel.setFont(engineFont);

                materialLabel = new JLabel("Material: " + c.getMaterial());
                materialLabel.setForeground(backgroundColor);
                materialLabel.setBounds(350, 140, 210, 30);
                materialLabel.setFont(engineFont);

                tractionLabel = new JLabel("Traction: " + c.getTraction());
                tractionLabel.setForeground(backgroundColor);
                tractionLabel.setBounds(570, 140, 210, 30);
                tractionLabel.setFont(engineFont);

                // Car Labels
                carComponentsLabel = new JLabel("Car");
                carComponentsLabel.setForeground(backgroundColor);
                carComponentsLabel.setBounds(18, 198, 70, 40);
                carComponentsLabel.setFont(titleLabelFont);
                carComponentsLabel.setOpaque(true);
                carComponentsLabel.setVerticalAlignment(JLabel.CENTER);
                carComponentsLabel.setHorizontalAlignment(JLabel.CENTER);
                carComponentsLabel.setBackground(Color.DARK_GRAY);
                carComponentsLabel.setBorder(new LineBorder(Color.BLACK, 5));

                brakesLabel = new JLabel("Brakes: " + c.getBrakes());
                brakesLabel.setForeground(backgroundColor);
                brakesLabel.setBounds(30, 250, 320, 30);
                brakesLabel.setFont(carFont);

                tiresLabel = new JLabel("Tires: " + c.getTires());
                tiresLabel.setForeground(backgroundColor);
                tiresLabel.setBounds(30, 300, 320, 30);
                tiresLabel.setFont(carFont);

                chassisLabel = new JLabel("Chassis: " + c.getChassis());
                chassisLabel.setForeground(backgroundColor);
                chassisLabel.setBounds(30, 350, 320, 30);
                chassisLabel.setFont(carFont);

                suspensionLabel = new JLabel("Suspension: " + c.getSuspension());
                suspensionLabel.setForeground(backgroundColor);
                suspensionLabel.setBounds(30, 400, 320, 30);
                suspensionLabel.setFont(carFont);

                // Stats Labels
                statsLabel = new JLabel("Stats");
                statsLabel.setForeground(backgroundColor);
                statsLabel.setBounds(395, 198, 100, 40);
                statsLabel.setFont(titleLabelFont);
                statsLabel.setOpaque(true);
                statsLabel.setVerticalAlignment(JLabel.CENTER);
                statsLabel.setHorizontalAlignment(JLabel.CENTER);
                statsLabel.setBackground(Color.DARK_GRAY);
                statsLabel.setBorder(new LineBorder(Color.BLACK, 5));

                costLabel = new JLabel("Cost:");
                costLabel.setForeground(backgroundColor);
                costLabel.setBounds(390, 240, 120, 30);
                costLabel.setFont(statsFont);
                costLabel.setHorizontalAlignment(JLabel.RIGHT);

                consumptionLabel = new JLabel("Consumption:");
                consumptionLabel.setForeground(backgroundColor);
                consumptionLabel.setBounds(390, 260, 120, 30);
                consumptionLabel.setFont(statsFont);
                consumptionLabel.setHorizontalAlignment(JLabel.RIGHT);

                weightLabel = new JLabel("Weight:");
                weightLabel.setForeground(backgroundColor);
                weightLabel.setBounds(390, 280, 120, 30);
                weightLabel.setFont(statsFont);
                weightLabel.setHorizontalAlignment(JLabel.RIGHT);

                maxSpeedLabel = new JLabel("Max Speed:");
                maxSpeedLabel.setForeground(backgroundColor);
                maxSpeedLabel.setBounds(390, 300, 120, 30);
                maxSpeedLabel.setFont(statsFont);
                maxSpeedLabel.setHorizontalAlignment(JLabel.RIGHT);

                accelerationLabel = new JLabel("Acceleration:");
                accelerationLabel.setForeground(backgroundColor);
                accelerationLabel.setBounds(390, 320, 120, 30);
                accelerationLabel.setFont(statsFont);
                accelerationLabel.setHorizontalAlignment(JLabel.RIGHT);

                torqueLabel = new JLabel("Torque:");
                torqueLabel.setForeground(backgroundColor);
                torqueLabel.setBounds(390, 340, 120, 30);
                torqueLabel.setFont(statsFont);
                torqueLabel.setHorizontalAlignment(JLabel.RIGHT);

                powerLabel = new JLabel("Power:");
                powerLabel.setForeground(backgroundColor);
                powerLabel.setBounds(390, 360, 120, 30);
                powerLabel.setFont(statsFont);
                powerLabel.setHorizontalAlignment(JLabel.RIGHT);

                handlingLabel = new JLabel("Handling:");
                handlingLabel.setForeground(backgroundColor);
                handlingLabel.setBounds(390, 380, 120, 30);
                handlingLabel.setFont(statsFont);
                handlingLabel.setHorizontalAlignment(JLabel.RIGHT);

                brakesPowerLabel = new JLabel("Brakes Power:");
                brakesPowerLabel.setForeground(backgroundColor);
                brakesPowerLabel.setBounds(390, 400, 120, 30);
                brakesPowerLabel.setFont(statsFont);
                brakesPowerLabel.setHorizontalAlignment(JLabel.RIGHT);

                // Adding components
                carInfoPanel.add(carNameLabel);
                carInfoPanel.add(engineComponentsLabel);
                carInfoPanel.add(carComponentsLabel);
                carInfoPanel.add(statsLabel);
                carInfoPanel.add(backButton);
                carInfoPanel.add(typeLabel);
                carInfoPanel.add(cylAmtLabel);
                carInfoPanel.add(cylindersLabel);
                carInfoPanel.add(aspirationLabel);
                carInfoPanel.add(fuelLabel);
                carInfoPanel.add(materialLabel);
                carInfoPanel.add(tractionLabel);
                carInfoPanel.add(brakesLabel);
                carInfoPanel.add(tiresLabel);
                carInfoPanel.add(chassisLabel);
                carInfoPanel.add(suspensionLabel);
                carInfoPanel.add(costLabel);
                carInfoPanel.add(consumptionLabel);
                carInfoPanel.add(weightLabel);
                carInfoPanel.add(maxSpeedLabel);
                carInfoPanel.add(accelerationLabel);
                carInfoPanel.add(torqueLabel);
                carInfoPanel.add(powerLabel);
                carInfoPanel.add(handlingLabel);
                carInfoPanel.add(brakesPowerLabel);
            });

            userCarsPanel.add(carButton);
        }

        //Action Listeners
        returnButton.addActionListener(event -> {
            gameWindow.showGarageInterface(userId, client);
        });

        exitButton.addActionListener(event -> {
            System.exit(0);
        });


        //Adding Components
        this.add(exitButton);
        this.add(returnButton);
        this.add(warehouseLabel);
        this.add(userCarsPanel);
        this.add(carInfoPanel);
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

    public void sendUpdateMessageToClient(Car car) {
        String fixedMessage = "Acessou carro: " + car.getCarName();
        client.updateMessage(fixedMessage);  // Envia a mensagem para o Client
    }
}
