package mapping.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
//@ToString
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    private String name;
}
