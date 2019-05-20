import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	public static void main (String[] args) {
		
		try {
			
			// Variablen für (Client-) SocketSocket
			InetAddress ip = InetAddress.getLocalHost();
			int port = 9000;
			Socket s = new Socket(ip, port);
			
			// Variablen zum Lesen der Ein-/Ausgabe
			Scanner scn = new Scanner(System.in);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			
			// Endlosschleife, Abbruchbedingung: Eingabe von "Exit"
			while(true) {
				
				// Nachricht vom Inputstream ausgeben
				// Nachfrage vom Server
				System.out.println(dis.readUTF());
				
				// Eingabe scannen und dem Outputstream übergeben; Senden an Server
				String toSend = scn.nextLine();
				dos.writeUTF(toSend);
				
				// Überprüfung der Abbruchbedingung
				// Socket schließen und Endlosschleife beenden
				if (toSend.equals("Exit")) {
					System.out.println("Trenne Verbindung: " + s);
					s.close();
					System.out.println("Verbindung getrennt.");
					break;
				}
				
				// Nachricht vom Inputstream ausgeben
				// Antwort vom Server
				String received = dis.readUTF();
				System.out.println(received);
			}
			
			// Scanner, Input- & Outputstream beenden
			scn.close();
			dis.close();
			dos.close();
		
		}catch (IOException e) {
			// Input- und Outputstream, Scanner und .getLocalHost() können Fehler werfen
			e.printStackTrace();
		}
		
	}
	
}
