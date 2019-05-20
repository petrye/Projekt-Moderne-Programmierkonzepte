# Projekt-Moderne-Programmierkonzepte

## Grobe Doku

### Server:
Erstellt einen lokalen Server (Server Socket), der Verbindungsanfragen von Clients akzeptiert
1. Server Socket ss mit Port (int) initialisieren
2. mit ```while(true) { ... }``` eine Endlosschleife erzeugen
3. im try/catch Block Verbindungsanfragen von Clients akzeptieren und Input- & Outputstreams vom Client Socket erhalten
4. Jedem Client einen neuen Thread (ClientHandler) zuweisen und starten (Funktional: ClientHandler/run-Methode wird direkt dem Thread übergeben)
5. zurück zu 3. und auf neue Verbindungsanfrage warten

### Client:
Erstellt einen Client (Socket), der sich auf den lokalen Server verbindet
* Scanner scn, Inetadress ip & Socket s (mit ip & Port von ss) initialisieren
* Input- & Outputstream von Socket s erhalten
1. mit ```while(true) { ... }``` eine Endlosschleife erzeugen
2. Inputstream (Nachricht von ss) printen
3. Eingabe mit scn scannen und mit Outputstream an ss senden
4. Falls Eingabe = "Exit", s mit close() schließen und Endlosschleife verlassen
5. Ansonsten Inputstream lesen und printen
6. zurück zu 2. und auf Eingabe warten

### ClientHandler:
(Nur bei nicht funktionaler Variante verwendet).

Subklasse von Thread, ein Objekt dieser Klasse wird bei jeder Clientanfrage an den Server erzeugt.
run()-Methode: Fragt den Benutzer, was er machen will, liest die Antwort und schreibt zurück.
Es müssen 3 Attribute bei der Objekterzeugung übergeben werden, damit jeder Client einen eigenen Input- & Outputstream erhält
* Inputstream dis, Outputstream dos & Socket s initialisieren
* Konstruktor (dis, dos, s)
run()-Methode:
1. mit ```while(true) { ... }``` eine Endlosschleife erzeugen
2. im try/catch Block findet die Verarbeitung der Nachrichten statt
3. Anfrage über den Outputstream an den Client, Vorschläge zur Eingabe (Date, Exit)
4. Eingabe vom Client über Inputstream lesen
5. Antwort an den Client über Outputstream
6. zurück zu 3. und auf Eingabe warten
