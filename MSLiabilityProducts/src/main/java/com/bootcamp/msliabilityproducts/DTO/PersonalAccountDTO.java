package com.bootcamp.msliabilityproducts.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalAccountDTO implements Serializable {
    @Id
    private String id;

    private String name;
    private String lastName;
    private String dni;
    private String address;
    private int age;
}
