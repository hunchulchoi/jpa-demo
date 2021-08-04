package shopping.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="ORDER_ITEM_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @OneToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;
    private int orderPrice;
    private int count;
}
