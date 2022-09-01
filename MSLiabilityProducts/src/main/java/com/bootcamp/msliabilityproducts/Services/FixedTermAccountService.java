package com.bootcamp.msliabilityproducts.Services;

import com.bootcamp.msliabilityproducts.DTO.CreateFixedTermAccountRequestDTO;
import com.bootcamp.msliabilityproducts.DTO.CreateFixedTermAccountResponseDTO;
import com.bootcamp.msliabilityproducts.Documents.FixedTermAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedTermAccountService {

    Flux<FixedTermAccount> findAll();

    Mono<FixedTermAccount> findById(String id);

    Mono<FixedTermAccount> save(FixedTermAccount fixedTermAccount);

    Mono<FixedTermAccount> update(FixedTermAccount fixedTermAccount);

    Mono<Void> delete(FixedTermAccount fixedTermAccount);

    Mono<CreateFixedTermAccountResponseDTO> createPersonalFixedTermAccount(CreateFixedTermAccountRequestDTO createFixedTermAccountRequestDTO);
}
