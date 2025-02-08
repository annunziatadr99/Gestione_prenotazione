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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ArchivioDati implements CommandLineRunner {

    private  EdificioService edificioService;
    private PostazioneService postazioneService;
    private UtenteService utenteService;
    private PrenotazioneService prenotazioneService;


    @Autowired
    public ArchivioDati(EdificioService edificioService, PostazioneService postazioneService, UtenteService utenteService, PrenotazioneService prenotazioneService) {
        this.edificioService = edificioService;
        this.postazioneService = postazioneService;
        this.utenteService = utenteService;
        this.prenotazioneService = prenotazioneService;
    }

    @Override
    public void run(String... args) throws Exception {
        Edificio edificio1 = new Edificio(null,"Empire State Bulding","350 5th Ave", "Paris");
        Edificio edificio2 = new Edificio(null,"Colosseo","Piazza del Colosseo 1", "Roma");
        Edificio edificio3 = new Edificio(null,"Torre Eiffel","Champ de Mars 5 ", "New York");

        edificioService.saveEdificio(edificio1);
        edificioService.saveEdificio(edificio2);
        edificioService.saveEdificio(edificio3);


        Postazione postazione1 = new Postazione(null,"PST001","Private Office", TipiPostazione.PRIVATE,1,edificio1);
        Postazione postazione2 = new Postazione(null,"PST002","Open Space", TipiPostazione.OPENSPACE,8,edificio2);
        Postazione postazione3 = new Postazione(null,"PST003","Meeting Room", TipiPostazione.MEETING_ROOM,15,edificio3);

        postazioneService.savePostazione(postazione1);
        postazioneService.savePostazione(postazione2);
        postazioneService.savePostazione(postazione3);


        Utente utente1 = new Utente(null,"Dario","Annunzita","anudar99@hotmail.it");
        Utente utente2 = new Utente(null,"Umberto","Emanuele","umberto.emanuele@gmail.com");
        Utente utente3 = new Utente(null,"Mario","Rossi","mario.rossi@hotmail.com");

        utenteService.saveUtente(utente1);
        utenteService.saveUtente(utente2);
        utenteService.saveUtente(utente3);

        Prenotazione prenotazione1 = new Prenotazione(null,postazione3,utente1, LocalDate.of(2025,2,15));
        Prenotazione prenotazione2 = new Prenotazione(null,postazione1,utente3, LocalDate.of(2025,2,18));
        Prenotazione prenotazione3 = new Prenotazione(null,postazione2,utente2, LocalDate.of(2025,2,13));

        prenotazioneService.savePrenotazione(prenotazione1);
        prenotazioneService.savePrenotazione(prenotazione2);
        prenotazioneService.savePrenotazione(prenotazione3);
    }


}
