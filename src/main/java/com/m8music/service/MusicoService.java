package com.m8music.service;

import com.m8music.model.Musico;
import com.m8music.repository.MusicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicoService {

    private final MusicoRepository musicoRepository;

    public MusicoService(MusicoRepository musicoRepository) {
        this.musicoRepository = musicoRepository;
    }

    public List<Musico> listarTodos() {
        return musicoRepository.findAll();
    }

    public Optional<Musico> buscarPorId(Long id) {
        return musicoRepository.findById(id);
    }

    public Musico cadastrar(Musico musico) {
        return musicoRepository.save(musico);
    }

    public Optional<Musico> atualizar(Long id, Musico dadosAtualizados) {
        Optional<Musico> musicoExistente = musicoRepository.findById(id);

        if (musicoExistente.isEmpty()) {
            return Optional.empty();
        }

        Musico musico = musicoExistente.get();
        musico.setNome(dadosAtualizados.getNome());
        musico.setGeneroMusical(dadosAtualizados.getGeneroMusical());
        musico.setEmail(dadosAtualizados.getEmail());

        Musico musicoSalvo = musicoRepository.save(musico);
        return Optional.of(musicoSalvo);
    }

    public boolean deletar(Long id) {
        if (!musicoRepository.existsById(id)) {
            return false;
        }

        musicoRepository.deleteById(id);
        return true;
    }
}