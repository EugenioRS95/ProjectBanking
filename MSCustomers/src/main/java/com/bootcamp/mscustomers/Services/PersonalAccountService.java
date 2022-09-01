package com.bootcamp.mscustomers.Services;

import com.bootcamp.mscustomers.Documents.PersonalAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalAccountService {
    Flux<PersonalAccount> findAll();

    Mono<PersonalAccount> findById(String id);

    Mono<PersonalAccount> save(PersonalAccount personalAccount);

    Mono<PersonalAccount> update(PersonalAccount personalAccount);

    Mono<Void> delete(PersonalAccount personalAccount);
}
