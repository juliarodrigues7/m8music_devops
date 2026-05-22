package com.m8music.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "musicos")
public class Musico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(name = "genero_musical")
    private String generoMusical;

    @NotBlank
    @Email
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "musico", cascade = CascadeType.ALL)
    private List<Evento> eventos;
}
