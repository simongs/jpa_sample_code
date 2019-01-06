package com.dasolute.simons.springdata;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    // 컬럼으로는 추가하지 않고 객체에서만 쓸 용도
    @Transient
    private String notNeedColumn;
    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name="street", column = @Column(name="home_street")),
                    @AttributeOverride(name="city", column = @Column(name="home_city")),
                    @AttributeOverride(name="state", column = @Column(name="home_state")),
                    @AttributeOverride(name="zipCode", column = @Column(name="home_zipcode"))

            }
    )
    private Address homeAddress;

    private Address address;

    // 한 사람이 여러개의 스터디를 만들수 있다.
    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();
}
