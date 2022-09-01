package com.bootcamp.msliabilityproducts.Services.Impl;

import com.bootcamp.msliabilityproducts.DTO.*;
import com.bootcamp.msliabilityproducts.Documents.CurrentAccount;
import com.bootcamp.msliabilityproducts.Repository.CurrentAccountRepository;
import com.bootcamp.msliabilityproducts.Services.CurrentAccountService;
import com.bootcamp.msliabilityproducts.WebClientService.ValidateCostumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private ValidateCostumerService validateCostumerService;

    @Autowired
    private CurrentAccountRepository repository;

    @Override
    public Flux<CurrentAccount> findAll() {
        return null;
    }

    @Override
    public Mono<CurrentAccount> findById(String id) {
        return null;
    }

    @Override
    public Mono<CurrentAccount> save(CurrentAccount currentAccount) {
        return null;
    }

    @Override
    public Mono<CurrentAccount> update(CurrentAccount currentAccount) {
        return null;
    }

    @Override
    public Mono<Void> delete(CurrentAccount currentAccount) {
        return null;
    }

    @Override
    public Mono<CreateCurrentAccountResponseDTO> createPersonalCurrentAccount(CreateCurrentAccountRequestDTO requestDTO) {

        CreateCurrentAccountResponseDTO response = new CreateCurrentAccountResponseDTO("Sin proceso",null);
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setAssociatedCustomerId(requestDTO.getIdCustomer());
        currentAccount.setCurrentAmount(0l);
        currentAccount.setCommissionMaintenance(requestDTO.getCommissionMaintenance());
        currentAccount.setTypeCustomer(requestDTO.getTypeCustomer());

        Mono<PersonalAccountDTO> userExist = validateCostumerService.getCustomer(requestDTO.getIdCustomer()).map(obj -> obj).switchIfEmpty(Mono.just(new PersonalAccountDTO()).map(obj -> {
            obj.setId("");
            return obj;
        }));

        Mono<CurrentAccount> auxcu = userExist.flatMap(obj -> {
            if(!obj.getId().equals("")){
                return repository.findByAssociatedCustomerId(obj.getId());
            }
            response.setMessage("Usuario no existe");
            return Mono.just(new CurrentAccount()).map(acc-> {
                acc.setId("noExist");
                return acc;
            });
        });

        return auxcu.flatMap(obj -> {
            if(obj.getId().equals("noExist")){
                return Mono.just(response);
            }

            if(!obj.getId().equals("") && !Objects.equals(obj.getId(), "noExist")){
                response.setMessage("El cliente ya tiene una cuenta.");
                return Mono.just(response);
            }

            return repository.save(currentAccount).flatMap(current -> {
                response.setMessage("guardado");
                response.setCurrentAccount(current);
                return Mono.just(response);
            });
        });

        /* return validateCostumerService.getCustomer(requestDTO.getIdCustomer())
                .flatMap(obj -> {
                    return repository.findByAssociatedCustomerId(requestDTO.getIdCustomer())
                            .flatMap(objCurrentAcc -> {
                                response.setMessage("El cliente ya tiene una cuenta corriente.");
                                return Mono.just(response);
                            })
                            .switchIfEmpty(repository.save(currentAccount)
                                    .flatMap(currentAcc -> {
                                        response.setMessage("Cuenta corriente creada con exito");
                                        response.setCurrentAccount(currentAccount);
                                        return Mono.just(response);
                                    })
                            );
                })
                .switchIfEmpty(Mono.just(response).map(r->{r.setMessage("El cliente no se encuentra registrado."); return r;})); */


    }

    @Override
    public Mono<CreateCurrentAccountResponseDTO> createBusinessCurrentAccount(CreateCurrentAccountRequestDTO requestDTO) {

        CreateCurrentAccountResponseDTO response = new CreateCurrentAccountResponseDTO();
        CurrentAccount currentAccount = new CurrentAccount(requestDTO.getIdCustomer(),0L,requestDTO.getCommissionMaintenance(),requestDTO.getTypeCustomer());

        Mono<BusinessAccountDTO> accountExist = validateCostumerService.getBusiness(requestDTO.getIdCustomer()).map(obj -> obj).switchIfEmpty(Mono.just(new BusinessAccountDTO())).map(obj -> {
            obj.setId("");
            return obj;
        });

        Mono<CurrentAccount> auxCurrentAcc = accountExist.flatMap(obj -> {
            if(!obj.getId().equals("")){
                return repository.findByAssociatedCustomerId(obj.getId());
            }
            response.setMessage("El cliente no se encuentra registrado");
            return Mono.just(new CurrentAccount()).map(acc -> {
                acc.setId("noExist");
                return acc;
            });
        });

        return auxCurrentAcc.flatMap(obj -> {
            if(obj.getId().equals("noExist")){
                return Mono.just(response);
            }

            return repository.save(currentAccount).flatMap(currentAcc -> {
                response.setMessage("Cuenta corriente empresarial creada con éxito");
                response.setCurrentAccount(currentAccount);
                return Mono.just(response);
            });
        });
    }




}
