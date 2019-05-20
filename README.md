# WIKI WURDE ERSTELLT

# Multi-Client-Server

## Aufgabenstellung
Es soll ein Server erstellt werden, der Anfragen mehrerer Clients annehmen und beantworten kann.
Ziel ist hier eine Gegenüberstellung zweier Lösungsansätze - statisch & funktional.

## Umsetzung
Hier ist vor allem entscheidend, wie der Server die vielen Anfragen handeln kann.
Wir benötigen drei Klassen - bestehend aus den zwei Instanzen (Client & Server)
und einem ClientHandler, welcher hier als Thread erzeugt wird und die beiden miteinander agieren lässt.

### Server:
Erstellt einen lokalen Server (Server Socket), der Verbindungsanfragen von Clients akzeptiert
* Server Socket ss mit Port (int) initialisieren
1. Endlosschleife erzeugen
2. In try/catch Block Verbindungsanfragen von Clients akzeptieren und Input- & Outputstreams vom Client Socket empfangen
3. Jedem Client einen neuen Thread (ClientHandler) zuweisen und starten (Funktional: ClientHandler/run-Methode wird direkt dem Thread übergeben)
4. Zurück zu 2. und auf neue Verbindungsanfrage warten

### Client:
Erstellt einen Client (Socket), der sich auf den lokalen Server verbindet
* Scanner scn, Inetadress ip & Socket s (mit ip & Port von ss) initialisieren
1. Input- & Outputstream von Socket s empfangen
2. Endlosschleife erzeugen
3. Inputstream (Nachricht von ss) printen
4. Eingabe mit scn scannen und mit Outputstream an ss senden
5. Falls Eingabe = "Exit", s mit close() schließen und Endlosschleife verlassen
6. Ansonsten Inputstream lesen und printen
7. Zurück zu 3. und auf Eingabe warten

### ClientHandler (wird nur bei statischer Version benötigt)

* Subklasse von Thread, Objekt wird bei jeder Clientanfrage an den Server erzeugt
* run()-Methode soll Nutzer fragen, Antwort auslesen und diese zurückgeben
Da jeder Client einen eigenen In- & Outputstream erhalten soll, müssen die drei Attribute 
(Inputstream dis, Outputstream dos & Socket s) initialisiert und dem Konstruktor übergeben werden.

run()-Methode:
1. Endlosschleife erzeugen
2. im try/catch Block findet die Verarbeitung der Nachrichten statt
3. Anfrage über den Outputstream an den Client, Vorschläge zur Eingabe (Date, Exit)
4. Eingabe vom Client über Inputstream lesen
5. Antwort an den Client über Outputstream
6. Zurück zu 3. und auf Eingabe warten

