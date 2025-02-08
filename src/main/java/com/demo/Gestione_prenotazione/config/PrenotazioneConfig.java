package com.demo.Gestione_prenotazione.config;

import com.demo.Gestione_prenotazione.repository.EdificioDAORepository;
import com.demo.Gestione_prenotazione.repository.PostazioneDAORepository;
import com.demo.Gestione_prenotazione.repository.PrenotazioneDAORepository;
import com.demo.Gestione_prenotazione.repository.UtenteDAORepository;
import com.demo.Gestione_prenotazione.service.EdificioService;
import com.demo.Gestione_prenotazione.service.PostazioneService;
import com.demo.Gestione_prenotazione.service.PrenotazioneService;
import com.demo.Gestione_prenotazione.service.UtenteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrenotazioneConfig {

    @Bean
    public EdificioService edificioService(EdificioDAORepository edificioDAORepository) {
        return new EdificioService(edificioDAORepository);
    }

    @Bean
    public PostazioneService postazioneService(PostazioneDAORepository postazioneDAORepository) {
        return new PostazioneService(postazioneDAORepository);
    }

    @Bean
    public UtenteService utenteService(UtenteDAORepository utenteDAORepository) {
        return new UtenteService(utenteDAORepository);
    }

    @Bean
    public PrenotazioneService prenotazioneService(PrenotazioneDAORepository prenotazioneDAORepository) {
        return new PrenotazioneService(prenotazioneDAORepository);
    }
}
