package org.example.CustomComponents;

import javax.swing.*;
import java.awt.*;

public class PixelatedButton extends JButton {
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
