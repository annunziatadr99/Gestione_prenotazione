package com.demo.Gestione_prenotazione.repository;

import com.demo.Gestione_prenotazione.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteDAORepository extends JpaRepository<Utente, Long> {
    Utente findByUsername(String username);
}
