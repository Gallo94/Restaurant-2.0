# Ristorante 2.0

Il progetto di ingegneria del software è stato sviluppato secondo il processo __UP__ (_Unified Process_) ovvero attraverso uno sviluppo iterativo ed incrementale.
UP è spesso associato all' __UML__, linguaggio di modellazione e specifica basato sul paradigma orientato agli oggetti,  così ho utilizzato il software [Visual Paradigm](https://www.visual-paradigm.com/) per fornire tutta la documentazione di progettazione.

Il progetto consiste nella modellazione e sviluppo di un sistema software per la gestione di un __Ristorante 2.0__.
In particolare, gestisco la sala e le possibili interazioni con la cucina. Dunque il cameriere crea la comanda, aggiunge e/o rimuove piatti, per poi confermarla. Invia la comanda alla cucina, viene processata al di fuori del sistema e quando tutti i piatti sono pronti, la comanda è completata. Inoltre è contemplata la gestione multipla degli ordini.

## Glossario
- __Cuoco__,     persona che cucina i piatti per il cliente.
- __Cassiere__,  persona che riscuote il denaro del cliente.
- __Cameriere__, persona che crea la comanda per il tavolo del cliente.
- __Caposala__,  persona che si occupa di accogliere il cliente.
- __Cliente__,   persona che viene servita dal cameriere.
- __Tavolo__,    tavolo su cui il cliente mangia i piatti ordinati.
- __Piatto__,    vivanda che consuma il cliente al tavolo.
- __Comanda__,   annotazione di piatti effettuata dal camiere.

## Attori
- __Caposala__.
- __Cameriere__.
- __Cassiere__.
- __Cuoco__.

## Documentazione
All'interno di [Doc](https://github.com/Gallo94/Software-Engineering-Project/tree/master/Doc) è disponibile il progetto con tutta la documentazione richiesta.
Per eseguire il progetto da terminale bisogna inserire questa stringa:
__mvn exec:java -Dexec.mainClass="it.unicam.cs.ids.lg.Main"__