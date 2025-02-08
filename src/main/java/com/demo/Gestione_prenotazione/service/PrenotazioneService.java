package com.demo.Gestione_prenotazione.service;

import com.demo.Gestione_prenotazione.model.Postazione;
import com.demo.Gestione_prenotazione.model.Prenotazione;
import com.demo.Gestione_prenotazione.model.Utente;
import com.demo.Gestione_prenotazione.repository.PrenotazioneDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    private final PrenotazioneDAORepository prenotazioneDAORepository;


@Autowired
    public PrenotazioneService(PrenotazioneDAORepository prenotazioneDAORepository) {
        this.prenotazioneDAORepository = prenotazioneDAORepository;
    }

    public Prenotazione savePrenotazione(Prenotazione prenotazione){
    return prenotazioneDAORepository.save(prenotazione);
    }

    public List<Prenotazione> getAllPrenotazioni(){
    return prenotazioneDAORepository.findAll();
    }

    public List<Prenotazione> findPrenotazioniByUtenteAndData(Utente utente, LocalDate data){
    return prenotazioneDAORepository.findByUtenteAndData(utente, data);
    }

    public List<Prenotazione> findPrenotazioniByPostazioneAndData(Postazione postazione, LocalDate data){
    return prenotazioneDAORepository.findByPostazioneAndData(postazione, data);
    }
}
