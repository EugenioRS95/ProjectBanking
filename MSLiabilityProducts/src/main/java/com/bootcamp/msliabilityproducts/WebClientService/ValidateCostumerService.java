package com.bootcamp.msliabilityproducts.WebClientService;

import com.bootcamp.msliabilityproducts.DTO.BusinessAccountDTO;
import com.bootcamp.msliabilityproducts.DTO.PersonalAccountDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class ValidateCostumerService {
    private final WebClient webClient;

    public ValidateCostumerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8020").build();
    }

    public Mono<PersonalAccountDTO> getCustomer(String id) {
        return this.webClient.get().uri("/personalAccount/{id}",id).retrieve().bodyToMono(PersonalAccountDTO.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
    }

    public Mono<BusinessAccountDTO> getBusiness(String id) {
        return this.webClient.get().uri("/businessAccount/{id}",id).retrieve().bodyToMono(BusinessAccountDTO.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
    }
}
