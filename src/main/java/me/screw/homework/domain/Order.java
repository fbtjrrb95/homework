package me.screw.homework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity(name = "orders")
@Getter
@Setter
public class Order {

    @Column
    @Id
    @GeneratedValue
    private long id;

    @Column
    @Size(max=12)
    @NotBlank
    private String orderNumber;

    @Column
    @Size(max=100)
    @NotBlank
    private String productName;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @NotBlank
    private Timestamp paymentDate;
}
