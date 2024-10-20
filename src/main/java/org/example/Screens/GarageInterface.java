package org.example.Screens;

import org.example.*;
import org.example.CarComponents.*;
import org.example.CustomComponents.CustomPanel;
import org.example.CustomComponents.CustomTextField;
import org.example.CustomComponents.PixelatedButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class GarageInterface extends JPanel {
    //Contador para exibição dos carros, bloqueio do botão do motor, criação do carro
    private int c = 0, engineCounter = 0, createCounter = 0;

    // JPanels principais
    JPanel startPanel;
    JPanel menuPanel;
    JPanel carPanel;
    CustomPanel exhibitionPanel;
    JPanel settingsPanel;
    private Timer timer;

    // Definição das váriaveis para declaração do objeto carro
    String engineTypeCar;
    int cylinderAmmount;
    double cylindersCar;
    String aspirationCar;
    String fuelCar;
    String engineMaterialCar;
    String tractionCar;

    String brakesCar;
    String tiresCar;
    String chassisCar;
    String suspensionCar;
    String colorCar;
    String carName;
    
    // Card Layout para facilitar a troca entre JPanels na seção de botões
    CardLayout cardLayout;

    // Botões do startPanel (inferior)
    PixelatedButton startButton;
    PixelatedButton garageButton;

    // Botões do settingPanel (superior)
    PixelatedButton menuButton;
    PixelatedButton exitButton;

    // Botões do carPanel (inferior)
    PixelatedButton engineButton;
        PixelatedButton engineType;
            PixelatedButton inlineEngine;
            PixelatedButton boxerEngine;
            PixelatedButton VEngine;
                PixelatedButton threeCylinders;
                PixelatedButton fourCylindersInline;
                PixelatedButton fourCylindersBoxer;
                PixelatedButton fiveCylinders;
                PixelatedButton sixCylindersBoxer;
                PixelatedButton sixCylindersV;
                PixelatedButton eightCylinders;
                PixelatedButton tenCylinders;
                PixelatedButton twelveCylinders;
        PixelatedButton cylinders;
            PixelatedButton firstCylinder;
            PixelatedButton secondCylinder;
            PixelatedButton thirdCylinder;
            PixelatedButton fourthCylinder;
            PixelatedButton fifthCylinder;
            PixelatedButton sixthCylinder;
            PixelatedButton seventhCylinder;
        PixelatedButton aspiration;
            PixelatedButton naturalAspiration;
            PixelatedButton turboCompressor;
            PixelatedButton superCompressor;
        PixelatedButton fuel;
            PixelatedButton gasFuel;
            PixelatedButton dieselFuel;
        PixelatedButton engineMaterial;
            PixelatedButton moltedIron;
            PixelatedButton aluminiumAlloy;
            PixelatedButton titaniumAlloy;
        PixelatedButton traction;
            PixelatedButton rearTraction;
            PixelatedButton frontTraction;
            PixelatedButton integralTraction;

    PixelatedButton brakesButton;
        PixelatedButton popularBrakes;
        PixelatedButton sportBrakes;
        PixelatedButton raceBrakes;
        PixelatedButton ceramicBrakes;

    PixelatedButton tiresButton;
        PixelatedButton popularTires;
        PixelatedButton sportTires;
        PixelatedButton raceTires;
        PixelatedButton offRoadTires;

    PixelatedButton chassisButton;
        PixelatedButton suvChassis;
        PixelatedButton sedanChassis;
        PixelatedButton sportChassis;
        PixelatedButton hatchbackChassis;
        PixelatedButton coupeChassis;

    PixelatedButton suspensionButton;
        PixelatedButton popularSuspension;
        PixelatedButton sportSuspension;
        PixelatedButton raceSuspension;
        PixelatedButton rallySuspension;

    PixelatedButton bodyPaintButton;
        PixelatedButton colorRed;
        PixelatedButton colorBlue;
        PixelatedButton colorYellow;
        PixelatedButton colorBlack;

    PixelatedButton carNameButton;
    PixelatedButton setCarName;
    CustomTextField carNameField;

    Color appColor = new Color(13, 6, 40);
    Color buttonColor = new Color(103, 124, 163);
    Color startColor = new Color(41, 40, 45);

    // Construtor da interface gráfica do jogo
    GarageInterface() {
        // Card Layout
        cardLayout = new CardLayout();
        menuPanel = new JPanel(cardLayout);
        menuPanel.setBackground(buttonColor);

        // Start Panel
        startPanel = new JPanel();
        startPanel.setBackground(startColor);
        startPanel.setPreferredSize(new Dimension(900, 75));
        startPanel.setLayout(new FlowLayout(0, 195, 55));

        // Settings Panel
        settingsPanel = new JPanel();
        settingsPanel.setBackground(startColor);
        settingsPanel.setPreferredSize(new Dimension(900, 75));
        settingsPanel.setLayout(new FlowLayout(0, 245, 25));

        // Exhibition Panel
        exhibitionPanel = new CustomPanel("images\\capa.jpg");
        exhibitionPanel.setBackground(Color.BLACK);
        exhibitionPanel.setPreferredSize(new Dimension(900, 425));

        // Button Panel
        carPanel = new JPanel();
        carPanel.setBackground(buttonColor);
        carPanel.setLayout(new FlowLayout(0, 27, 75));
        carPanel.setPreferredSize(new Dimension(900, 175));

//-----------------------------------------------------Car Components Panels-----------------------------------------------------------------------
        // Engine Panel
        JPanel enginePanel = new JPanel();
        enginePanel.setBackground(buttonColor);
        enginePanel.setLayout(new FlowLayout(0, 15, 75));
        enginePanel.setPreferredSize(new Dimension(900, 175));

        engineButton = new PixelatedButton("Motor");

        JPanel engineTypePanel = new JPanel();
        engineTypePanel.setBackground(buttonColor);
        engineTypePanel.setLayout(new FlowLayout(0, 150, 75));
        engineTypePanel.setPreferredSize(new Dimension(900, 175));

        engineType = new PixelatedButton("Tipo de Motor");

        threeCylinders = new PixelatedButton("3");
        fourCylindersInline = new PixelatedButton("4");
        fourCylindersBoxer = new PixelatedButton("4");
        fiveCylinders = new PixelatedButton("5");
        sixCylindersBoxer = new PixelatedButton("6");
        sixCylindersV = new PixelatedButton("6");
        eightCylinders = new PixelatedButton("8");
        tenCylinders = new PixelatedButton("10");
        twelveCylinders = new PixelatedButton("12");

        JPanel inlinePanel = new JPanel();
        inlinePanel.setBackground(buttonColor);
        inlinePanel.setLayout(new FlowLayout(0, 185, 75));
        inlinePanel.setPreferredSize(new Dimension(900, 175));

        inlineEngine = new PixelatedButton("Em Linha");

        inlinePanel.add(threeCylinders);
        inlinePanel.add(fourCylindersInline);
        inlinePanel.add(fiveCylinders);

        JPanel boxerPanel = new JPanel();
        boxerPanel.setBackground(buttonColor);
        boxerPanel.setLayout(new FlowLayout(0, 260, 75));
        boxerPanel.setPreferredSize(new Dimension(900, 175));

        boxerEngine = new PixelatedButton("Boxer");

        boxerPanel.add(fourCylindersBoxer);
        boxerPanel.add(sixCylindersBoxer);

        JPanel VPanel = new JPanel();
        VPanel.setBackground(buttonColor);
        VPanel.setLayout(new FlowLayout(0, 140, 75));
        VPanel.setPreferredSize(new Dimension(900, 175));

        VEngine = new PixelatedButton("V");

        VPanel.add(sixCylindersV);
        VPanel.add(eightCylinders);
        VPanel.add(tenCylinders);
        VPanel.add(twelveCylinders);

        engineTypePanel.add(inlineEngine);
        engineTypePanel.add(boxerEngine);
        engineTypePanel.add(VEngine);

        JPanel cylindersPanel = new JPanel();
        cylindersPanel.setBackground(buttonColor);
        cylindersPanel.setLayout(new FlowLayout(0, 55, 75));
        cylindersPanel.setPreferredSize(new Dimension(900, 175));

        cylinders = new PixelatedButton("Cilindradas");

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

        JPanel aspirationPanel = new JPanel();
        aspirationPanel.setBackground(buttonColor);
        aspirationPanel.setLayout(new FlowLayout(0, 60, 75));
        aspirationPanel.setPreferredSize(new Dimension(900, 175));

        aspiration = new PixelatedButton("Aspiração");

        naturalAspiration = new PixelatedButton("Aspirado Naturalmente");
        turboCompressor = new PixelatedButton("Turbo Compressor");
        superCompressor = new PixelatedButton("Super Compressor");

        aspirationPanel.add(naturalAspiration);
        aspirationPanel.add(turboCompressor);
        aspirationPanel.add(superCompressor);
        
        JPanel fuelPanel = new JPanel();
        fuelPanel.setBackground(buttonColor);
        fuelPanel.setLayout(new FlowLayout(0, 225, 75));
        fuelPanel.setPreferredSize(new Dimension(900, 175));

        fuel = new PixelatedButton("Combustivel");

        gasFuel = new PixelatedButton("Gasolina");
        dieselFuel = new PixelatedButton("Diesel");

        fuelPanel.add(gasFuel);
        fuelPanel.add(dieselFuel);

        JPanel engineMaterialPanel = new JPanel();
        engineMaterialPanel.setBackground(buttonColor);
        engineMaterialPanel.setLayout(new FlowLayout(0, 80, 75));
        engineMaterialPanel.setPreferredSize(new Dimension(900, 175));

        engineMaterial = new PixelatedButton("Material");

        moltedIron = new PixelatedButton("Ferro Fundido");
        aluminiumAlloy = new PixelatedButton("Liga de Aluminio");
        titaniumAlloy = new PixelatedButton("Liga de Titanio");

        engineMaterialPanel.add(moltedIron);
        engineMaterialPanel.add(aluminiumAlloy);
        engineMaterialPanel.add(titaniumAlloy);

        JPanel tractionPanel = new JPanel();
        tractionPanel.setBackground(buttonColor);
        tractionPanel.setLayout(new FlowLayout(0, 135, 75));
        tractionPanel.setPreferredSize(new Dimension(900, 175));

        traction = new PixelatedButton("Tração");

        rearTraction = new PixelatedButton("Traseira");
        frontTraction = new PixelatedButton("Dianteira");
        integralTraction = new PixelatedButton("Integral");

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
        JPanel brakesPanel = new JPanel();
        brakesPanel.setBackground(buttonColor);
        brakesPanel.setLayout(new FlowLayout(0, 90, 75));
        brakesPanel.setPreferredSize(new Dimension(900, 175));

        brakesButton = new PixelatedButton("Freios");

        popularBrakes = new PixelatedButton("Popular");
        sportBrakes = new PixelatedButton("Esportivo");
        raceBrakes = new PixelatedButton("Corrida");
        ceramicBrakes = new PixelatedButton("Ceramica");

        brakesPanel.add(popularBrakes);
        brakesPanel.add(sportBrakes);
        brakesPanel.add(raceBrakes);
        brakesPanel.add(ceramicBrakes);

        // Tires Panel
        JPanel tiresPanel = new JPanel();
        tiresPanel.setBackground(buttonColor);
        tiresPanel.setLayout(new FlowLayout(0, 90, 75));
        tiresPanel.setPreferredSize(new Dimension(900, 175));

        tiresButton = new PixelatedButton("Rodas");

        popularTires = new PixelatedButton("Popular");
        sportTires = new PixelatedButton("Esportivo");
        raceTires = new PixelatedButton("Corrida");
        offRoadTires = new PixelatedButton("Off-Road");

        tiresPanel.add(popularTires);
        tiresPanel.add(sportTires);
        tiresPanel.add(raceTires);
        tiresPanel.add(offRoadTires);

        // Chassis Panel
        JPanel chassisPanel = new JPanel();
        chassisPanel.setBackground(buttonColor);
        chassisPanel.setLayout(new FlowLayout(0, 75, 75));
        chassisPanel.setPreferredSize(new Dimension(900, 175));

        chassisButton = new PixelatedButton("Chassi");

        suvChassis = new PixelatedButton("SUV");
        sedanChassis = new PixelatedButton("Sedan");
        sportChassis = new PixelatedButton("Sport");
        hatchbackChassis = new PixelatedButton("HatchBack");
        coupeChassis = new PixelatedButton("Coupe");

        chassisPanel.add(suvChassis);
        chassisPanel.add(sedanChassis);
        chassisPanel.add(sportChassis);
        chassisPanel.add(hatchbackChassis);
        chassisPanel.add(coupeChassis);
        
        // Suspension Panel
        JPanel suspensionPanel = new JPanel();
        suspensionPanel.setBackground(buttonColor);
        suspensionPanel.setLayout(new FlowLayout(0, 90, 75));
        suspensionPanel.setPreferredSize(new Dimension(900, 175));

        suspensionButton = new PixelatedButton("Suspenssão");

        popularSuspension = new PixelatedButton("Popular");
        sportSuspension = new PixelatedButton("Esportivo");
        raceSuspension = new PixelatedButton("Corrida");
        rallySuspension = new PixelatedButton("Rally");

        suspensionPanel.add(popularSuspension);
        suspensionPanel.add(sportSuspension);
        suspensionPanel.add(raceSuspension);
        suspensionPanel.add(rallySuspension);

        // Body Paint Panel
        JPanel bodyPaintPanel = new JPanel();
        bodyPaintPanel.setBackground(buttonColor);
        bodyPaintPanel.setLayout(new FlowLayout(0, 90, 75));
        bodyPaintPanel.setPreferredSize(new Dimension(900, 175));

        bodyPaintButton = new PixelatedButton("Pintura");

        colorRed = new PixelatedButton("Vermelho");
        colorYellow = new PixelatedButton("Amarelo");
        colorBlue = new PixelatedButton("Azul");
        colorBlack = new PixelatedButton("Preto");

        bodyPaintPanel.add(colorRed);
        bodyPaintPanel.add(colorYellow);
        bodyPaintPanel.add(colorBlue);
        bodyPaintPanel.add(colorBlack);

        // Car Name Panel
        JPanel carNamePanel = new JPanel();
        carNamePanel.setBackground(buttonColor);
        carNamePanel.setLayout(new FlowLayout(0, 60, 75));
        carNamePanel.setPreferredSize(new Dimension(900, 175));

        carNameButton = new PixelatedButton("Name");

        setCarName = new PixelatedButton("Set Name");
        carNameField = new CustomTextField( 50);

        carNamePanel.add(setCarName);
        carNamePanel.add(carNameField);

        // Start Buttons
        startButton = new PixelatedButton("New");
        startButton.setPreferredSize(new Dimension(150, 60));
        garageButton = new PixelatedButton("Garage");
        garageButton.setPreferredSize(new Dimension(150, 60));

        // Start Panel
        startPanel.add(startButton);
        startPanel.add(garageButton);

        // Settings Buttons
        menuButton = new PixelatedButton("Menu");
        exitButton = new PixelatedButton("Exit");

        // Settings Panel
        settingsPanel.add(menuButton);
        settingsPanel.add(exitButton);

        carPanel.add(engineButton);
        carPanel.add(brakesButton);
        carPanel.add(tiresButton);
        carPanel.add(chassisButton);
        carPanel.add(suspensionButton);
        carPanel.add(bodyPaintButton);
        carPanel.add(carNameButton);

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
            GarageInterface.mostrarCarros(finalConn);
        });

        //Inicia a criação de um novo carro
        startButton.addActionListener(event -> {
            cardLayout.show(menuPanel, "carPanel");

            settingsPanel.setBackground(appColor);
            exhibitionPanel.setBackgroundImage("images\\Garagem_pixelada.jpg");
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
            System.exit(1);
        });

