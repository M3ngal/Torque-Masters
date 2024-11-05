package org.example.Configurations;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static final int PORT = 4000; //ou 3334
    private ServerSocket serverSocket;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static FileLogWriter file = new FileLogWriter();

    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.printf("\n----------------------------------------------------------------------------------------------------------------\n" +
                "<SERVER> (%s) Servidor iniciado na porta: %d", sdf.format(new Date()), PORT);
        file.writeRecord(String.format("\n----------------------------------------------------------------------------------------------------------------\n" +
                "<SERVER> (%s) Servidor iniciado na porta: %d", sdf.format(new Date()), PORT));

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
                System.out.printf("\n<SERVER> (%s) Cliente %s: %s", sdf.format(new Date()), clientSocket.getRemoteSocketAddress(), msg);
                file.writeRecord(String.format("<SERVER> (%s) Cliente %s: %s", sdf.format(new Date()), clientSocket.getRemoteSocketAddress(), msg));
            }
        } finally {
            clientSocket.close();
        }
    }

    public static void main(String args[]) {
        try {
            Server server = new Server();
            server.start();
        } catch (IOException ex) {
            System.out.printf("\n<SERVER> (%s) Erro ao iniciar o servidor: %s", sdf.format(new Date()),ex.getMessage());
            file.writeRecord(String.format("\n<SERVER> (%s) Erro ao iniciar o servidor: %s", sdf.format(new Date()),ex.getMessage()));
        }

        System.out.printf("\n<SERVER> (%s) Servidor finalizado!", sdf.format(new Date()));
        file.writeRecord(String.format("<SERVER> (%s) Servidor finalizado!", sdf.format(new Date())));
    }
}