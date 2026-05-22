package com.m8music.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome_evento")
    private String nomeEvento;

    @NotBlank
    @Column(name = "local_evento")
    private String localEvento;

    @NotNull
    @Column(name = "data_evento")
    private LocalDate dataEvento;

    @ManyToOne
    @JoinColumn(name = "musico_id", nullable = false)
    private Musico musico;
}