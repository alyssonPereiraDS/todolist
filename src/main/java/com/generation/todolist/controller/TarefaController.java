package com.generation.todolist.controller;

import com.generation.todolist.model.Tarefa;
import com.generation.todolist.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping
    public ResponseEntity<Tarefa> post(@Valid @RequestBody Tarefa tarefa){
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaRepository.save(tarefa));
    }
    @PutMapping
    public ResponseEntity<Tarefa> put(@Valid @RequestBody Tarefa tarefa){
        return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.save(tarefa));
    }
}
