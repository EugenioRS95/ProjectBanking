package com.bootcamp.msliabilityproducts.DTO;

import com.bootcamp.msliabilityproducts.Documents.FixedTermAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFixedTermAccountResponseDTO implements Serializable {
    private String message;
    private FixedTermAccount fixedTermAccount;
}
