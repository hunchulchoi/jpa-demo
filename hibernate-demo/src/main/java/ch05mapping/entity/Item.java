package ch05mapping.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ToString
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    private String name;
    private int price;

    @Column(name="STOCK_QUANTITY")
    private int stockQuantity;

}
