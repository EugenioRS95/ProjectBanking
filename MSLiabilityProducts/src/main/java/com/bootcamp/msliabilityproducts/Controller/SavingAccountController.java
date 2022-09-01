package com.bootcamp.msliabilityproducts.Controller;

import com.bootcamp.msliabilityproducts.DTO.CreateSavingAccountRequestDTO;
import com.bootcamp.msliabilityproducts.Documents.SavingAccount;
import com.bootcamp.msliabilityproducts.Services.SavingAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/savingAccount")
public class SavingAccountController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private SavingAccountService savingAccountService;

    @GetMapping
    public Mono<ResponseEntity<Flux<SavingAccount>>> getSavingAccounts(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(savingAccountService.findAll())
        );
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<Map<String,Object>>> createSavingAccount(@RequestBody Mono<CreateSavingAccountRequestDTO> request){
        Map<String,Object> response = new HashMap<>();
        LOGGER.info("Inicio de proceso de creación de cuentas de ahorro.");

        return request.flatMap(data -> {
            return savingAccountService.createSavingAccount(data).map(p-> {
                response.put("cuenta", p);
                response.put("timestamp", new Date());
                return ResponseEntity
                        .created(URI.create("/savingAccount/"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(response);
            });
        }).onErrorResume(t -> {
            return Mono.just(t).cast(WebExchangeBindException.class)
                    .flatMap(e -> Mono.just(e.getFieldErrors()))
                    .flatMapMany(Flux::fromIterable)
                    .map(fieldErrors -> "Field: " + fieldErrors.getField() + " " + fieldErrors.getDefaultMessage())
                    .collectList()
                    .flatMap(list -> {
                        response.put("errors", list);
                        response.put("timestamp", new Date());
                        response.put("Status", HttpStatus.BAD_REQUEST.value());
                        return Mono.just(ResponseEntity.badRequest().body(response));
                    });
        });

    }
}
