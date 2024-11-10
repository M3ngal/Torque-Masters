package org.example.Screens;

import org.example.Configurations.*;
import org.example.CarComponents.*;
import org.example.CustomComponents.CustomPanel;
import org.example.CustomComponents.CustomTextField;
import org.example.CustomComponents.PixelatedButton;
import org.example.CustomComponents.PixelatedSlider;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.*;

public class GarageInterface extends JPanel {
    private Timer timer;
    private Client client;
    private ResourceBundle rb;

    //Contador para exibição dos carros, bloqueio do botão do motor, criação do carro
    private int c = 0, engineCounter = 0, createCounter = 0;

    // JPanels principais
    private JPanel startPanel;
    private JPanel menuPanel;
    private JPanel carPanel;
    private CustomPanel exhibitionPanel;
    private JPanel settingsPanel;

    // Definição das váriaveis para declaração do objeto carro
    private String engineTypeCar;
    private int cylinderAmmount;
    private double cylindersCar;
    private String aspirationCar;
    private String fuelCar;
    private String engineMaterialCar;
    private String tractionCar;

    private String brakesCar;
    private String tiresCar;
    private String chassisCar;
    private String suspensionCar;
    private String colorCar;
    private String carName;
    
    // Card Layout para facilitar a troca entre JPanels na seção de botões
    private CardLayout cardLayout;

    // Slider para controle do volume da música
    private PixelatedSlider volumeSlider;

    // Botões do startPanel (inferior)
    private PixelatedButton startButton;
    private PixelatedButton garageButton;
    private PixelatedButton deleteButton;

    // Botões do settingPanel (superior)
    private PixelatedButton menuButton;
    private PixelatedButton exitButton;

    // Botões do carPanel (inferior)
    private PixelatedButton engineButton;
        private PixelatedButton engineType;
            private PixelatedButton inlineEngine;
            private PixelatedButton boxerEngine;
            private PixelatedButton VEngine;
                private PixelatedButton threeCylinders;
                private PixelatedButton fourCylindersInline;
                private PixelatedButton fourCylindersBoxer;
                private PixelatedButton fiveCylinders;
                private PixelatedButton sixCylindersBoxer;
                private PixelatedButton sixCylindersV;
                private PixelatedButton eightCylinders;
                private PixelatedButton tenCylinders;
                private PixelatedButton twelveCylinders;
        private PixelatedButton cylinders;
            private PixelatedButton firstCylinder;
            private PixelatedButton secondCylinder;
            private PixelatedButton thirdCylinder;
            private PixelatedButton fourthCylinder;
            private PixelatedButton fifthCylinder;
            private PixelatedButton sixthCylinder;
            private PixelatedButton seventhCylinder;
        private PixelatedButton aspiration;
            private PixelatedButton naturalAspiration;
            private PixelatedButton turboCompressor;
            private PixelatedButton superCompressor;
        private PixelatedButton fuel;
            private PixelatedButton gasFuel;
            private PixelatedButton dieselFuel;
        private PixelatedButton engineMaterial;
            private PixelatedButton moltedIron;
            private PixelatedButton aluminiumAlloy;
            private PixelatedButton titaniumAlloy;
        private PixelatedButton traction;
            private PixelatedButton rearTraction;
            private PixelatedButton frontTraction;
            private PixelatedButton integralTraction;

    private PixelatedButton brakesButton;
        private PixelatedButton popularBrakes;
        private PixelatedButton sportBrakes;
        private PixelatedButton raceBrakes;
        private PixelatedButton ceramicBrakes;

    private PixelatedButton tiresButton;
        private PixelatedButton popularTires;
        private PixelatedButton sportTires;
        private PixelatedButton raceTires;
        private PixelatedButton offRoadTires;

    private PixelatedButton chassisButton;
        private PixelatedButton suvChassis;
        private PixelatedButton sedanChassis;
        private PixelatedButton sportChassis;
        private PixelatedButton hatchbackChassis;
        private PixelatedButton coupeChassis;

