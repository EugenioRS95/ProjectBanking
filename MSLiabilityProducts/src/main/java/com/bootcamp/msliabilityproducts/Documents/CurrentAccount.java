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
@Document(collection="CurrentAccount")
public class CurrentAccount implements Serializable {
    @Id
    private String id;

    private String associatedCustomerId;
    private Long currentAmount;
    private Double commissionMaintenance;
    private String typeCustomer;

    public CurrentAccount(String associatedCustomerId,Long currentAmount,Double commissionMaintenance,String typeCustomer){
        this.associatedCustomerId = associatedCustomerId;
        this.currentAmount = currentAmount;
        this.commissionMaintenance = commissionMaintenance;
        this.typeCustomer = typeCustomer;
    }
}
