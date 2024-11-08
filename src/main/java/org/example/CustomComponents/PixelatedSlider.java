package org.example.CustomComponents;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import org.example.Configurations.Music;

public class PixelatedSlider extends JSlider {

    private final Music music;

    public PixelatedSlider(Music music) {
        super(0, 100, 50); // Min: 0, Max: 100, Inicial: 50%
        this.music = music;
        setUI(new PixelatedSliderUI(this));
        setForeground(Color.BLACK);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Adiciona listener para ajustar o volume ao mover o slider
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = getValue();
                music.setVolume(value / 100f); // Converte para valor entre 0.0 e 1.0
            }
        });
    }

    private static class PixelatedSliderUI extends BasicSliderUI {

        public PixelatedSliderUI(JSlider slider) {
            super(slider);
        }

        @Override
        public void paintThumb(Graphics g) {
            g.setColor(new Color(91, 85, 136));
            g.fillRoundRect(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, 10, 10);
            g.setColor(Color.BLACK);
            g.drawRoundRect(thumbRect.x, thumbRect.y, thumbRect.width - 1, thumbRect.height - 1, 10, 10);
        }

        @Override
        public void paintTrack(Graphics g) {
            g.setColor(new Color(200, 200, 200));
            g.fillRoundRect(trackRect.x, trackRect.y + trackRect.height / 4, trackRect.width, trackRect.height / 2, 10, 10);
        }

        @Override
        public void paintTicks(Graphics g) {
            g.setColor(Color.BLACK);
            super.paintTicks(g);
        }

        @Override
        public void paintFocus(Graphics g) {
            // Não desenha o foco para manter o estilo pixelado
        }
    }
}
