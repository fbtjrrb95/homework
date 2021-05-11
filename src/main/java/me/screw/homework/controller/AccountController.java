package me.screw.homework.controller;

import lombok.RequiredArgsConstructor;
import me.screw.homework.domain.Account;
import me.screw.homework.service.AccountService;
import me.screw.homework.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final JwtUserDetailsService accountService;

    @PostMapping("/signup")
    public ResponseEntity<Account> signup(@Validated @RequestBody Account account, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + ":" + error.getField() +    " - " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            accountService.save(account);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(account);
    }



}
