package shopping.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Order {

    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<Item> orderItems;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ORDER_DATE")
    private Date orderDate;
    private OrderStatus status;

    public enum OrderStatus{ORDER, CANCEL};
}
