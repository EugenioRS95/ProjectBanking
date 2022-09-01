package com.bootcamp.msliabilityproducts.Services.Impl;

import com.bootcamp.msliabilityproducts.DTO.CreateFixedTermAccountRequestDTO;
import com.bootcamp.msliabilityproducts.DTO.CreateFixedTermAccountResponseDTO;
import com.bootcamp.msliabilityproducts.DTO.PersonalAccountDTO;
import com.bootcamp.msliabilityproducts.Documents.FixedTermAccount;
import com.bootcamp.msliabilityproducts.Repository.FixedTermAccountRepository;
import com.bootcamp.msliabilityproducts.Services.FixedTermAccountService;
import com.bootcamp.msliabilityproducts.WebClientService.ValidateCostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class FixedTermAccountServiceImpl implements FixedTermAccountService {

    @Autowired
    private ValidateCostumerService validateCostumerService;

    @Autowired
    FixedTermAccountRepository repository;

    @Override
    public Flux<FixedTermAccount> findAll() {
        return null;
    }

    @Override
    public Mono<FixedTermAccount> findById(String id) {
        return null;
    }

    @Override
    public Mono<FixedTermAccount> save(FixedTermAccount fixedTermAccount) {
        return null;
    }

    @Override
    public Mono<FixedTermAccount> update(FixedTermAccount fixedTermAccount) {
        return null;
    }

    @Override
    public Mono<Void> delete(FixedTermAccount fixedTermAccount) {
        return null;
    }

    @Override
    public Mono<CreateFixedTermAccountResponseDTO> createPersonalFixedTermAccount(CreateFixedTermAccountRequestDTO request) {
        CreateFixedTermAccountResponseDTO response = new CreateFixedTermAccountResponseDTO();
        FixedTermAccount fixedTermAccount = new FixedTermAccount(request.getIdCustomer(),0L,request.getRetirementDate(),0.0);

        Mono<PersonalAccountDTO> accountExist = validateCostumerService.getCustomer(request.getIdCustomer()).map(obj -> obj).switchIfEmpty(Mono.just(new PersonalAccountDTO()).map(obj -> {
            obj.setId("");
            return obj;
        }));


        Mono<FixedTermAccount> auxFixedTermAcc = accountExist.flatMap(obj -> {
            if(!obj.getId().equals("")){
                return repository.findByAssociatedCustomerId(obj.getId());
            }
            response.setMessage("El cliente no se encuentra registrado");
            return Mono.just(new FixedTermAccount()).map(acc -> {
                acc.setId("noExist");
                return acc;
            });
        });


        return auxFixedTermAcc.flatMap(obj -> {
           if(obj.getId().equals("noExist")){
               return Mono.just(response);
           }
           if(!obj.getId().equals("") && !Objects.equals(obj.getId(),"noExist")){
               response.setMessage("El cliente ya tiene una cuenta a plazo fijo.");
               return Mono.just(response);
           }

           return repository.save(fixedTermAccount).flatMap(fixedAcc -> {
               response.setMessage("Cuenta a plazo fijo creada con éxito");
               response.setFixedTermAccount(fixedAcc);
               return Mono.just(response);
           });
        });

    }
}
