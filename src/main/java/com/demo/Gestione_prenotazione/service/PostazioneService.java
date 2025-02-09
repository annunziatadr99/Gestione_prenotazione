package com.demo.Gestione_prenotazione.service;

import com.demo.Gestione_prenotazione.enumerated.TipiPostazione;
import com.demo.Gestione_prenotazione.model.Postazione;
import com.demo.Gestione_prenotazione.repository.PostazioneDAORepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {

    private final PostazioneDAORepository postazioneDAORepository;

    public PostazioneService(PostazioneDAORepository postazioneDAORepository) {
        this.postazioneDAORepository = postazioneDAORepository;
    }

    public Postazione savePostazione(Postazione postazione) {
        return postazioneDAORepository.save(postazione);
    }

    public List<Postazione> getAllPostazioni() {
        return postazioneDAORepository.findAll();
    }

    public List<Postazione> findPostazioniByTipoAndCitta(TipiPostazione tipiPostazione, String city) {
        return postazioneDAORepository.findByTipiPostazioneAndEdificio_City(tipiPostazione, city);
    }
    public Postazione findByCodice(String codice) {
        return postazioneDAORepository.findByCodice(codice);
    }
}
