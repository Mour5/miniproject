package com.company.miniproject;

import org.w3c.dom.ls.LSOutput;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static MainFrame frame;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PackageData pd = new PackageData();
        frame = new MainFrame();
        frame.setVisible(true);

    }

    public static class ServerConnection{
        private static Handler handler = new Handler();

        public static Handler getHandler() {
            return handler;
        }
    }
}
