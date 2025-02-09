package com.demo.Gestione_prenotazione.runner;

import com.demo.Gestione_prenotazione.enumerated.TipiPostazione;
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

@Component
public class ArchivioDati implements CommandLineRunner {

    private final EdificioService edificioService;
    private final PostazioneService postazioneService;
    private final UtenteService utenteService;
    private final PrenotazioneService prenotazioneService;

    public ArchivioDati(EdificioService edificioService, PostazioneService postazioneService, UtenteService utenteService, PrenotazioneService prenotazioneService) {
        this.edificioService = edificioService;
        this.postazioneService = postazioneService;
        this.utenteService = utenteService;
        this.prenotazioneService = prenotazioneService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Inizializzazione dati...");

        // Creazione Edifici
        Edificio edificio1 = new Edificio(null, "Empire State Building", "350 5th Ave", "New York", null);
        Edificio edificio2 = new Edificio(null, "Colosseo", "Piazza del Colosseo 1", "Roma", null);
        Edificio edificio3 = new Edificio(null, "Torre Eiffel", "Champ de Mars 5", "Parigi", null);

        edificioService.saveEdificio(edificio1);
        edificioService.saveEdificio(edificio2);
        edificioService.saveEdificio(edificio3);

        // Creazione Postazioni
        Postazione postazione1 = new Postazione(null, "PST001", "Ufficio Privato", TipiPostazione.PRIVATO, 1, edificio1, null);
        Postazione postazione2 = new Postazione(null, "PST002", "Open Space", TipiPostazione.OPENSPACE, 8, edificio2, null);
        Postazione postazione3 = new Postazione(null, "PST003", "Sala Riunioni", TipiPostazione.SALA_RIUNIONI, 15, edificio3, null);

        postazioneService.savePostazione(postazione1);
        postazioneService.savePostazione(postazione2);
        postazioneService.savePostazione(postazione3);

        // Creazione Utenti
        Utente utente1 = new Utente(null, "dario99", "Dario Annunziata", "anudar99@hotmail.it", null);
        Utente utente2 = new Utente(null, "umberto33", "Umberto Emanuele", "umberto.emanuele@gmail.com", null);
        Utente utente3 = new Utente(null, "mario.rossi", "Mario Rossi", "mario.rossi@hotmail.com", null);

        utenteService.saveUtente(utente1);
        utenteService.saveUtente(utente2);
        utenteService.saveUtente(utente3);

        // Creazione Prenotazioni
        Prenotazione prenotazione1 = new Prenotazione(null, postazione3, utente1, LocalDate.of(2025, 2, 15));
        Prenotazione prenotazione2 = new Prenotazione(null, postazione1, utente3, LocalDate.of(2025, 2, 18));
        Prenotazione prenotazione3 = new Prenotazione(null, postazione2, utente2, LocalDate.of(2025, 2, 13));

        try {
            prenotazioneService.savePrenotazione(prenotazione1);
            prenotazioneService.savePrenotazione(prenotazione2);
            prenotazioneService.savePrenotazione(prenotazione3);
        } catch (Exception e) {
            System.err.println("Errore durante la prenotazione: " + e.getMessage());
        }

        System.out.println("Dati inizializzati con successo!");
    }
}
