package versuch3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        int port = 9000;
        InetAddress ip = InetAddress.getLocalHost();

        int i = 5;

        Client client1 = new Client(new Socket(ip, port));
        Client client2 = new Client(new Socket(ip, port));

        try{
                client1.start();
                client2.start();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
