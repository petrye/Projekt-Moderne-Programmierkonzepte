package versuch3;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler3 extends Thread {

    DataInputStream dis;
    DataOutputStream dos;
    Socket cs;

    //Konstruktor
    public ClientHandler3 (Socket cs, DataInputStream dis, DataOutputStream dos){
        this.cs = cs;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        String recieved;

        while(true){
            try{
                //dos.writeUTF("Hallo, wie kann ich Ihnen helfen?\nGeben sie EXIT ein, um die Verbindung zu trennen.");
                dos.writeUTF("");
                recieved = dis.readUTF();
                if (recieved.equals("EXIT")) {
                    System.out.println("Trenne Verbindung...");
                    this.cs.close();
                    System.out.println("Vebindung getrennt!");
                    break;
                }else {
                    dos.writeUTF(recieved);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        try{
            this.dis.close();
            this.dos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
