package com.demo.Gestione_prenotazione.repository;

import com.demo.Gestione_prenotazione.model.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EdificioDAORepository extends JpaRepository<Edificio,Long> {
}
