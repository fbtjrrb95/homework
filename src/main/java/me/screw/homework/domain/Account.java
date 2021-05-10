package me.screw.homework.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Account {

    @Column(nullable = false, length = 20)
    private String userName;

    @Column(nullable = false, length = 30)
    private String userNick;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 100)
    private String eMail;

    @Column
    private String gender;

}
