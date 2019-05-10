package versuch3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client (Socket cs){
        this.cs = cs;
    }

        Scanner scn = new Scanner(System.in);
        Socket cs;

        public void start() throws IOException {
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
        }

}
