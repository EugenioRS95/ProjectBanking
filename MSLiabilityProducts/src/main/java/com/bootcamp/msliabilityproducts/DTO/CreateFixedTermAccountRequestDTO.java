package com.bootcamp.msliabilityproducts.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFixedTermAccountRequestDTO implements Serializable {
    private String idCustomer;
    private Date retirementDate;
    private String typeCustomer;
}
