package Versuch5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        final int port = 9000;
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

                // neuer Thread erstellen
                Thread t = new Thread(() ->
                {

                    while (true) {

                        try {
                            dos.writeUTF("");
                            String recieved = dis.readUTF();
                            if (recieved.equals("EXIT")) {
                                System.out.println("Trenne Verbindung mit: "+ cs);
                                cs.close();
                                System.out.println("Vebindung getrennt!");
                                break;
                            } else {
                                dos.writeUTF(recieved);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        dis.close();
                        dos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                t.start();

            } catch (Exception e) {
                ss.close();
                e.printStackTrace();
            }
        }
    }

}
