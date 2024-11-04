package org.example.Configurations;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private Clip clip; // Referência ao Clip
    private final String audioFilePath = "src/main/java/resources/musica.wav"; // Caminho armazenado na classe

    public Music() {
        try {
            // Carrega o arquivo de áudio diretamente usando o caminho armazenado
            File audioFile = new File(audioFilePath);
            if (!audioFile.exists()) {
                System.err.println("Arquivo de áudio não encontrado! Verifique o caminho.");
                return; // Encerra se o arquivo não for encontrado
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            play(); // Inicia a reprodução da música
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Método para iniciar a música
    public void play() {
        if (clip != null) {
            clip.start(); // Inicia a reprodução do clip
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Reproduz em loop contínuo, se desejado
        }
    }
}
