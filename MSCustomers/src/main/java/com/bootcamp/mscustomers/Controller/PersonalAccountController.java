package com.bootcamp.mscustomers.Controller;

import com.bootcamp.mscustomers.Documents.PersonalAccount;
import com.bootcamp.mscustomers.Services.PersonalAccountService;
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
@RequestMapping("/personalAccount")
public class PersonalAccountController {

    @Autowired
    private PersonalAccountService personalAccountService;

    @GetMapping
    public Mono<ResponseEntity<Flux<PersonalAccount>>> getPersonalAccounts(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(personalAccountService.findAll())
        );
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PersonalAccount>> getPersonalAccountById(@PathVariable String id){
        return personalAccountService.findById(id).map(c -> ResponseEntity.ok().
                contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String,Object>>> setPersonalAccount(@RequestBody Mono<PersonalAccount> personalAccountMono){
        Map<String,Object> response = new HashMap<>();

        return personalAccountMono.flatMap(pAccount -> {
            return personalAccountService.save(pAccount).map(p ->{
                response.put("personalAccount", p);
                response.put("message","Personal Account saved successfully");
                response.put("timestamp", new Date());
                return ResponseEntity
                        .created(URI.create("/personalAccount/".concat(p.getId())))
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

    @PutMapping("/{id}")
    public Mono<ResponseEntity<PersonalAccount>> editPersonalAccount(@RequestBody PersonalAccount personalAccount, @PathVariable String id){
        return personalAccountService.findById(id).flatMap(p -> {
            p.setName(personalAccount.getName());
            p.setLastName(personalAccount.getLastName());
            p.setDni(personalAccount.getDni());
            p.setAddress(personalAccount.getAddress());
            p.setAge(personalAccount.getAge());

            return personalAccountService.save(p);
        }).map(p -> ResponseEntity.created(URI.create("/personalAccount/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePersonalAccount(@PathVariable String id){
        return personalAccountService.findById(id).flatMap(p -> {
            return personalAccountService.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}
