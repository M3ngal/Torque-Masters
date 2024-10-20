package org.example.CustomComponents;

import javax.swing.*;
import java.awt.*;

// Panel customizado para inserção de imagem de background
public class CustomPanel extends JPanel {
    private Image backgroundImage;

    public CustomPanel(String imagePath) {
        setBackgroundImage(imagePath); // Usando o novo metodo ao construir o painel
    }

    // Metodo para alterar a imagem de fundo
    public void setBackgroundImage(String imagePath) {
        try {
            backgroundImage = new ImageIcon(imagePath).getImage();
            repaint(); // Reforça a repintura do painel com a nova imagem
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}