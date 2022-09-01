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
@Document(collection="BusinessAccount")
public class BusinessAccount implements Serializable {

    @Id
    private String id;

    private String businessName;
    private String ruc;
    private String address;

}
