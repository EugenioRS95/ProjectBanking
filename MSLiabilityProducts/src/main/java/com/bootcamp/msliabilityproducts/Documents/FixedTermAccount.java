package com.bootcamp.msliabilityproducts.Documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="FixedTermAccount")
public class FixedTermAccount implements Serializable {
    @Id
    private String id;

    private String associatedCustomerId;
    private Long currentAmount;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
    private Date retirementDate;

    private int movementLimit;
    private Double commissionMaintenance;

    public FixedTermAccount(String associatedCustomerId,Long currentAmount,Date retirementDate,Double commissionMaintenance){
        this.associatedCustomerId = associatedCustomerId;
        this.currentAmount = currentAmount;
        this.retirementDate = retirementDate;
        this.commissionMaintenance = commissionMaintenance;
    }
}
