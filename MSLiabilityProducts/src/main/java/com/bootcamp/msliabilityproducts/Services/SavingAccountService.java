package com.bootcamp.msliabilityproducts.Services;

import com.bootcamp.msliabilityproducts.DTO.CreateSavingAccountRequestDTO;
import com.bootcamp.msliabilityproducts.DTO.CreateSavingAccountResponseDTO;
import com.bootcamp.msliabilityproducts.DTO.SavingAccountDTO;
import com.bootcamp.msliabilityproducts.Documents.SavingAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingAccountService {
    Flux<SavingAccount> findAll();

    Mono<SavingAccount> findById(String id);

    Mono<SavingAccount> save(SavingAccount savingAccount);

    Mono<SavingAccount> update(SavingAccount savingAccount);

    Mono<Void> delete(SavingAccount savingAccount);

    Mono<CreateSavingAccountResponseDTO> createSavingAccount(CreateSavingAccountRequestDTO dto);
}
