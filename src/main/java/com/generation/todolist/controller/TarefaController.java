package com.generation.todolist.controller;

import com.generation.todolist.model.Tarefa;
import com.generation.todolist.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
