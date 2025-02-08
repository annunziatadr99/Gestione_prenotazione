package com.demo.Gestione_prenotazione.service;

import com.demo.Gestione_prenotazione.model.Postazione;
import com.demo.Gestione_prenotazione.model.Prenotazione;
import com.demo.Gestione_prenotazione.model.Utente;
import com.demo.Gestione_prenotazione.repository.PrenotazioneDAORepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    private final PrenotazioneDAORepository prenotazioneDAORepository;

    public PrenotazioneService(PrenotazioneDAORepository prenotazioneDAORepository) {
        this.prenotazioneDAORepository = prenotazioneDAORepository;
    }

    public Prenotazione savePrenotazione(Prenotazione prenotazione) throws Exception {

        List<Prenotazione> prenotazioniUtente = prenotazioneDAORepository.findByUtenteAndData(prenotazione.getUtente(), prenotazione.getData());
        if (!prenotazioniUtente.isEmpty()) {
            throw new Exception("L'utente ha già una prenotazione per questa data.");
        }


        List<Prenotazione> prenotazioniPostazione = prenotazioneDAORepository.findByPostazioneAndData(prenotazione.getPostazione(), prenotazione.getData());
        if (!prenotazioniPostazione.isEmpty()) {
            throw new Exception("La postazione è già prenotata per questa data.");
        }

        return prenotazioneDAORepository.save(prenotazione);
    }

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneDAORepository.findAll();
    }

    public List<Prenotazione> findPrenotazioniByUtenteAndData(Utente utente, LocalDate data) {
        return prenotazioneDAORepository.findByUtenteAndData(utente, data);
    }

    public List<Prenotazione> findPrenotazioniByPostazioneAndData(Postazione postazione, LocalDate data) {
        return prenotazioneDAORepository.findByPostazioneAndData(postazione, data);
    }
}
