package org.example.CustomComponents;

import javax.swing.*;
import java.awt.*;

public class PixelatedDumpsterButton extends JButton {
    public PixelatedDumpsterButton(String label) {
        super(label);
        this.setFont(new Font("Monospaced", Font.BOLD, 16));
        this.setForeground(new Color(249, 253, 221));
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
            g.setColor(new Color(47,26,61));
        } else {
            g.setColor(new Color(47,26,61));
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

