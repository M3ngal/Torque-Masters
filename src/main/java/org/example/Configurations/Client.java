package org.example.Configurations;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import org.example.Screens.GameWindow;
import org.example.Configurations.Music;

public class Client {
    private Socket clientSocket;
    private Scanner scanner;
    private PrintWriter saida;
    private String serverAddress;
    private int serverPort;
    public String msg = " ";
    private boolean messageUpdated = false;  // Flag para controlar o envio
    Music music = new Music();

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        scanner = new Scanner(System.in);
    }

    public void start(Client client) throws IOException {
        clientSocket = new Socket(serverAddress, serverPort);
        saida = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("Conectado ao servidor em " + serverAddress + ":" + serverPort);
        new GameWindow(client);
        music.play();
        messageLoop();
    }

    private void messageLoop() throws IOException {
        while (!msg.equalsIgnoreCase("sair")) {
            if (messageUpdated) {   // Envia apenas quando a mensagem é atualizada
                saida.println(msg);
                messageUpdated = false;  // Reseta a flag após o envio
            }
        }
    }

    public void updateMessage(String newMessage) {
        this.msg = newMessage;
        this.messageUpdated = true;  // Marca a flag como true para enviar a mensagem
    }

    public static void main(String args[]) {
        try {
            Client client = new Client("127.0.0.1", 4000);
            client.start(client);
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o cliente: " + ex.getMessage());
        }
        System.out.println("Cliente finalizado!");
    }
}
