package ch02.starter;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="MEMBER")
@Data
@ToString
public class Member {

    @Id
    private String id;

    @Column(name = "NAME")
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    private Date lastModiedDate;

    @Lob
    private String descriptor;

    public enum RoleType {USER, ADMIN};
}
