package org.example.Configurations;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class Music {
    private Clip clip; // Mantenha uma referência ao Clip

    public Music() {
        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("musica.wav"); // Use apenas "musica.wav"
            if (url == null) {
                System.err.println("Arquivo de áudio não encontrado! Verifique o caminho.");
                return; // Encerra se o arquivo não for encontrado
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            play(); // Inicia a reprodução da música
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
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

    // Método para parar a música
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Para a reprodução do clip
        }
    }

    // Método para reiniciar a música
    public void restart() {
        if (clip != null) {
            clip.setFramePosition(0); // Reinicia a música
            play(); // Inicia novamente a reprodução
        }
    }
}
