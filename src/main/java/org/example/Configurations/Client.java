package org.example.Configurations;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import org.example.Screens.GameWindow;

public class Client {
    private Socket clientSocket;
    private Scanner scanner;
    private PrintWriter saida;
    private String serverAddress;
    private int serverPort;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        clientSocket = new Socket(serverAddress, serverPort);
        saida = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("Conectado ao servidor em " + serverAddress + ":" + serverPort);
        new GameWindow();
        messageLoop();
    }

    private void messageLoop() throws IOException {
        String msg;
        System.out.println("Aguardando a digitação de uma mensagem!");
        do {
            System.out.print("Digite uma mensagem (ou <sair> para finalizar): ");
            msg = scanner.nextLine();
            saida.println(msg);
        } while (!msg.equalsIgnoreCase("sair"));
    }

    public static void main(String args[]) {
        try {
            // Utilize o endereço do servidor e a porta correta.
            Client client = new Client("127.0.0.1", 4000); // Use o IP do servidor se em outra máquina
            client.start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o cliente: " + ex.getMessage());
        }
        System.out.println("Cliente finalizado!");
    }
}
