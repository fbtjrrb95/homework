package me.screw.homework.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails {

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

    @NotBlank
    @Size(max=20)
    private String phonenumber;


    @NotBlank
    @Size(max=100)
    @Email
    @Column(unique = true)
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
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.gender = gender;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

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
}
