package com.bootcamp.mscustomers.Repository;

import com.bootcamp.mscustomers.Documents.PersonalAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonalAccountRepository extends ReactiveMongoRepository<PersonalAccount,String> {
}
