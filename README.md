# Gestione Prenotazione Cinema Multisala

## Implementation
Per l'importazione del progetto in Eclipse è necessario:

Importare il progetto (Import Projects from folder or Archive);
Aggiungere la libreria JUNIT4 (aprire le Proprieties -> Java Build Path -> Libraries -> Add Library... -> JUnit)

## Testing
Le cartelle contenenti i file di test sono 'test_strutturali' e 'test_funzionali', presenti nella directory 'src'.
Per eseguire i test funzionali eseguire TestSuite.java, per i test strutturali AllTest.java
I test strutturali raggiungono la coverage del 99.4% del package 'cinema' invece del 100% in quanto nella classe Permesso.java non viene considerata la prima riga del codice 'package cinema;' (<- necessaria), portando al 79,4% la coverage di quel file.
