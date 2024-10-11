package com.test.lri.controllers;

import com.test.lri.dto.responses.todo.ToDoResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("api/todo")
@RequiredArgsConstructor
@Data
public class ToDoController {

    private final WebClient.Builder webClientBuilder;

    @GetMapping("/list")
    private ResponseEntity<List<ToDoResponse>> list() {
        String url = "https://jsonplaceholder.typicode.com/todos";

        List<ToDoResponse> todoList = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(ToDoResponse.class)
                .collectList()
                .block();

        return ResponseEntity.ok(todoList);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ToDoResponse> getUserById(@PathVariable String todoId) {
        String url = "https://jsonplaceholder.typicode.com/todos/" + todoId;

        ToDoResponse todo = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ToDoResponse.class)
                .block();

        return ResponseEntity.ok(todo);
    }
}
