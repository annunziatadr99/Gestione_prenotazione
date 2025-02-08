package com.demo.Gestione_prenotazione.repository;

import com.demo.Gestione_prenotazione.enumerated.TipiPostazione;
import com.demo.Gestione_prenotazione.model.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostazioneDAORepository extends JpaRepository<Postazione, Long> {
    List<Postazione> findByTipiPostazioneAndEdificio_City(TipiPostazione tipiPostazione, String city);
}

