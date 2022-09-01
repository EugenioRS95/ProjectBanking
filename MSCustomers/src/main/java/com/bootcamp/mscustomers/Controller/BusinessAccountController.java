package com.bootcamp.mscustomers.Controller;

import com.bootcamp.mscustomers.Documents.BusinessAccount;
import com.bootcamp.mscustomers.Services.BusinessAccountService;
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
@RequestMapping("/businessAccount")
public class BusinessAccountController {

    @Autowired
    private BusinessAccountService businessAccountService;

    @GetMapping
    public Mono<ResponseEntity<Flux<BusinessAccount>>> getBusinessAccounts(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(businessAccountService.findAll())
        );
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BusinessAccount>> getBusinessAccountById(@PathVariable String id){
        return businessAccountService.findById(id).map(b -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(b))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String,Object>>> setBusinessAccount(@RequestBody Mono<BusinessAccount> businessAccountMono){
        Map<String,Object> response = new HashMap<>();

        return businessAccountMono.flatMap(bAccount -> {
            return businessAccountService.save(bAccount).map(b -> {
                response.put("BusinessAccount", b);
                response.put("message", "Business Account saved Successfully");
                response.put("timestamp", new Date());
                return ResponseEntity
                        .created(URI.create("/businessAccount/".concat(b.getId())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(response);
            });
        }).onErrorResume(t -> {
            return Mono.just(t).cast(WebExchangeBindException.class)
                    .flatMap(e -> Mono.just(e.getFieldErrors()))
                    .flatMapMany(Flux::fromIterable)
                    .map(fieldErrors -> "Field: "+fieldErrors.getField()+" "+fieldErrors.getDefaultMessage())
                    .collectList()
                    .flatMap(list -> {
                        response.put("errors",list);
                        response.put("timestamp", new Date());
                        response.put("Status", HttpStatus.BAD_REQUEST.value());

                        return Mono.just(ResponseEntity.badRequest().body(response));
                    });
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<BusinessAccount>> editBusinessAccount(@RequestBody BusinessAccount businessAccount,@PathVariable String id){
        return businessAccountService.findById(id).flatMap(b -> {
            b.setBusinessName(businessAccount.getBusinessName());
            b.setRuc(businessAccount.getRuc());
            b.setAddress(businessAccount.getAddress());

            return businessAccountService.update(b);
        }).map(b -> ResponseEntity.created(URI.create("/businessAccount/".concat(b.getId())))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(b))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteBusinessAccount(@PathVariable String id){
        return businessAccountService.findById(id).flatMap(b -> {
            return businessAccountService.delete(b).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}
