package com.bootcamp.mscustomers.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="PersonalAccount")
public class PersonalAccount implements Serializable {
    @Id
    private String id;

    private String name;
    private String lastName;
    private String dni;
    private String address;
    private int age;

}
