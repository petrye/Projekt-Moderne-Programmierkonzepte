package Versuch5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args){
        try {
            Scanner scn = new Scanner(System.in);

            InetAddress ip = InetAddress.getLocalHost();
            Socket cs = new Socket(ip, 9000);

            DataInputStream dis = new DataInputStream(cs.getInputStream());
            DataOutputStream dos = new DataOutputStream(cs.getOutputStream());

            while (true) {

                System.out.println(dis.readUTF());
                String toSend = scn.nextLine();
                dos.writeUTF(toSend);

                if (toSend.equals("EXIT")) {
                    System.out.println("Trenne Verbindung: " + cs);
                    cs.close();
                    System.out.println("Verbindung getrennt.");
                    break;
                }

                String recieved = dis.readUTF();
                System.out.println(recieved);
            }
            scn.close();
            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
