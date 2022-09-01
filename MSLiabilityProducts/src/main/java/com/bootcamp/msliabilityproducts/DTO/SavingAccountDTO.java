package com.bootcamp.msliabilityproducts.DTO;

import com.bootcamp.msliabilityproducts.Documents.SavingAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccountDTO implements Serializable {
    private SavingAccount savingAccount;
    private PersonalAccountDTO personalAccountDTO;
}
