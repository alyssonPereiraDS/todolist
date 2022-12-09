package com.generation.todolist;

import com.generation.todolist.repository.TarefaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

}
