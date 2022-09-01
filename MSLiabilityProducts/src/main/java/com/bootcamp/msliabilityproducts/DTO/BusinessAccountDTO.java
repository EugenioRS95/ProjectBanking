package com.bootcamp.msliabilityproducts.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessAccountDTO implements Serializable {

    @Id
    private String id;

    private String businessName;
    private String ruc;
    private String address;
}
