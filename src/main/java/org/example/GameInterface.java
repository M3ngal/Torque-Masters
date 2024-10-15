package org.example;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Flow;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class GameInterface extends JFrame implements ActionListener {
    // JPanels principais
    JPanel menuPanel;
    JPanel carPanel;
    CustomPanel exhibitionPanel;
    JPanel settingsPanel;

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
    
    // Card Layout para facilitar a troca entre JPanels na seção de botões
    CardLayout cardLayout;

    // Botões do settingPanel (superior)
    PixelatedButton dataBaseButton;
    PixelatedButton menuButton;
    PixelatedButton statsButton;
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
    
    Color appColor = new Color(13, 6, 40);
    Color buttonColor = new Color(103, 124, 163);

    // Panel customizado para inserção de imagem de background
    public class CustomPanel extends JPanel {
    private Image backgroundImage;

    public CustomPanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a imagem" + e.getMessage());
        }
    }

    // Exibição da imagem de fundo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}

    // Botão customizado
    class PixelatedButton extends JButton {
        public PixelatedButton(String label) {
            super(label);
            this.setFont(new Font("Monospaced", Font.BOLD, 16));
            this.setForeground(Color.BLACK);
            this.setFocusPainted(false);
            this.setBorderPainted(false);
            this.setContentAreaFilled(false);
            this.setOpaque(false);
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        // Exibição do botão customizado
        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(new Color(91, 85, 136));
            } else {
                g.setColor(new Color(91, 85, 136));
            }
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
    }

    // Construtor da interface gráfica do jogo
    GameInterface() {
        // Card Layout
        cardLayout = new CardLayout();
        menuPanel = new JPanel(cardLayout);
        menuPanel.setBackground(buttonColor);

        // Settings Panel
        settingsPanel = new JPanel();
        settingsPanel.setBackground(appColor);
        settingsPanel.setPreferredSize(new Dimension(900, 75));
        settingsPanel.setLayout(new FlowLayout(0, 100, 25));

        // Exhibition Panel
        exhibitionPanel = new CustomPanel("images\\Garagem_pixelada.jpg");
        exhibitionPanel.setBackground(Color.BLACK);
        exhibitionPanel.setPreferredSize(new Dimension(900, 425));

        // Button Panel
        carPanel = new JPanel();
        carPanel.setBackground(buttonColor);
        carPanel.setLayout(new FlowLayout(0, 35, 75));
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

        // Buttons
        dataBaseButton = new PixelatedButton("Data Base");
        menuButton = new PixelatedButton("Menu");
        statsButton = new PixelatedButton("Stats");
        exitButton = new PixelatedButton("Exit");

        // Add buttons to panels
        settingsPanel.add(dataBaseButton);
        settingsPanel.add(menuButton);
        settingsPanel.add(statsButton);
        settingsPanel.add(exitButton);

        carPanel.add(engineButton);
        carPanel.add(brakesButton);
        carPanel.add(tiresButton);
        carPanel.add(chassisButton);
        carPanel.add(suspensionButton);
        carPanel.add(bodyPaintButton);

        // Action Listeners
        dataBaseButton.addActionListener(this);
        menuButton.addActionListener(this);
        exitButton.addActionListener(this);
        statsButton.addActionListener(this);
        
        engineButton.addActionListener(this);
        engineType.addActionListener(this);
        inlineEngine.addActionListener(this);
        boxerEngine.addActionListener(this);
        VEngine.addActionListener(this);
        threeCylinders.addActionListener(this);
        fourCylindersInline.addActionListener(this);
        fourCylindersBoxer.addActionListener(this);
        fiveCylinders.addActionListener(this);
        sixCylindersBoxer.addActionListener(this);
        sixCylindersV.addActionListener(this);
        eightCylinders.addActionListener(this);
        tenCylinders.addActionListener(this);
        twelveCylinders.addActionListener(this);
        cylinders.addActionListener(this);
        firstCylinder.addActionListener(this);
        secondCylinder.addActionListener(this);
        thirdCylinder.addActionListener(this);
        fourthCylinder.addActionListener(this);
        fifthCylinder.addActionListener(this);
        sixthCylinder.addActionListener(this);
        seventhCylinder.addActionListener(this);
        aspiration.addActionListener(this);
        naturalAspiration.addActionListener(this);
        turboCompressor.addActionListener(this);
        superCompressor.addActionListener(this);
        fuel.addActionListener(this);
        gasFuel.addActionListener(this);
        dieselFuel.addActionListener(this);
        engineMaterial.addActionListener(this);
        moltedIron.addActionListener(this);
        aluminiumAlloy.addActionListener(this);
        titaniumAlloy.addActionListener(this);
        traction.addActionListener(this);
        rearTraction.addActionListener(this);
        frontTraction.addActionListener(this);
        integralTraction.addActionListener(this);
        
        brakesButton.addActionListener(this);
        popularBrakes.addActionListener(this);
        sportBrakes.addActionListener(this);
        raceBrakes.addActionListener(this);
        ceramicBrakes.addActionListener(this);
        
        tiresButton.addActionListener(this);
        popularTires.addActionListener(this);
        sportTires.addActionListener(this);
        raceTires.addActionListener(this);
        offRoadTires.addActionListener(this);
        
        chassisButton.addActionListener(this);
        suvChassis.addActionListener(this);
        sedanChassis.addActionListener(this);
        sportChassis.addActionListener(this);
        hatchbackChassis.addActionListener(this);
        coupeChassis.addActionListener(this);
        
        suspensionButton.addActionListener(this);
        popularSuspension.addActionListener(this);
        sportSuspension.addActionListener(this);
        raceSuspension.addActionListener(this);
        rallySuspension.addActionListener(this);
        
        bodyPaintButton.addActionListener(this);
        colorRed.addActionListener(this);
        colorBlue.addActionListener(this);
        colorYellow.addActionListener(this);
        colorBlack.addActionListener(this);
        
        // Add panels to CardLayout
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
        

        // Game window
        this.setLayout(new BorderLayout());

        this.add(settingsPanel, BorderLayout.NORTH);
        this.add(exhibitionPanel, BorderLayout.CENTER);
        this.add(menuPanel, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setSize(900, 700);
        this.setResizable(false);
        this.setTitle("Torque Masters");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new GameInterface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int c = 0;
        Connection conn = null;
        Conector bd = new Conector();
        JFrame fr = new JFrame();
        try {
            conn = bd.conectar();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        // Data base 
        if (e.getSource() == dataBaseButton) {
           GameInterface.mostrarCarros(conn);
        }
        // Retorna para o menu principal da aplicação
        if (e.getSource() == menuButton) {
            cardLayout.show(menuPanel, "carPanel");
        }

        // Exibe os stats do carro
        if (e.getSource() == statsButton) {
            Engine carEngine = new Engine(engineTypeCar, cylinderAmmount, cylindersCar, aspirationCar, fuelCar, engineMaterialCar, tractionCar);
            Brakes carBrakes = new Brakes(brakesCar);
            Tires carTires = new Tires(tiresCar);
            Chassis carChassis = new Chassis(chassisCar);
            Suspension carSuspension = new Suspension(suspensionCar);
            BodyPaint carBodyPaint = new BodyPaint(colorCar);

            Car carrao = new Car(carEngine, carBrakes, carTires, carChassis, carSuspension, carBodyPaint);
            carrao.setStats();
            carEngine.incluir(conn);
            carrao.incluir(conn);
            ShowPane.show(fr, carrao.toString());
            c++;

            // Criação e escrita no arquivo texto
            CreateTextFile fileWriter = new CreateTextFile();
            fileWriter.openFile(); // Abre o arquivo
            fileWriter.addRecord(carrao.toString()); // Escreve a string resultante no arquivo
            fileWriter.closeFile(); // Fecha o arquivo
        }

        if (e.getSource() == exitButton) {
            System.exit(1);
        }

        if (e.getSource() == engineButton) {
            cardLayout.show(menuPanel, "enginePanel");
        }

        // --------------------------Engine--------------------------
        if (e.getSource() == engineType) {
            cardLayout.show(menuPanel, "engineType");
        }

        // Inline Engine Type
        if (e.getSource() == inlineEngine) {
            cardLayout.show(menuPanel, "inline");
            engineTypeCar = "em linha";
        }

        if (e.getSource() == threeCylinders) {
            cylinderAmmount = 3;
            engineType.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == fourCylindersInline) {
            cylinderAmmount = 4;
            engineType.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == fiveCylinders) {
            cylinderAmmount = 5;
            engineType.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }
        
        // Boxer Engine Type
        if (e.getSource() == boxerEngine) {
            cardLayout.show(menuPanel, "boxer");
            engineTypeCar = "boxer";
        }

        if (e.getSource() == fourCylindersBoxer) {
            cylinderAmmount = 4;
            engineType.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == sixCylindersBoxer) {
            cylinderAmmount = 6;
            engineType.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        // V Engine Type
        if (e.getSource() == VEngine) {
            cardLayout.show(menuPanel, "V");
            engineTypeCar = "V";
        }

        if (e.getSource() == sixCylindersV) {
            cylinderAmmount = 6;
            engineType.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == eightCylinders) {
            cylinderAmmount = 8;
            engineType.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == tenCylinders) {
            cylinderAmmount = 10;
            engineType.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == twelveCylinders) {
            cylinderAmmount = 12;
            engineType.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        // Cylinders
        if (e.getSource() == cylinders) {
            cardLayout.show(menuPanel, "cylinders");
        }

        if (e.getSource() == firstCylinder) {
            cylindersCar = 1.0;
            cylinders.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == secondCylinder) {
            cylindersCar = 1.6;
            cylinders.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == thirdCylinder) {
            cylindersCar = 2.0;
            cylinders.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == fourthCylinder) {
            cylindersCar = 2.4;
            cylinders.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == fifthCylinder) {
            cylindersCar = 3.0;
            cylinders.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == sixthCylinder) {
            cylindersCar = 3.6;
            cylinders.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == seventhCylinder) {
            cylindersCar = 4.2;
            cylinders.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        // Aspiration
        if (e.getSource() == aspiration) {
            cardLayout.show(menuPanel, "aspiration");
        }

        if (e.getSource() == naturalAspiration) {
            aspirationCar = "aspirado naturalmente";
            aspiration.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == turboCompressor) {
            aspirationCar = "turbo compressor";
            aspiration.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }
        
        if (e.getSource() == superCompressor) {
            aspirationCar = "super compressor";
            aspiration.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        // Fuel
        if (e.getSource() == fuel) {
            cardLayout.show(menuPanel, "fuel");
        }

        if (e.getSource() == gasFuel) {
            fuelCar = "gasolina";
            fuel.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == dieselFuel) {
            fuelCar = "diesel";
            fuel.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        // Engine Material
        if (e.getSource() == engineMaterial) {
            cardLayout.show(menuPanel, "engineMaterial");
        }

        if (e.getSource() == moltedIron) {
            engineMaterialCar = "ferro fundido";
            engineMaterial.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == aluminiumAlloy) {
            engineMaterialCar = "liga de aluminio";
            engineMaterial.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == titaniumAlloy) {
            engineMaterialCar = "liga de titanio";
            engineMaterial.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        // Tração
        if (e.getSource() == traction) {
            cardLayout.show(menuPanel, "traction");
            engineButton.setEnabled(false);
        }

        if (e.getSource() == rearTraction) {
            tractionCar = "traseira";
            traction.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == frontTraction) {
            tractionCar = "dianteira";
            traction.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        if (e.getSource() == integralTraction) {
            tractionCar = "integral";
            traction.setEnabled(false);
            cardLayout.show(menuPanel, "enginePanel");
        }

        // --------------------------Brakes--------------------------
        if (e.getSource() == brakesButton) {
            cardLayout.show(menuPanel, "brakesPanel");
        }

        if (e.getSource() == popularBrakes) {
            brakesCar = "popular";
            brakesButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == sportBrakes) {
            brakesCar = "esportivo";
            brakesButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == raceBrakes) {
            brakesCar = "corrida";
            brakesButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == ceramicBrakes) {
            brakesCar = "ceramica";
            brakesButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        // --------------------------Tires--------------------------
        if (e.getSource() == tiresButton) {
            cardLayout.show(menuPanel, "tiresPanel");
        }

        if (e.getSource() == popularTires) {
            tiresCar = "popular";
            tiresButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == sportTires) {
            tiresCar = "esportivo";
            tiresButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == raceTires) {
            tiresCar = "corrida";
            tiresButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == offRoadTires) {
            tiresCar = "off-road";
            tiresButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        // --------------------------Chassis--------------------------
        if (e.getSource() == chassisButton) {
            cardLayout.show(menuPanel, "chassisPanel");
        }

        if (e.getSource() == suvChassis) {
            chassisCar = "suv";
            chassisButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == sedanChassis) {
            chassisCar = "sedan";
            chassisButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == sportChassis) {
            chassisCar = "esportivo";
            chassisButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == hatchbackChassis) {
            chassisCar = "hatchback";
            chassisButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == coupeChassis) {
            chassisCar = "coupe";
            chassisButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }
        // --------------------------Suspension--------------------------
        if (e.getSource() == suspensionButton) {
            cardLayout.show(menuPanel, "suspensionPanel");
        }
        
        if (e.getSource() == popularSuspension) {
            suspensionCar = "popular";
            suspensionButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == sportSuspension) {
            suspensionCar = "esportivo";
            suspensionButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == raceSuspension) {
            suspensionCar = "corrida";
            suspensionButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == rallySuspension) {
            suspensionCar = "rally";
            suspensionButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        // --------------------------Body Paint--------------------------
        if (e.getSource() == bodyPaintButton) {
            cardLayout.show(menuPanel, "bodyPaintPanel");
        }

        if (e.getSource() == colorBlack) {
            colorCar = "Preto";
            bodyPaintButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == colorRed) {
            colorCar = "Vermelho";
            bodyPaintButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == colorBlue) {
            colorCar = "Azul";
            bodyPaintButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == colorYellow) {
            colorCar = "Amarelo";
            bodyPaintButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }

        if (e.getSource() == colorBlack) {
            colorCar = "Black";
            bodyPaintButton.setEnabled(false);
            cardLayout.show(menuPanel, "carPanel");
        }
    }

    public static void mostrarCarros(Connection conn) {
        JFrame fr = new JFrame();
        String sql = "SELECT id_engine, brakes, tires, chassis, suspension FROM cars";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs =  pstmt.executeQuery()) {

            while (rs.next()) {
                int id_engine = rs.getInt("id_engine");
                String brakes = rs.getString("brakes");
                String tires = rs.getString("tires");
                String chassis = rs.getString("chassis");
                String suspension = rs.getString("suspension");


                String carInfo = String.format("Car{id_engine='%s', brakes='%s', tires='%s', chassis='%s', suspension='%s'}",
                        id_engine, brakes, tires, chassis, suspension);

                ShowPane.show(fr, carInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}