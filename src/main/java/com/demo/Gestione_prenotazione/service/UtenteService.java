package com.demo.Gestione_prenotazione.service;

import com.demo.Gestione_prenotazione.exception.UtenteNonTrovatoException;
import com.demo.Gestione_prenotazione.model.Utente;
import com.demo.Gestione_prenotazione.repository.UtenteDAORepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtenteService {

    private final UtenteDAORepository utenteDAORepository;

    public UtenteService(UtenteDAORepository utenteDAORepository) {
        this.utenteDAORepository = utenteDAORepository;
    }

    public Utente saveUtente(Utente utente) {
        return utenteDAORepository.save(utente);
    }

    public List<Utente> getAllUtenti() {
        return utenteDAORepository.findAll();
    }

    public Utente findByUsername(String username) {
        Utente utente = utenteDAORepository.findByUsername(username);
        if (utente == null) {
            throw new UtenteNonTrovatoException("Utente non trovato: " + username);
        }
        return utente;
    }
}
