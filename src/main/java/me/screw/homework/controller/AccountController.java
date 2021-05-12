package me.screw.homework.controller;

import lombok.RequiredArgsConstructor;
import me.screw.homework.config.JwtTokenUtil;
import me.screw.homework.domain.Account;
import me.screw.homework.service.JwtUserDetailsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final JwtUserDetailsService accountService;
    private final RedisTemplate<String, String> redisTemplate;
    private final JwtTokenUtil jwtTokenUtil;

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

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){

        final String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(jwtToken);
            redisTemplate.opsForValue().set(
                    jwtToken, "l",
                    expirationDate.getTime() - System.currentTimeMillis(),
                    TimeUnit.MILLISECONDS
            );
        }
        return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/test")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("test");
    }
}
