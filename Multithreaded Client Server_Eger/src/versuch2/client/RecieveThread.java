package versuch2.client;

import java.io.*;
import java.net.*;

class RecieveThread implements Runnable
{
    Socket sock=null;
    BufferedReader recieve=null;

    public RecieveThread(Socket sock) {

        this.sock = sock;

    }//end constructor

    public void run() {

        try{

            recieve = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));//get inputstream
            String msgRecieved = null;
            while((msgRecieved = recieve.readLine())!= null)
            {
                System.out.println("From Server: " + msgRecieved);
                System.out.println("Please enter something to send to server..");
            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }//end run

}//end class recievethread