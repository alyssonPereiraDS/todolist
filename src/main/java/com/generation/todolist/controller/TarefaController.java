package com.generation.todolist.controller;

import com.generation.todolist.model.Tarefa;
import com.generation.todolist.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        if (tarefaRepository.existsById(tarefa.getId()))
            return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.save(tarefa));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Tarefa> tarefa= tarefaRepository.findById(id);
        if (tarefa.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        tarefaRepository.deleteById(id);
    }
}
