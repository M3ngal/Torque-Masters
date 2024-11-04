package org.example.Configurations;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientSocket {
    private final Socket socket;
    private final BufferedReader entrada;
    private final PrintWriter saida;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public ClientSocket(final Socket socket) throws IOException {
        this.socket = socket;
        System.out.printf("\n<CLIENT> (%s) Cliente %s se conectou", sdf.format(new Date()), socket.getRemoteSocketAddress());
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        saida = new PrintWriter(socket.getOutputStream(), true);
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    public void close() {
        try {
            entrada.close();
            saida.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Erro o fechar o socket: " + ex.getMessage());
        }
    }

    public String getMessage() {
        try {
            return entrada.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    public boolean sendMsg(String msg) {
        saida.println(msg);
        return saida.checkError();
    }
}