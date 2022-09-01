package com.bootcamp.mscustomers.Services.Impl;

import com.bootcamp.mscustomers.Documents.PersonalAccount;
import com.bootcamp.mscustomers.Repository.PersonalAccountRepository;
import com.bootcamp.mscustomers.Services.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalAccountServiceImpl implements PersonalAccountService {

    @Autowired
    PersonalAccountRepository repository;

    @Override
    public Flux<PersonalAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<PersonalAccount> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<PersonalAccount> save(PersonalAccount personalAccount) {
        return repository.save(personalAccount);
    }

    @Override
    public Mono<PersonalAccount> update(PersonalAccount personalAccount) {
        return repository.save(personalAccount);
    }

    @Override
    public Mono<Void> delete(PersonalAccount personalAccount) {
        return repository.delete(personalAccount);
    }
}
