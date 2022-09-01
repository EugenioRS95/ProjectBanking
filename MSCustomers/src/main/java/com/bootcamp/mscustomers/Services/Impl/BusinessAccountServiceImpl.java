package com.bootcamp.mscustomers.Services.Impl;

import com.bootcamp.mscustomers.Documents.BusinessAccount;
import com.bootcamp.mscustomers.Repository.BusinessAccountRepository;
import com.bootcamp.mscustomers.Services.BusinessAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusinessAccountServiceImpl implements BusinessAccountService {

    @Autowired
    private BusinessAccountRepository repository;

    @Override
    public Flux<BusinessAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<BusinessAccount> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<BusinessAccount> save(BusinessAccount businessAccount) {
        return repository.save(businessAccount);
    }

    @Override
    public Mono<BusinessAccount> update(BusinessAccount businessAccount) {
        return repository.save(businessAccount);
    }

    @Override
    public Mono<Void> delete(BusinessAccount businessAccount) {
        return repository.delete(businessAccount);
    }
}
