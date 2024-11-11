package org.example.Configurations;

import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.example.Screens.GameWindow;

public class Client {
    private Socket clientSocket;
    private Scanner scanner;
    private PrintWriter saida;
    private String serverAddress;
    private int serverPort;
    public String msg = " ";
    private boolean messageUpdated = false;  // Flag para controlar o envio
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static FileLogWriter file = new FileLogWriter();
    CryptoAES caes = new CryptoAES();

    public Client(String serverAddress, int serverPort) throws NoSuchAlgorithmException {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        scanner = new Scanner(System.in);
    }

    public void start(Client client) throws Exception {
        clientSocket = new Socket(serverAddress, serverPort);
        saida = new PrintWriter(clientSocket.getOutputStream(), true);

        System.out.printf("\n<CLIENT> (%s) Conectado ao servidor em %s: %d", sdf.format(new Date()), serverAddress, serverPort);
        file.writeRecord(String.format("<CLIENT> (%s) Conectado ao servidor em %s: %d", sdf.format(new Date()), serverAddress, serverPort));

        new GameWindow(client);
        messageLoop();
    }

    private void messageLoop() throws Exception {
        while (!msg.equalsIgnoreCase("sair")) {
            if (messageUpdated) {   // Envia apenas quando a mensagem é atualizada
                String cypher = caes.geraCifra(msg);
                saida.println(cypher);
                messageUpdated = false;  // Reseta a flag após o envio
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
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
        } catch (Exception ex) {
            System.out.printf("<CLIENT> (%s) Erro ao iniciar o cliente: %s", sdf.format(new Date()), ex.getMessage());
        }

        System.out.printf("\n<CLIENT> (%s) Cliente finalizado!", sdf.format(new Date()));
        file.writeRecord(String.format("<CLIENT> (%s) Cliente finalizado!", sdf.format(new Date())));
    }
}
