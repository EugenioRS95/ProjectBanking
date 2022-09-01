package com.bootcamp.msliabilityproducts.Repository;

import com.bootcamp.msliabilityproducts.Documents.SavingAccount;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SavingAccountRepository extends ReactiveMongoRepository<SavingAccount, String> {

    @Query("{'associatedCustomerId':  ?0}")
    Mono<SavingAccount> findByAssociatedCustomerId(String idCustomer);
}
