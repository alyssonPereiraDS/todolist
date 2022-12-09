package com.generation.todolist.controller;

import com.generation.todolist.model.Tarefa;
import com.generation.todolist.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Tarefa>> getAll(){
        return ResponseEntity.ok(tarefaRepository.findAll());
    }

    @GetMapping("/listar/{nome}")
    public ResponseEntity <List<Tarefa>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(tarefaRepository.findAllByNomeContainingIgnoreCase(nome));
    }
}
