package me.screw.homework.domain;

import lombok.Builder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Account {

    @Column
    @Id
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 30)
    private String nick;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String phonenumber;

    @Column(nullable = false, length = 100)
    private String email;

    @Column
    private String gender;

    @Builder
    public Account(String name, String nick, String password, String phonenumber, String email, String gender){
        this.name = name;
        this.nick = nick;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.phonenumber = phonenumber;
        this.email = email;
        this.gender = gender;
    }

}
