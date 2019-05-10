package versuch3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client33 {

    public static void main(String[] args) throws IOException {
        try {
            Scanner scn = new Scanner(System.in);

            InetAddress ip = InetAddress.getLocalHost();
            Socket cs = new Socket(ip, 9000);

            DataInputStream dis = new DataInputStream(cs.getInputStream());
            DataOutputStream dos = new DataOutputStream(cs.getOutputStream());

            while (true) {
                System.out.println(dis.readUTF());
                String tosend = scn.nextLine();
                dos.writeUTF(tosend);

                if (tosend.equals("EXIT")) {
                    System.out.println("Closing this connection: " + cs);
                    cs.close();
                    System.out.println("Connection closed.");
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
