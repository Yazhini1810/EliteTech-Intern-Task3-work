package MultithreadingChatApplication;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleChat {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type 's' to start server or 'c' for client:");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("s")) {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Server started...");
            Socket socket = server.accept();
            System.out.println("Client connected!");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println("Client: " + msg);
                    }
                } catch (IOException e) {
                }
            }).start();

            while (true) {
                String text = sc.nextLine();
                out.println(text);
            }
        } else {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server!");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println("Server: " + msg);
                    }
                } catch (IOException e) {
                }
            }).start();

            while (true) {
                String text = sc.nextLine();
                out.println(text);
            }
        }
    }
}