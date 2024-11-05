package org.example.Configurations;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private Clip clip; // Referência ao Clip
    private final String audioFilePath = "resources//Horizon.wav";

    public Music() {
        try {
            File audioFile = new File(audioFilePath);
            if (!audioFile.exists()) {
                System.err.println("Arquivo de áudio não encontrado! Verifique o caminho.");
                return; // Encerra se o arquivo não for encontrado
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