// --------------------------Engine--------------------------
        engineButton.addActionListener(event -> cardLayout.show(menuPanel, "enginePanel"));

        engineType.addActionListener(event -> cardLayout.show(menuPanel, "engineType"));

        inlineEngine.addActionListener(event -> {
            cardLayout.show(menuPanel, "inline");
            engineTypeCar = "em linha";
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
            aspirationCar = "aspirado naturalmente";
            aspiration.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        turboCompressor.addActionListener(event -> {
            aspirationCar = "turbo compressor";
            aspiration.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        superCompressor.addActionListener(event -> {
            aspirationCar = "super compressor";
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
            engineMaterialCar = "ferro fundido";
            engineMaterial.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        aluminiumAlloy.addActionListener(event -> {
            engineMaterialCar = "liga de aluminio";
            engineMaterial.setEnabled(false); engineCounter++; createCounter++;
            cardLayout.show(menuPanel, "enginePanel");
        });

        titaniumAlloy.addActionListener(event -> {
            engineMaterialCar = "liga de titanio";
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
        
        // Add panels to CardLayout
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
                    cardLayout.show(menuPanel, "startPanel");
                    exhibitionPanel.setBackgroundImage("images//capa.jpg");

                    Engine carEngine = new Engine(engineTypeCar, cylinderAmmount, cylindersCar, aspirationCar, fuelCar, engineMaterialCar, tractionCar);
                    Brakes carBrakes = new Brakes(brakesCar);
                    Tires carTires = new Tires(tiresCar);
                    Chassis carChassis = new Chassis(chassisCar);
                    Suspension carSuspension = new Suspension(suspensionCar);
                    BodyPaint carBodyPaint = new BodyPaint(colorCar);

                    Car carrao = new Car(carEngine, carBrakes, carTires, carChassis, carSuspension, carBodyPaint, carName);
                    carrao.setStats();
                    carEngine.incluir(finalConn);
                    carrao.incluir(finalConn);
                    c++;

                    // Criação e escrita no arquivo texto
                    CreateTextFile fileWriter = new CreateTextFile();
                    fileWriter.openFile(); // Abre o arquivo
                    fileWriter.addRecord(carrao.toString()); // Escreve a string resultante no arquivo
                    fileWriter.closeFile(); // Fecha o arquivo

                    engineCounter = 0;
                    createCounter = 0;
                }
            }
        });

        timer.start();

        // Game window
        this.setLayout(new BorderLayout());

        this.add(settingsPanel, BorderLayout.NORTH);
        this.add(exhibitionPanel, BorderLayout.CENTER);
        this.add(menuPanel, BorderLayout.SOUTH);
    }

    public static void mostrarCarros(Connection conn) {
        JFrame fr = new JFrame();
        String sql = "SELECT eng_id, brakes, tires, chassis, suspension, name FROM cars";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs =  pstmt.executeQuery()) {

            while (rs.next()) {
                int id_engine = rs.getInt("id_engine");
                String brakes = rs.getString("brakes");
                String tires = rs.getString("tires");
                String chassis = rs.getString("chassis");
                String suspension = rs.getString("suspension");
                String carName = rs.getString("name");

                String carInfo = String.format("Car{eng_id='%s', brakes='%s', tires='%s', chassis='%s', suspension='%s', name='%s'}",
                        id_engine, brakes, tires, chassis, suspension, carName);

                ShowPane.show(fr, carInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}