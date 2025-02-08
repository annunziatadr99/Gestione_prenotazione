package com.demo.Gestione_prenotazione.service;

import com.demo.Gestione_prenotazione.model.Edificio;
import com.demo.Gestione_prenotazione.repository.EdificioDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdificioService {
    private final EdificioDAORepository edificioDAORepository;

@Autowired
    public EdificioService(EdificioDAORepository edificioDAORepository) {
        this.edificioDAORepository = edificioDAORepository;
    }

    public Edificio saveEdificio(Edificio edificio){
        return edificioDAORepository.save(edificio);
    }

    public List<Edificio> getAllEdifici(){
        return edificioDAORepository.findAll();
    }
}
