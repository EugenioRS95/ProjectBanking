package com.bootcamp.msliabilityproducts.DTO;

import com.bootcamp.msliabilityproducts.Documents.CurrentAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCurrentAccountResponseDTO implements Serializable {
    private String message;
    private CurrentAccount currentAccount;
}
