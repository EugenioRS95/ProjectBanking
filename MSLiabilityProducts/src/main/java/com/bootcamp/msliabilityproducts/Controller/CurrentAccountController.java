package com.bootcamp.msliabilityproducts.Controller;

import com.bootcamp.msliabilityproducts.DTO.CreateCurrentAccountRequestDTO;
import com.bootcamp.msliabilityproducts.Services.CurrentAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/currentAccount")
public class CurrentAccountController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private CurrentAccountService currentAccountService;

    @PostMapping("/create")
    public Mono<ResponseEntity<Map<String,Object>>> createCurrentAccount(@RequestBody Mono<CreateCurrentAccountRequestDTO> request){
        Map<String,Object> response = new HashMap<>();
        LOGGER.info("Inicio de proceso de creación de cuentas corriente.");

        return request.flatMap(data -> {
            LOGGER.info(data.getTypeCustomer());
            return currentAccountService.createPersonalCurrentAccount(data).map(c-> {
                response.put("cuenta", c);
                response.put("timestamp", new Date());
                return ResponseEntity
                        .created(URI.create("/currentAccount/"))
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
