package shopping.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String street;
    private String city;
    private String zipcode;

    @OneToMany(mappedBy = "member")
    private List<Order> orders;
}
