package org.example.Configurations;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static final String ADDRESS = "0.0.0.0";
    public static final int PORT = 4000; //ou 3334
    private ServerSocket serverSocket;

    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta: " + PORT);
        clientConnectionLoop();
    }

    private void clientConnectionLoop() throws IOException {
        do {
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
            new Thread(() -> clientMessageLoop(clientSocket)).start();
        } while (true);
    }

    public void clientMessageLoop(ClientSocket clientSocket) {
        String msg;
        try {
            while ((msg = clientSocket.getMessage()) != null && !msg.equalsIgnoreCase("sair")) {
                System.out.printf("Ação do cliente %s: %s\n", clientSocket.getRemoteSocketAddress(), msg);
            }
        } finally {
            clientSocket.close();
        }
    }

    public static void main(String args[]) {
        System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
        try {
            Server server = new Server();
            server.start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
        }
        System.out.println("Servidor finalizado!");
    }
}