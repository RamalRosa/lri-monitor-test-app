package com.test.lri.controllers;

import com.test.lri.dto.responses.users.UsersListResponse;
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
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Data
public class UserController {

    private final WebClient.Builder webClientBuilder;

    @GetMapping("/list")
    public ResponseEntity<List<UsersListResponse>> usersList() {
        String url = "https://jsonplaceholder.typicode.com/users";

        List<UsersListResponse> usersList = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(UsersListResponse.class)
                .collectList()
                .block();

        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UsersListResponse> getUserById(@PathVariable String userId) {
        String url = "https://jsonplaceholder.typicode.com/users/" + userId;

        UsersListResponse user = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UsersListResponse.class)
                .block();

        return ResponseEntity.ok(user);
    }

}
