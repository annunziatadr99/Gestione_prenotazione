package com.demo.Gestione_prenotazione.runner;

import com.demo.Gestione_prenotazione.enumerated.TipiPostazione;
import com.demo.Gestione_prenotazione.exception.*;
import com.demo.Gestione_prenotazione.model.Edificio;
import com.demo.Gestione_prenotazione.model.Postazione;
import com.demo.Gestione_prenotazione.model.Prenotazione;
import com.demo.Gestione_prenotazione.model.Utente;
import com.demo.Gestione_prenotazione.service.EdificioService;
import com.demo.Gestione_prenotazione.service.PostazioneService;
import com.demo.Gestione_prenotazione.service.PrenotazioneService;
import com.demo.Gestione_prenotazione.service.UtenteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class MenuInterattivo implements CommandLineRunner {

    private final EdificioService edificioService;
    private final PostazioneService postazioneService;
    private final UtenteService utenteService;
    private final PrenotazioneService prenotazioneService;

    public MenuInterattivo(EdificioService edificioService, PostazioneService postazioneService,
                           UtenteService utenteService, PrenotazioneService prenotazioneService) {
        this.edificioService = edificioService;
        this.postazioneService = postazioneService;
        this.utenteService = utenteService;
        this.prenotazioneService = prenotazioneService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            System.out.println("\n--- Menu Interattivo ---");
            System.out.println("1. Aggiungi un nuovo utente");
            System.out.println("2. Ricerca postazioni per tipo e città");
            System.out.println("3. Effettua una prenotazione");
            System.out.println("4. Visualizza tutte le prenotazioni");
            System.out.println("5. Esci");
            System.out.print("Seleziona un'opzione: ");

            int scelta = Integer.parseInt(scanner.nextLine());

            try {
                switch (scelta) {
                    case 1:
                        aggiungiUtente(scanner);
                        break;
                    case 2:
                        ricercaPostazioni(scanner);
                        break;
                    case 3:
                        effettuaPrenotazione(scanner);
                        break;
                    case 4:
                        visualizzaPrenotazioni();
                        break;
                    case 5:
                        continua = false;
                        System.out.println("Uscita dal programma.");
                        break;
                    default:
                        System.out.println("Opzione non valida.");
                        break;
                }
            } catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void aggiungiUtente(Scanner scanner) {
        System.out.print("Inserisci username: ");
        String username = scanner.nextLine();

        System.out.print("Inserisci nome completo: ");
        String nomeCompleto = scanner.nextLine();

        System.out.print("Inserisci email: ");
        String email = scanner.nextLine();

        if (username.isEmpty() || nomeCompleto.isEmpty() || email.isEmpty()) {
            throw new DatiNonValidiException("I dati inseriti non sono validi. Tutti i campi sono obbligatori.");
        }

        Utente utente = new Utente(null, username, nomeCompleto, email, null);
        utenteService.saveUtente(utente);

        System.out.println("Utente aggiunto con successo!");
    }

    private void ricercaPostazioni(Scanner scanner) {
        System.out.print("Inserisci tipo di postazione (PRIVATO, OPENSPACE, SALA_RIUNIONI): ");
        String tipoInput = scanner.nextLine();
        TipiPostazione tipo;
        try {
            tipo = TipiPostazione.valueOf(tipoInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DatiNonValidiException("Tipo di postazione non valido.");
        }

        System.out.print("Inserisci città: ");
        String citta = scanner.nextLine();

        List<Postazione> postazioni = postazioneService.findPostazioniByTipoAndCitta(tipo, citta);
        if (postazioni.isEmpty()) {
            System.out.println("Nessuna postazione trovata per i criteri inseriti.");
        } else {
            System.out.println("Postazioni trovate:");
            postazioni.forEach(p -> System.out.println(p.getCodice() + " - " + p.getDescrizione()));
        }
    }

    private void effettuaPrenotazione(Scanner scanner) {
        System.out.print("Inserisci username utente: ");
        String username = scanner.nextLine();
        Utente utente = null;
        try {
            utente = utenteService.findByUsername(username);
        } catch (UtenteNonTrovatoException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Inserisci codice postazione: ");
        String codicePostazione = scanner.nextLine();
        Postazione postazione = null;
        try {
            postazione = postazioneService.findByCodice(codicePostazione);
        } catch (PostazioneNonTrovataException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Inserisci data prenotazione (AAAA-MM-GG): ");
        String dataInput = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataInput);

        Prenotazione prenotazione = new Prenotazione(null, postazione, utente, data);
        try {
            prenotazioneService.savePrenotazione(prenotazione);
            System.out.println("Prenotazione effettuata con successo!");
        } catch (PrenotazioneNonPermessaException | PostazioneOccupataException e) {
            System.out.println(e.getMessage());
        }
    }

    private void visualizzaPrenotazioni() {
        List<Prenotazione> prenotazioni = prenotazioneService.getAllPrenotazioni();
        if (prenotazioni.isEmpty()) {
            System.out.println("Nessuna prenotazione presente.");
        } else {
            System.out.println("Elenco prenotazioni:");
            prenotazioni.forEach(p -> System.out.println(
                    "Utente: " + p.getUtente().getUsername() +
                            ", Postazione: " + p.getPostazione().getCodice() +
                            ", Data: " + p.getData()
            ));
        }
    }
}
