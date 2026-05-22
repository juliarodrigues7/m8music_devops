package com.m8music.service;

import com.m8music.model.Evento;
import com.m8music.model.Musico;
import com.m8music.repository.EventoRepository;
import com.m8music.repository.MusicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final MusicoRepository musicoRepository;

    public EventoService(EventoRepository eventoRepository, MusicoRepository musicoRepository) {
        this.eventoRepository = eventoRepository;
        this.musicoRepository = musicoRepository;
    }

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public Optional<Evento> cadastrar(String nomeEvento, String localEvento, LocalDate dataEvento, Long musicoId) {
        Optional<Musico> musicoEncontrado = musicoRepository.findById(musicoId);

        if (musicoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Evento evento = new Evento();
        evento.setNomeEvento(nomeEvento);
        evento.setLocalEvento(localEvento);
        evento.setDataEvento(dataEvento);
        evento.setMusico(musicoEncontrado.get());

        Evento eventoSalvo = eventoRepository.save(evento);
        return Optional.of(eventoSalvo);
    }

    public Optional<Evento> atualizar(Long id, String nomeEvento, String localEvento, LocalDate dataEvento, Long musicoId) {
        Optional<Evento> eventoExistente = eventoRepository.findById(id);
        Optional<Musico> musicoEncontrado = musicoRepository.findById(musicoId);

        if (eventoExistente.isEmpty() || musicoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Evento evento = eventoExistente.get();
        evento.setNomeEvento(nomeEvento);
        evento.setLocalEvento(localEvento);
        evento.setDataEvento(dataEvento);
        evento.setMusico(musicoEncontrado.get());

        Evento eventoSalvo = eventoRepository.save(evento);
        return Optional.of(eventoSalvo);
    }

    public boolean deletar(Long id) {
        if (!eventoRepository.existsById(id)) {
            return false;
        }

        eventoRepository.deleteById(id);
        return true;
    }
}