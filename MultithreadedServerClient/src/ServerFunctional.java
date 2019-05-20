import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

public class ServerFunctional {
	
	public static void main(String[] args) throws IOException {
			
		// Variablen für Server Socket
		int port = 9000;
		ServerSocket ss = new ServerSocket(port);
		
		// eigenes Format für Datum
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy - hh:mm:ss");
		
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
				// Funktional: Übergabe der run()-Methode vom ClientHandler
				new Thread(() -> {
					
					// Variablen zum Speichern der Nachrichten
					String received;
					String toReturn;
					
					// Endlosschleife, Abbruchbedingung: Empfangen von "Exit"
					while(true) {
												
						try {
							
							// Nachricht dem Outputstream übergeben
							// Nachfrage beim Client
							dos.writeUTF("Hallo, wie kann ich Ihnen helfen? \"Date\" gibt das aktuelle Datum zurück, mit \"Exit\" wird die Verbindung getrennt.");
							
							// Inputstream lesen und in Variable speichern
							// Antwort vom Client lesen
							received = dis.readUTF();
							
							// Überprüfung der Abbruchbedingung
							// Socket schließen und Endlosschleife beenden
							if (received.equals("Exit")) {
								System.out.println("Trenne Verbindung zu " + s);
								s.close();
								System.out.println("Verbindung getrennt.");
								break;
							}
							
							Date date = new Date();
							
							// Empfangene Nachricht prüfen und entsprechend antworten
							// Antwort dem Outputstream übergeben
							switch (received) {
							
								case "Date" :
									toReturn = dateFormatter.format(date);
									dos.writeUTF(toReturn);
									break;
								
								case "Hallo" :
									toReturn = "Welt!";
									dos.writeUTF(toReturn);
									break;
							
								default:
									// Echo :P
									dos.writeUTF(received);
									break;
							}
							
						}catch (IOException e) {
							// Bei geworfenen Fehlern verliert der Socket die Verbindung, Endlosschleife wird verlassen
							e.printStackTrace();
							System.out.println(s + " hat die Verbindung verloren.");
							break;
						}
					}
					
					try {
						// Input- und Outputstream beenden
						dis.close();
						dos.close();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}).start();
				
			}catch (Exception e) {
				// Bei Fehlern wie z.B. Verbindungsabbruch wird der Server Socket beendet
				ss.close();
				e.printStackTrace();
			}
		}
		
		
	}
	
	

}
