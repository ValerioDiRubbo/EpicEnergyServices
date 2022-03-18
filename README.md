# EpicEnergyServices

Prima impalcatura di un CRM per la  gestione di Clienti e Fatture per un ipotetica azienda nel Settore Energetico.

<b>-Tecnologie utilizzate-</b>

Il linguaggio usato per lo sviluppo è quello Java, nella sua versione 11. Ide utilizzato per la creazione: Eclipse.
Il piccolo front-end relativo è realizzato tramite Template Engine Thymeleaf.
Il database utilizzato è PostegreSQL, con la possibilità di integrare un database integrato (H2).


Ispirata all'architettura REST, vengono esposti diversi servizi WEB.
E' stato applicato il pattern MVC, così da rendere il più semplice e intuibile possibile, l'interfaccia per l'utente finale.

Le maggiori features del progetto sono:

<b>- Autenticazione, tramite JWT tokens.</b>
 E' quindi possibile registrare nuovi utenti, con dati essenziali e con ruoli che differiscono per permessi.
 
    Ruolo Admin: Permette l'accesso a tutti i servizi.
	
    Ruolo User: Permette di accedere alle funzionalità di sola lettura, isolando cambiamenti sostanziali per i soli utenti.
	
<b>- L'app è raccolta in package seguendo il pattern architetturale MVC.</b>

	-I controller si dividono in controller.WEB (che richiama le funzionalità di Thymeleaf) e il controller per le chiamate REST.
	-I Package che terminano per security sono specializzati nella sicurezza dell'applicazione, autenticazione e tutto il resto.
	
<b>- Caricamento database tramite CSV, utiizzando la libreria OpenCsv.</b>
	
	-Comuni e province italiane vengono caricate in DB tramite file CSV, per avere consistenza di dati reali.
	-Tramite un'apposita chiamata Rest o un link nell'Index html del piccolo front-end, carichiamo il DB
	con tre clienti e comuni e province Italiane.
	
<b>- Interfacce grafiche per chiamate REST.</b>
	Tramite Postman e Swagger, possiamo attivare le chiamate ai vari endpoint dell'applicazione. Swagger contiene anche la documentazione relativa,
	una breve spiegazione dei vari metodi.
	
	
						Endpoint per esplorare l'applicazione:

Link Swagger:
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

Index Front-end Thymeleaf:
http://localhost:8080/clienti/index

					 TODO list:

- Coverage dei test junit più ampio.
- Implementazione di controlli sulla logica di Business e l'inserimento di dati.
- Bootstrap e integrazioni grafiche frontend.
- Modifiche e migliorie sul Modello Dati.
