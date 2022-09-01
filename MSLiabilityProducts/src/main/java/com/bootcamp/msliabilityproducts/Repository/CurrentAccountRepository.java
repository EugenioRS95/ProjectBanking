package com.bootcamp.msliabilityproducts.Repository;

import com.bootcamp.msliabilityproducts.Documents.CurrentAccount;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CurrentAccountRepository extends ReactiveMongoRepository<CurrentAccount, String> {

    @Query("{'associatedCustomerId':  ?0}")
    Mono<CurrentAccount> findByAssociatedCustomerId(String idCustomer);
}
