package com.demo.Gestione_prenotazione.service;

import com.demo.Gestione_prenotazione.enumerated.TipiPostazione;
import com.demo.Gestione_prenotazione.model.Postazione;
import com.demo.Gestione_prenotazione.repository.PostazioneDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {
    private final PostazioneDAORepository postazioneDAORepository;

@Autowired
    public PostazioneService(PostazioneDAORepository postazioneDAORepository) {
        this.postazioneDAORepository = postazioneDAORepository;
    }

    public Postazione savePostazione(Postazione postazione){
    return postazioneDAORepository.save(postazione);
    }

    public List<Postazione> getAllPostazioni(){
    return postazioneDAORepository.findAll();
    }

    public List<Postazione> findPostazioneByTipoAndCitta(TipiPostazione tipiPostazione, String citta){
    return postazioneDAORepository.findByTipoeCittaEdificio(tipiPostazione,citta);
    }
}
