package versuch3;

import java.io.*;
import java.net.*;

public class Server3 {

    public static void main(String[] args) throws IOException {

        int port = 9000;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Warte auf Verbindung auf Port " + port + "...");

        while (true) {
            try {
                // Client Requests empfangen
                Socket cs = ss.accept();
                System.out.println("Ein neuer Client hat sich verbunden: " + cs);

                // Input- & Outputstreams
                DataInputStream dis = new DataInputStream(cs.getInputStream());
                DataOutputStream dos = new DataOutputStream(cs.getOutputStream());

                /*
                new Thread (() -> {
                    new ClientHandler3(cs,dis,dos);
                }).start();
                */

                // neuer Thread erstellen
                Thread t = new ClientHandler3(cs, dis, dos);
                // Thread starten
                t.start();

            }catch (Exception e){
                ss.close();
                e.printStackTrace();
            }
        }
    }

}
