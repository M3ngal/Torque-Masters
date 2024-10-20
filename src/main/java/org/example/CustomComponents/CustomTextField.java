package org.example.CustomComponents;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    public CustomTextField(int columns) {
        super(columns);
        this.setFont(new Font("Monospaced", Font.BOLD, 16));
        this.setForeground(Color.BLACK);
        this.setBackground(new Color(91, 85, 136));
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(91, 85, 136));
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.BLACK); // Cor da borda
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }

    @Override
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(false);
    }
}
