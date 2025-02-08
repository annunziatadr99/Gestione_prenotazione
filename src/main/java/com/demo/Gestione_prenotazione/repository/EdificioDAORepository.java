package com.demo.Gestione_prenotazione.repository;

import com.demo.Gestione_prenotazione.model.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioDAORepository extends JpaRepository<Edificio, Long> {
}
