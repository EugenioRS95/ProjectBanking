package com.bootcamp.mscustomers.Repository;

import com.bootcamp.mscustomers.Documents.BusinessAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BusinessAccountRepository extends ReactiveMongoRepository<BusinessAccount,String> {
}
