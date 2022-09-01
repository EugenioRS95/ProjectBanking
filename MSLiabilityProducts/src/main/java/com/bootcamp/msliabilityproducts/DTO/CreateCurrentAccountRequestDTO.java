package com.bootcamp.msliabilityproducts.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCurrentAccountRequestDTO implements Serializable {
    private String idCustomer;
    private Double commissionMaintenance;
    private String typeCustomer;
}