    private PixelatedButton suspensionButton;
        private PixelatedButton popularSuspension;
        private PixelatedButton sportSuspension;
        private PixelatedButton raceSuspension;
        private PixelatedButton rallySuspension;

    private PixelatedButton bodyPaintButton;
        private PixelatedButton colorRed;
        private PixelatedButton colorBlue;
        private PixelatedButton colorYellow;
        private PixelatedButton colorBlack;

    private PixelatedButton carNameButton;
    private PixelatedButton setCarName;
    private CustomTextField carNameField;

    Color appColor = new Color(13, 6, 40);
    Color buttonColor = new Color(103, 124, 163);
    Color startColor = new Color(41, 40, 45);

    // Construtor da interface gráfica do jogo
    GarageInterface(int userId, GameWindow gameWindow, Client client, Music music, ResourceBundle rb) {
        this.client = client;
        this.rb = rb;

        // Layout settings
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        // Card Layout
        cardLayout = new CardLayout();
        menuPanel = new JPanel(cardLayout);
        menuPanel.setBackground(buttonColor);

        // Start Panel
        startPanel = new JPanel(new GridBagLayout());
        startPanel.setBackground(startColor);
        startPanel.setPreferredSize(new Dimension(900, 75));

        // Settings Panel
        settingsPanel = new JPanel(new GridBagLayout());
        settingsPanel.setBackground(startColor);
        settingsPanel.setPreferredSize(new Dimension(900, 75));

        // Exhibition Panel
        exhibitionPanel = new CustomPanel("images\\capa.jpg");
        exhibitionPanel.setBackground(Color.BLACK);
        exhibitionPanel.setPreferredSize(new Dimension(900, 425));

        // Button Panel
        carPanel = new JPanel(new GridBagLayout());
        carPanel.setBackground(buttonColor);
        carPanel.setPreferredSize(new Dimension(900, 175));

//-----------------------------------------------------Car Components Panels-----------------------------------------------------------------------
        // Engine Panel
        JPanel enginePanel = new JPanel(new GridBagLayout());
        enginePanel.setBackground(buttonColor);
        enginePanel.setPreferredSize(new Dimension(900, 175));

        engineButton = new PixelatedButton(rb.getString("motor"));

        JPanel engineTypePanel = new JPanel(new GridBagLayout());
        engineTypePanel.setBackground(buttonColor);
        engineTypePanel.setPreferredSize(new Dimension(900, 175));

        engineType = new PixelatedButton(rb.getString("tipo-do-motor"));

        threeCylinders = new PixelatedButton("3");
        fourCylindersInline = new PixelatedButton("4");
        fourCylindersBoxer = new PixelatedButton("4");
        fiveCylinders = new PixelatedButton("5");
        sixCylindersBoxer = new PixelatedButton("6");
        sixCylindersV = new PixelatedButton("6");
        eightCylinders = new PixelatedButton("8");
        tenCylinders = new PixelatedButton("10");
        twelveCylinders = new PixelatedButton("12");

        JPanel inlinePanel = new JPanel(new GridBagLayout());
        inlinePanel.setBackground(buttonColor);
        inlinePanel.setPreferredSize(new Dimension(900, 175));

        inlineEngine = new PixelatedButton(rb.getString("em-linha"));

        inlinePanel.add(threeCylinders);
        inlinePanel.add(fourCylindersInline);
        inlinePanel.add(fiveCylinders);

        JPanel boxerPanel = new JPanel(new GridBagLayout());
        boxerPanel.setBackground(buttonColor);
        boxerPanel.setPreferredSize(new Dimension(900, 175));

        boxerEngine = new PixelatedButton("boxer");

        boxerPanel.add(fourCylindersBoxer);
        boxerPanel.add(sixCylindersBoxer);

        JPanel VPanel = new JPanel(new GridBagLayout());
        VPanel.setBackground(buttonColor);
        VPanel.setPreferredSize(new Dimension(900, 175));

        VEngine = new PixelatedButton("V");

        VPanel.add(sixCylindersV);
        VPanel.add(eightCylinders);
        VPanel.add(tenCylinders);
        VPanel.add(twelveCylinders);

        engineTypePanel.add(inlineEngine);
        engineTypePanel.add(boxerEngine);
        engineTypePanel.add(VEngine);

        JPanel cylindersPanel = new JPanel(new GridBagLayout());
        cylindersPanel.setBackground(buttonColor);
        cylindersPanel.setPreferredSize(new Dimension(900, 175));

        cylinders = new PixelatedButton(rb.getString("cilindradas"));

        firstCylinder = new PixelatedButton("1.0");
        secondCylinder = new PixelatedButton("1.6");
        thirdCylinder = new PixelatedButton("2.0");
        fourthCylinder = new PixelatedButton("2.4");
        fifthCylinder = new PixelatedButton("3.0");
        sixthCylinder = new PixelatedButton("3.6");
        seventhCylinder = new PixelatedButton("4.2");

        cylindersPanel.add(firstCylinder);
        cylindersPanel.add(secondCylinder);
        cylindersPanel.add(thirdCylinder);
        cylindersPanel.add(fourthCylinder);
        cylindersPanel.add(fifthCylinder);
        cylindersPanel.add(sixthCylinder);
        cylindersPanel.add(seventhCylinder);

        JPanel aspirationPanel = new JPanel(new GridBagLayout());
        aspirationPanel.setBackground(buttonColor);
        aspirationPanel.setPreferredSize(new Dimension(900, 175));

        aspiration = new PixelatedButton(rb.getString("aspiração"));

        naturalAspiration = new PixelatedButton(rb.getString("aspirado-naturalmente"));
        turboCompressor = new PixelatedButton(rb.getString("turbo-compressor"));
        superCompressor = new PixelatedButton(rb.getString("super-compressor"));

        aspirationPanel.add(naturalAspiration);
        aspirationPanel.add(turboCompressor);
        aspirationPanel.add(superCompressor);
        
        JPanel fuelPanel = new JPanel(new GridBagLayout());
        fuelPanel.setBackground(buttonColor);
        fuelPanel.setPreferredSize(new Dimension(900, 175));

        fuel = new PixelatedButton(rb.getString("combustivel"));

        gasFuel = new PixelatedButton(rb.getString("gasolina"));
        dieselFuel = new PixelatedButton(rb.getString("diesel"));

        fuelPanel.add(gasFuel);
        fuelPanel.add(dieselFuel);

        JPanel engineMaterialPanel = new JPanel(new GridBagLayout());
        engineMaterialPanel.setBackground(buttonColor);
        engineMaterialPanel.setPreferredSize(new Dimension(900, 175));

        engineMaterial = new PixelatedButton("Material");

        moltedIron = new PixelatedButton(rb.getString("ferro-fundido"));
        aluminiumAlloy = new PixelatedButton(rb.getString("liga-de-aluminio"));
        titaniumAlloy = new PixelatedButton(rb.getString("liga-de-titanio"));

        engineMaterialPanel.add(moltedIron);
        engineMaterialPanel.add(aluminiumAlloy);
        engineMaterialPanel.add(titaniumAlloy);

        JPanel tractionPanel = new JPanel(new GridBagLayout());
        tractionPanel.setBackground(buttonColor);
        tractionPanel.setPreferredSize(new Dimension(900, 175));

        traction = new PixelatedButton(rb.getString("tração"));

        rearTraction = new PixelatedButton(rb.getString("traseira"));
        frontTraction = new PixelatedButton(rb.getString("dianteira"));
        integralTraction = new PixelatedButton(rb.getString("integral"));

        tractionPanel.add(rearTraction);
        tractionPanel.add(frontTraction);
        tractionPanel.add(integralTraction);

        enginePanel.add(engineType);
        enginePanel.add(cylinders);
        enginePanel.add(aspiration);
        enginePanel.add(fuel);
        enginePanel.add(engineMaterial);
        enginePanel.add(traction);

        // Brakes Panel
        JPanel brakesPanel = new JPanel(new GridBagLayout());
        brakesPanel.setBackground(buttonColor);
        brakesPanel.setPreferredSize(new Dimension(900, 175));

        brakesButton = new PixelatedButton(rb.getString("freios"));

        popularBrakes = new PixelatedButton(rb.getString("popular"));
        sportBrakes = new PixelatedButton(rb.getString("esportivo"));
        raceBrakes = new PixelatedButton(rb.getString("corrida"));
        ceramicBrakes = new PixelatedButton(rb.getString("ceramica"));

        brakesPanel.add(popularBrakes);
        brakesPanel.add(sportBrakes);
        brakesPanel.add(raceBrakes);
        brakesPanel.add(ceramicBrakes);

        // Tires Panel
        JPanel tiresPanel = new JPanel(new GridBagLayout());
        tiresPanel.setBackground(buttonColor);
        tiresPanel.setPreferredSize(new Dimension(900, 175));

        tiresButton = new PixelatedButton(rb.getString("rodas"));

        popularTires = new PixelatedButton(rb.getString("popular"));
        sportTires = new PixelatedButton(rb.getString("esportivo"));
        raceTires = new PixelatedButton(rb.getString("corrida"));
        offRoadTires = new PixelatedButton(rb.getString("off-road"));

        tiresPanel.add(popularTires);
        tiresPanel.add(sportTires);
        tiresPanel.add(raceTires);
        tiresPanel.add(offRoadTires);

        // Chassis Panel
        JPanel chassisPanel = new JPanel(new GridBagLayout());
        chassisPanel.setBackground(buttonColor);
        chassisPanel.setPreferredSize(new Dimension(900, 175));

        chassisButton = new PixelatedButton(rb.getString("chassi"));

        suvChassis = new PixelatedButton(rb.getString("suv"));
        sedanChassis = new PixelatedButton(rb.getString("sedan"));
        sportChassis = new PixelatedButton(rb.getString("sport"));
        hatchbackChassis = new PixelatedButton(rb.getString("hatchback"));
        coupeChassis = new PixelatedButton(rb.getString("coupe"));

        chassisPanel.add(suvChassis);
        chassisPanel.add(sedanChassis);
        chassisPanel.add(sportChassis);
        chassisPanel.add(hatchbackChassis);
        chassisPanel.add(coupeChassis);
        
        // Suspension Panel
        JPanel suspensionPanel = new JPanel(new GridBagLayout());
        suspensionPanel.setBackground(buttonColor);
        suspensionPanel.setPreferredSize(new Dimension(900, 175));

        suspensionButton = new PixelatedButton(rb.getString("suspensão"));

        popularSuspension = new PixelatedButton(rb.getString("popular"));
        sportSuspension = new PixelatedButton(rb.getString("esportivo"));
        raceSuspension = new PixelatedButton(rb.getString("corrida"));
        rallySuspension = new PixelatedButton(rb.getString("rally"));

        suspensionPanel.add(popularSuspension);
        suspensionPanel.add(sportSuspension);
        suspensionPanel.add(raceSuspension);
        suspensionPanel.add(rallySuspension);

        // Body Paint Panel
        JPanel bodyPaintPanel = new JPanel(new GridBagLayout());
        bodyPaintPanel.setBackground(buttonColor);
        bodyPaintPanel.setPreferredSize(new Dimension(900, 175));

        bodyPaintButton = new PixelatedButton(rb.getString("pintura"));

        colorRed = new PixelatedButton(rb.getString("vermelho"));
        colorYellow = new PixelatedButton(rb.getString("amarelo"));
        colorBlue = new PixelatedButton(rb.getString("azul"));
        colorBlack = new PixelatedButton(rb.getString("preto"));

        bodyPaintPanel.add(colorRed);
        bodyPaintPanel.add(colorYellow);
        bodyPaintPanel.add(colorBlue);
        bodyPaintPanel.add(colorBlack);

        // Car Name Panel
        JPanel carNamePanel = new JPanel(new GridBagLayout());
        carNamePanel.setBackground(buttonColor);
        carNamePanel.setPreferredSize(new Dimension(900, 175));

        carNameButton = new PixelatedButton(rb.getString("nome"));
        setCarName = new PixelatedButton(rb.getString("dar-nome"));
        carNameField = new CustomTextField( 50);

        carNamePanel.add(setCarName);
        carNamePanel.add(carNameField);

        // Start Buttons
        deleteButton = new PixelatedButton(rb.getString("excluir"));
        deleteButton.setPreferredSize(new Dimension(150, 60));
        startButton = new PixelatedButton(rb.getString("novo"));
        startButton.setPreferredSize(new Dimension(150, 60));
        garageButton = new PixelatedButton(rb.getString("garagem"));
        garageButton.setPreferredSize(new Dimension(150, 60));

        // Start Panel
        startPanel.add(deleteButton, gbc); gbc.gridx++;
        startPanel.add(startButton, gbc); gbc.gridx++;
        startPanel.add(garageButton, gbc); gbc.gridx++;

        // Settings Buttons
        menuButton = new PixelatedButton(rb.getString("menu"));
        menuButton.setEnabled(false);

        exitButton = new PixelatedButton(rb.getString("sair"));

        // Volume Slider
        volumeSlider = new PixelatedSlider(music);

        // Settings Panel
        settingsPanel.add(menuButton, gbc); gbc.gridx++;
        settingsPanel.add(volumeSlider, gbc); gbc.gridx++;
        settingsPanel.add(exitButton, gbc); gbc.gridx++;

        carPanel.add(engineButton, gbc); gbc.gridx++;
        carPanel.add(brakesButton, gbc); gbc.gridx++;
        carPanel.add(tiresButton, gbc); gbc.gridx++;
        carPanel.add(chassisButton, gbc); gbc.gridx++;
        carPanel.add(suspensionButton, gbc); gbc.gridx++;
        carPanel.add(bodyPaintButton, gbc); gbc.gridx++;
        carPanel.add(carNameButton, gbc); gbc.gridx++;

        //Database Connection
        Connection conn = null;
        Conector bd = new Conector();

        try {
            conn = bd.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Action Listeners
        Connection finalConn = conn;

        //Acessa os veículos armazenados na database
        garageButton.addActionListener(event -> {
            if (countUserCars(userId) == 0)
                showTemporaryImage(exhibitionPanel, "images//cars_not_found.jpg");

            else
                gameWindow.showWarehouseInterface(userId, client, music, rb);
        });

        //Exclui um carro do banco de dados
        deleteButton.addActionListener(event -> {
            gameWindow.showDupsterInterface(userId, client, music, rb);
        });

        //Inicia a criação de um novo carro
        startButton.addActionListener(event -> {
            if (countUserCars(userId) >= 6)
                showTemporaryImage(exhibitionPanel, "images//car_limit.jpg");

            else {
                cardLayout.show(menuPanel, "carPanel");
                menuButton.setEnabled(true);

                settingsPanel.setBackground(appColor);
                exhibitionPanel.setBackgroundImage("images\\Garagem_pixelada.jpg");
            }
        });

        //Retorna ao menu principal de botões
        menuButton.addActionListener(event -> {
            cardLayout.show(menuPanel, "carPanel");

            if (engineCounter == 6) {
                engineButton.setEnabled(false);
            }
        });

        //Encerra a aplicação
        exitButton.addActionListener(event -> {
            System.exit(0);
        });

// --------------------------Engine--------------------------
        engineButton.addActionListener(event -> cardLayout.show(menuPanel, "enginePanel"));

        engineType.addActionListener(event -> cardLayout.show(menuPanel, "engineType"));

        inlineEngine.addActionListener(event -> {
            cardLayout.show(menuPanel, "inline");
            engineTypeCar = "em-linha";
        });

        threeCylinders.addActionListener(event -> {
            cylinderAmmount = 3;
            engineType.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        fourCylindersInline.addActionListener(event -> {
            cylinderAmmount = 4;
            engineType.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        fiveCylinders.addActionListener(event -> {
            cylinderAmmount = 5;
            engineType.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        boxerEngine.addActionListener(event -> {
            cardLayout.show(menuPanel, "boxer");
            engineTypeCar = "boxer";
        });

        fourCylindersBoxer.addActionListener(event -> {
            cylinderAmmount = 4;
            engineType.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        sixCylindersBoxer.addActionListener(event -> {
            cylinderAmmount = 6;
            engineType.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        VEngine.addActionListener(event -> {
            cardLayout.show(menuPanel, "V");
            engineTypeCar = "V";
        });

        sixCylindersV.addActionListener(event -> {
            cylinderAmmount = 6;
            engineType.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        eightCylinders.addActionListener(event -> {
            cylinderAmmount = 8;
            engineType.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        tenCylinders.addActionListener(event -> {
            cylinderAmmount = 10;
            engineType.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        twelveCylinders.addActionListener(event -> {
            cylinderAmmount = 12;
            engineType.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

// --------------------------Cylinders--------------------------
        cylinders.addActionListener(event -> cardLayout.show(menuPanel, "cylinders"));

        firstCylinder.addActionListener(event -> {
            cylindersCar = 1.0;
            cylinders.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        secondCylinder.addActionListener(event -> {
            cylindersCar = 1.6;
            cylinders.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        thirdCylinder.addActionListener(event -> {
            cylindersCar = 2.0;
            cylinders.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        fourthCylinder.addActionListener(event -> {
            cylindersCar = 2.4;
            cylinders.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        fifthCylinder.addActionListener(event -> {
            cylindersCar = 3.0;
            cylinders.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        sixthCylinder.addActionListener(event -> {
            cylindersCar = 3.6;
            cylinders.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        seventhCylinder.addActionListener(event -> {
            cylindersCar = 4.2;
            cylinders.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

// --------------------------Aspiration--------------------------
        aspiration.addActionListener(event -> cardLayout.show(menuPanel, "aspiration"));

        naturalAspiration.addActionListener(event -> {
            aspirationCar = "aspirado-naturalmente";
            aspiration.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        turboCompressor.addActionListener(event -> {
            aspirationCar = "turbo-compressor";
            aspiration.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        superCompressor.addActionListener(event -> {
            aspirationCar = "super-compressor";
            aspiration.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

// --------------------------Fuel--------------------------
        fuel.addActionListener(event -> cardLayout.show(menuPanel, "fuel"));

        gasFuel.addActionListener(event -> {
            fuelCar = "gasolina";
            fuel.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        dieselFuel.addActionListener(event -> {
            fuelCar = "diesel";
            fuel.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

// --------------------------Engine Material--------------------------
        engineMaterial.addActionListener(event -> cardLayout.show(menuPanel, "engineMaterial"));

        moltedIron.addActionListener(event -> {
            engineMaterialCar = "ferro-fundido";
            engineMaterial.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        aluminiumAlloy.addActionListener(event -> {
            engineMaterialCar = "liga-de-aluminio";
            engineMaterial.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        titaniumAlloy.addActionListener(event -> {
            engineMaterialCar = "liga-de-titanio";
            engineMaterial.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

// --------------------------Traction--------------------------
        traction.addActionListener(event -> cardLayout.show(menuPanel, "traction"));

        rearTraction.addActionListener(event -> {
            tractionCar = "traseira";
            traction.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        frontTraction.addActionListener(event -> {
            tractionCar = "dianteira";
            traction.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        integralTraction.addActionListener(event -> {
            tractionCar = "integral";
            traction.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

// --------------------------Brakes--------------------------
        brakesButton.addActionListener(event -> cardLayout.show(menuPanel, "brakesPanel"));

        popularBrakes.addActionListener(event -> {
            brakesCar = "popular";
            brakesButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        sportBrakes.addActionListener(event -> {
            brakesCar = "esportivo";
            brakesButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        raceBrakes.addActionListener(event -> {
            brakesCar = "corrida";
            brakesButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        ceramicBrakes.addActionListener(event -> {
            brakesCar = "ceramica";
            brakesButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

// --------------------------Tires--------------------------
        tiresButton.addActionListener(event -> cardLayout.show(menuPanel, "tiresPanel"));

        popularTires.addActionListener(event -> {
            tiresCar = "popular";
            tiresButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        sportTires.addActionListener(event -> {
            tiresCar = "esportivo";
            tiresButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        raceTires.addActionListener(event -> {
            tiresCar = "corrida";
            tiresButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        offRoadTires.addActionListener(event -> {
            tiresCar = "off-road";
            tiresButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

// --------------------------Chassis--------------------------
        chassisButton.addActionListener(event -> cardLayout.show(menuPanel, "chassisPanel"));

        suvChassis.addActionListener(event -> {
            chassisCar = "suv";
            chassisButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        sedanChassis.addActionListener(event -> {
            chassisCar = "sedan";
            chassisButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        sportChassis.addActionListener(event -> {
            chassisCar = "esportivo";
            chassisButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        hatchbackChassis.addActionListener(event -> {
            chassisCar = "hatchback";
            chassisButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        coupeChassis.addActionListener(event -> {
            chassisCar = "coupe";
            chassisButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

// --------------------------Suspension--------------------------
        suspensionButton.addActionListener(event -> cardLayout.show(menuPanel, "suspensionPanel"));

        popularSuspension.addActionListener(event -> {
            suspensionCar = "popular";
            suspensionButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        sportSuspension.addActionListener(event -> {
            suspensionCar = "esportivo";
            suspensionButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        raceSuspension.addActionListener(event -> {
            suspensionCar = "corrida";
            suspensionButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        rallySuspension.addActionListener(event -> {
            suspensionCar = "rally";
            suspensionButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

// --------------------------BodyPaint--------------------------
        bodyPaintButton.addActionListener(event -> cardLayout.show(menuPanel, "bodyPaintPanel"));

        colorBlack.addActionListener(event -> {
            colorCar = "Preto";
            bodyPaintButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        colorRed.addActionListener(event -> {
            colorCar = "Vermelho";
            bodyPaintButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        colorBlue.addActionListener(event -> {
            colorCar = "Azul";
            bodyPaintButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

        colorYellow.addActionListener(event -> {
            colorCar = "Amarelo";
            bodyPaintButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });

// --------------------------Car Name--------------------------
        carNameButton.addActionListener(event -> cardLayout.show(menuPanel, "carNamePanel"));

        setCarName.addActionListener(event -> {
            carName = carNameField.getText();
            carNameField.setText("");
            carNameButton.setEnabled(false); createCounter++;
            cardLayout.show(menuPanel, "carPanel");
        });
        
        // Adding Components
        menuPanel.add(startPanel, "startPanel");
        menuPanel.add(carPanel, "carPanel");
        menuPanel.add(enginePanel, "enginePanel");
            menuPanel.add(engineTypePanel, "engineType");
                menuPanel.add(inlinePanel, "inline");
                menuPanel.add(boxerPanel, "boxer");
                menuPanel.add(VPanel, "V");
            menuPanel.add(cylindersPanel, "cylinders");
            menuPanel.add(aspirationPanel, "aspiration");
            menuPanel.add(fuelPanel, "fuel");
            menuPanel.add(engineMaterialPanel, "engineMaterial");
            menuPanel.add(tractionPanel, "traction");
        
        menuPanel.add(brakesPanel, "brakesPanel");
        menuPanel.add(tiresPanel, "tiresPanel");
        menuPanel.add(chassisPanel, "chassisPanel");
        menuPanel.add(suspensionPanel, "suspensionPanel");
        menuPanel.add(bodyPaintPanel, "bodyPaintPanel");
        menuPanel.add(carNamePanel, "carNamePanel");

        // Timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (createCounter == 12) {
                    engineButton.setEnabled(true);
                        engineType.setEnabled(true);
                        engineMaterial.setEnabled(true);
                        cylinders.setEnabled(true);
                        aspiration.setEnabled(  true);
                        fuel.setEnabled(true);
                        engineMaterial.setEnabled(true);
                        traction.setEnabled(true);
                    brakesButton.setEnabled(true);
                    tiresButton.setEnabled(true);
                    chassisButton.setEnabled(true);
                    suspensionButton.setEnabled(true);
                    bodyPaintButton.setEnabled(true);
                    carNameButton.setEnabled(true);

                    settingsPanel.setBackground(startColor);
                    sendUpdateMessageToClient();
                    cardLayout.show(menuPanel, "startPanel");
                    exhibitionPanel.setBackgroundImage("images//capa.jpg");

                    Engine carEngine = new Engine(userId, engineTypeCar, cylinderAmmount, cylindersCar, aspirationCar, fuelCar, engineMaterialCar, tractionCar);
                    Brakes carBrakes = new Brakes(brakesCar);
                    Tires carTires = new Tires(tiresCar);
                    Chassis carChassis = new Chassis(chassisCar);
                    Suspension carSuspension = new Suspension(suspensionCar);
                    BodyPaint carBodyPaint = new BodyPaint(colorCar);

                    Car carrao = new Car(userId, carEngine, carBrakes, carTires, carChassis, carSuspension, carBodyPaint, carName);
                    carEngine.incluir(finalConn);
                    carrao.incluir(finalConn);
                    c++;

                    engineCounter = 0;
                    createCounter = 0;
                    menuButton.setEnabled(false);
                }
            }
        });

        timer.start();

        this.setLayout(new BorderLayout());
        this.add(settingsPanel, BorderLayout.NORTH);
        this.add(exhibitionPanel, BorderLayout.CENTER);
        this.add(menuPanel, BorderLayout.SOUTH);
    }

    public void sendUpdateMessageToClient() {
        String fixedMessage = "Finalizou carro";
        client.updateMessage(fixedMessage);  // Envia a mensagem para o Client
    }

    public int countUserCars(int userId) {
        String sql = "SELECT COUNT(*) FROM Cars WHERE user_id = ?;";
        int count = 0;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rSet = null;

        try {
            conn = Conector.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rSet = ps.executeQuery();

            if (rSet.next()) {
                count = rSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Prepared statement error...");
            e.printStackTrace();

        } finally {
            try {
                if (rSet != null)
                    rSet.close();

                if (ps != null)
                    ps.close();

                if (conn != null)
                    conn.close();

            } catch (Exception e) {
                System.out.println("Connections termination error...");
                e.printStackTrace();
            }
        }

        return count;
    }

    public void showTemporaryImage(CustomPanel exhibitionPanel, String tempImageUrl) {
        exhibitionPanel.setBackgroundImage(tempImageUrl);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exhibitionPanel.setBackgroundImage("images//capa.jpg");
            }
        });

        timer.setRepeats(false);
        timer.start();
    }
}