package me.screw.homework.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Size(max=20)
    private String name;


    @NotBlank
    @Size(max=30)
    private String nick;

    @Size(min=10)
    @NotBlank
    private String password;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    @NotBlank
    @Size(max=20)
    private String phonenumber;


    @NotBlank
    @Size(max=100)
    @Email
    private String email;

    private String gender;

    @Builder
    public Account(
            @NotBlank String name,
            @NotBlank String nick,
            @NotBlank String password,
            @NotBlank String phonenumber,
            @NotBlank String email,
            String gender){

        this.name = name;
        this.nick = nick;
//        this.password = new BCryptPasswordEncoder().encode(password);
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.gender = gender;
    }

}
