package com.demo.Gestione_prenotazione.repository;

import com.demo.Gestione_prenotazione.enumerated.TipiPostazione;
import com.demo.Gestione_prenotazione.model.Edificio;
import com.demo.Gestione_prenotazione.model.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneDAORepository extends JpaRepository<Postazione, Long> {
    List<Postazione> findByTipoeCittaEdificio(TipiPostazione tipiPostazione,String citta);
}
