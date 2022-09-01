package com.bootcamp.msliabilityproducts.Repository;

import com.bootcamp.msliabilityproducts.Documents.FixedTermAccount;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface FixedTermAccountRepository extends ReactiveMongoRepository<FixedTermAccount, String> {

    @Query("{'associatedCustomerId':  ?0}")
    Mono<FixedTermAccount> findByAssociatedCustomerId(String idCustomer);

}
