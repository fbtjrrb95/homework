package me.screw.homework.controller;

import me.screw.homework.domain.Account;
import me.screw.homework.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping(value = "/save")
    public ResponseEntity<Account> createAccount(@Validated @RequestBody Account account, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + ":" + error.getField() +    " - " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            accountService.createAccount(account);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}
