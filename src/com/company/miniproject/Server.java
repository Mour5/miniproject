package com.company.miniproject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;

public class Server {
    public static void main(String[] args) {
        int id = 1;
        try {
            ServerSocket server = new ServerSocket(1234);
            System.out.println("WAITING FOR CLIENT");
            while (true) {
                Socket socket = server.accept();
                System.out.println("CLIENT #" + id + " CONNECTED");
                id++;
                ClientHandler ch = new ClientHandler(socket);
                ch.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
