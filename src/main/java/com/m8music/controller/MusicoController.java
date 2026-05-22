package com.m8music.controller;

import com.m8music.model.Musico;
import com.m8music.service.MusicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicos")
public class MusicoController {

    private final MusicoService musicoService;

    public MusicoController(MusicoService musicoService) {
        this.musicoService = musicoService;
    }

    @GetMapping
    public List<Musico> listar() {
        return musicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musico> buscarPorId(@PathVariable Long id) {
        return musicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Musico> cadastrar(@RequestBody @Valid Musico musico) {
        Musico musicoCadastrado = musicoService.cadastrar(musico);
        return ResponseEntity.ok(musicoCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musico> atualizar(@PathVariable Long id, @RequestBody @Valid Musico dadosAtualizados) {
        return musicoService.atualizar(id, dadosAtualizados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = musicoService.deletar(id);

        if (!deletado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}