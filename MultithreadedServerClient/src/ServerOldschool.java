import java.io.*;
import java.net.*;

public class ServerOldschool {
	
	public static void main(String[] args) throws IOException {
		
		// Variablen für Server Socket
		int port = 9000;
		ServerSocket ss = new ServerSocket(port);
		
		System.out.println("Warte auf Verbindung auf Port " + port + "...");
		
		// Endlosschleife, um mehrere Clientverbindungen zuzulassen
		while (true) {
			
			// Versuche, Client Request zu akzeptieren
			try {
				
				// Client/Socket Requests empfangen
				Socket s = ss.accept();
				System.out.println("Ein neuer Client hat sich verbunden: " + s);
				
				// Input- & Outputstreams vom Client/Socket erhalten
				DataInputStream dis = new DataInputStream(s.getInputStream());
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				
				// Jedem Client/Socket einen neuen Thread zuweisen und starten
				Thread t = new ClientHandler(s, dis, dos);
				t.start();
				
				
			}catch (Exception e) {
				// Bei Fehlern wie z.B. Verbindungsabbruch wird der Server Socket beendet
				ss.close();
				e.printStackTrace();
			}
		}
		
		
	}
	
	

}
