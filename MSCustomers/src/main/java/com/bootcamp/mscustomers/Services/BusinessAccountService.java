package com.bootcamp.mscustomers.Services;

import com.bootcamp.mscustomers.Documents.BusinessAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessAccountService {
    Flux<BusinessAccount> findAll();

    Mono<BusinessAccount> findById(String id);

    Mono<BusinessAccount> save(BusinessAccount businessAccount);

    Mono<BusinessAccount> update(BusinessAccount businessAccount);

    Mono<Void> delete(BusinessAccount businessAccount);
}
