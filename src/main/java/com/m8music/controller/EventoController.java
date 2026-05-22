package com.m8music.controller;

import com.m8music.model.Evento;
import com.m8music.service.EventoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> listar() {
        return eventoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        return eventoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Evento> cadastrar(@RequestBody @Valid EventoRequest request) {
        return eventoService.cadastrar(
                        request.nomeEvento(),
                        request.localEvento(),
                        request.dataEvento(),
                        request.musicoId()
                )
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable Long id, @RequestBody @Valid EventoRequest request) {
        return eventoService.atualizar(
                        id,
                        request.nomeEvento(),
                        request.localEvento(),
                        request.dataEvento(),
                        request.musicoId()
                )
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = eventoService.deletar(id);

        if (!deletado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    public record EventoRequest(
            @NotBlank String nomeEvento,
            @NotBlank String localEvento,
            @NotNull LocalDate dataEvento,
            @NotNull Long musicoId
    ) {
    }
}