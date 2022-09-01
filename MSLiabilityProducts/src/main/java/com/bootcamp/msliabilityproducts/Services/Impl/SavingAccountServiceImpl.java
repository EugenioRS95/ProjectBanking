package com.bootcamp.msliabilityproducts.Services.Impl;

import com.bootcamp.msliabilityproducts.DTO.CreateSavingAccountRequestDTO;
import com.bootcamp.msliabilityproducts.DTO.CreateSavingAccountResponseDTO;
import com.bootcamp.msliabilityproducts.DTO.PersonalAccountDTO;
import com.bootcamp.msliabilityproducts.DTO.SavingAccountDTO;
import com.bootcamp.msliabilityproducts.Documents.SavingAccount;
import com.bootcamp.msliabilityproducts.Repository.SavingAccountRepository;
import com.bootcamp.msliabilityproducts.Services.SavingAccountService;
import com.bootcamp.msliabilityproducts.WebClientService.ValidateCostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingAccountServiceImpl implements SavingAccountService {

    @Autowired
    private SavingAccountRepository repository;

    @Autowired
    private ValidateCostumerService validateCostumerService;

    @Override
    public Flux<SavingAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<SavingAccount> findById(String id) {
        return null;
    }

    @Override
    public Mono<SavingAccount> save(SavingAccount savingAccount) {
        return null;
    }

    @Override
    public Mono<SavingAccount> update(SavingAccount savingAccount) {
        return null;
    }

    @Override
    public Mono<Void> delete(SavingAccount savingAccount) {
        return null;
    }

    @Override
    public Mono<CreateSavingAccountResponseDTO> createSavingAccount(CreateSavingAccountRequestDTO customerData) {

        return validateCostumerService.getCustomer(customerData.getIdCustomer()).flatMap(customer -> {

            return repository.findByAssociatedCustomerId(customer.getId()).flatMap(account -> {
                return Mono.just(new CreateSavingAccountResponseDTO("El cliente ya cuenta con una cuenta de ahorro.",null));
            }).switchIfEmpty(Mono.just(new SavingAccount()).flatMap(sa -> {
                sa.setAssociatedCustomerId(customer.getId());
                sa.setCurrentAmount(0L);
                sa.setMovementLimit(customerData.getMovementLimit());
                sa.setCommissionMaintenance(0.0);
                return repository.save(sa).flatMap(savingAccount -> {

                    Mono<CreateSavingAccountResponseDTO> resp = Mono.just( new CreateSavingAccountResponseDTO("Cuenta creada con exito", savingAccount));
                    return resp;
                });
            }));
        }).switchIfEmpty(Mono.just(new CreateSavingAccountResponseDTO("El cliente no está registrado.",null)));

    }
}
