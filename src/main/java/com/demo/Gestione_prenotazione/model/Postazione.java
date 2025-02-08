package com.demo.Gestione_prenotazione.model;

import com.demo.Gestione_prenotazione.enumerated.TipiPostazione;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "postazioni")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codice;

    @Column(nullable = false)
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipiPostazione tipiPostazione;

    @Column(nullable = false)
    private int maxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;
}
