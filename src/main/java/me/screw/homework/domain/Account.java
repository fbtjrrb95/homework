package me.screw.homework.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Account {

    @Column
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 20)
    @NotBlank
    private String name;

    @Column(length = 30)
    @NotBlank
    private String nick;

    @Column
    @Min(10)
    @NotBlank
    private String password;

    @Column(length = 20)
    @NotBlank
    private String phonenumber;

    @Column(length = 100)
    @NotBlank
    private String email;

    @Column
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
