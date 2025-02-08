package com.demo.Gestione_prenotazione.repository;

import com.demo.Gestione_prenotazione.model.Postazione;
import com.demo.Gestione_prenotazione.model.Prenotazione;
import com.demo.Gestione_prenotazione.model.Utente;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneDAORepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtenteAndData(Utente utente, LocalDate data);
    List<Prenotazione> findByPostazioneAndData(Postazione postazione, LocalDate data);
}
