package org.example.Configurations;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    //Music music = new Music();
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static FileLogWriter file = new FileLogWriter();

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        scanner = new Scanner(System.in);
    }

    public void start(Client client) throws IOException {
        clientSocket = new Socket(serverAddress, serverPort);
        saida = new PrintWriter(clientSocket.getOutputStream(), true);

        System.out.printf("\n<CLIENT> (%s) Conectado ao servidor em %s: %d", sdf.format(new Date()), serverAddress, serverPort);
        file.writeRecord(String.format("<CLIENT> (%s) Conectado ao servidor em %s: %d", sdf.format(new Date()), serverAddress, serverPort));

        new GameWindow(client);
        //music.play();
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
            System.out.printf("<CLIENT> (%s) Erro ao iniciar o cliente: %s", sdf.format(new Date()), ex.getMessage());
        }

        System.out.printf("\n<CLIENT> (%s) Cliente finalizado!", sdf.format(new Date()));
        file.writeRecord(String.format("<CLIENT> (%s) Cliente finalizado!", sdf.format(new Date())));
    }
}
