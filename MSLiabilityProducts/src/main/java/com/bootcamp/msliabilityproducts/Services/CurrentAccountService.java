package com.bootcamp.msliabilityproducts.Services;

import com.bootcamp.msliabilityproducts.DTO.CreateCurrentAccountRequestDTO;
import com.bootcamp.msliabilityproducts.DTO.CreateCurrentAccountResponseDTO;
import com.bootcamp.msliabilityproducts.Documents.CurrentAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountService {
    Flux<CurrentAccount> findAll();

    Mono<CurrentAccount> findById(String id);

    Mono<CurrentAccount> save(CurrentAccount currentAccount);

    Mono<CurrentAccount> update(CurrentAccount currentAccount);

    Mono<Void> delete(CurrentAccount currentAccount);

    Mono<CreateCurrentAccountResponseDTO> createPersonalCurrentAccount(CreateCurrentAccountRequestDTO dto);

    Mono<CreateCurrentAccountResponseDTO> createBusinessCurrentAccount(CreateCurrentAccountRequestDTO dto);
}
