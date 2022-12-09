package com.generation.todolist;

import com.generation.todolist.model.Tarefa;
import com.generation.todolist.repository.TarefaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodolistApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private TarefaRepository tarefaRepository;

	@Test
	@DisplayName("Listar Tarefas")
	public void deveMostrarTarefas(){
		ResponseEntity<String> resposta= testRestTemplate
				.exchange("/tarefas/listar", HttpMethod.GET, null, String.class);
				assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	@Test
	@DisplayName("Listar por Nome")
	public void deveMostrarTarefaPorNome(){
		Tarefa procuraTarefa= tarefaRepository.save(new Tarefa(0L, "tarefa 1", "tarefa número 1", "Alysson", LocalDate.now(), true));
		ResponseEntity<String> resposta= testRestTemplate
				.exchange("/tarefas/listar/"+ procuraTarefa.getNome(), HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	@Test
	@DisplayName("Atualizar tarefa")
	public void deveAtualizarUmaTarefa(){
		Tarefa tarefaCadastrada= tarefaRepository.save(new Tarefa(0L, "tarefa 2", "tarefa número 2", "Edu", LocalDate.now(), true));
		Tarefa tarefaAtualizada= new Tarefa(tarefaCadastrada.getId(), "tarefa 2", "tarefa número 2", "Eduardo", LocalDate.now(), true);
		HttpEntity<Tarefa> corpoRequisicao = new HttpEntity<>(tarefaAtualizada);
		ResponseEntity<Tarefa> corpoResposta=testRestTemplate
				.exchange("/tarefas/atualizar", HttpMethod.PUT, corpoRequisicao, Tarefa.class);
		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());

		assertEquals(corpoRequisicao.getBody().getId(), corpoResposta.getBody().getId());

	}

	@Test
	@DisplayName("Cadastrar Tarefa")
	public void deveCadastrarUmaTarefa(){
		Tarefa tarefa = (new Tarefa(0L, "tarefa 3", "tarefa número 3", "Moises", LocalDate.now(), true));
		HttpEntity<Tarefa> corpoRequisicao = new HttpEntity<Tarefa>(tarefa);
		ResponseEntity<Tarefa> corpoResposta = testRestTemplate
			.exchange("/tarefas", HttpMethod.POST, corpoRequisicao, Tarefa.class);
		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());
	}

}
