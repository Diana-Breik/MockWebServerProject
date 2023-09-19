package com.example.springwebclient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class RickAndMortyCharacterService {

    private final WebClient webClient;


    public RickAndMortyCharacterService(@Value("${rickandmorty.api.url}") String url) {
        webClient = WebClient.create(url);
    }

    public List<RickAndMortyCharacter> getCharacters() {

        ResponseEntity<RickAndMortyApiResponse> responseEntity = webClient
                .get()
                .retrieve()
                .toEntity(RickAndMortyApiResponse.class)
                .block();
        return responseEntity.getBody().results();
    }

    public RickAndMortyCharacter getCharacterById(String id) {
        String uriString = "/" + id;
        ResponseEntity<RickAndMortyCharacter> responseEntity = webClient
                .get()
                .uri(uriString)
                .retrieve()
                .toEntity(RickAndMortyCharacter.class)
                .block();
        return responseEntity.getBody();
    }

    public List<RickAndMortyCharacter> getCharactersByStatus(String status) {
        String newUri = "?status=" + status;
        ResponseEntity<RickAndMortyApiResponse> responseEntity = webClient
                .get()
                .uri(newUri)
                .retrieve()
                .toEntity(RickAndMortyApiResponse.class)
                .block();
        return responseEntity.getBody().results();
    }

    public int getStatisticForSpecies(String request, String species) {
        String newUri = "?status=" + request + "&species=" + species;
        ResponseEntity<RickAndMortyApiResponse> responseEntity = webClient
                .get()
                .uri(newUri)
                .retrieve()
                .toEntity(RickAndMortyApiResponse.class)
                .block();
        return responseEntity.getBody().info().count();
    }
}
