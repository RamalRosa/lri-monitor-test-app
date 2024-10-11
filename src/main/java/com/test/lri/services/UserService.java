package com.test.lri.services;

import com.test.lri.dto.responses.users.UsersListResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    public List<UsersListResponse> getUsersList() {
        Mono<List<UsersListResponse>> usersListResponseMono = webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(UsersListResponse.class)
                .collectList();

        return usersListResponseMono.block(); // Blocking the response to return the list synchronously
    }
}
