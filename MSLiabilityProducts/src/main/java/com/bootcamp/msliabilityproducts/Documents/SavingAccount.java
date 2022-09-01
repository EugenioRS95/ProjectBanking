package com.bootcamp.msliabilityproducts.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="SavingAccount")
public class SavingAccount implements Serializable {
    @Id
    private String id;

    private String associatedCustomerId;
    private Long currentAmount;
    private int movementLimit;
    private Double commissionMaintenance;
}
