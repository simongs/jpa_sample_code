package com.dasolute.simons.springdata;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Study {

    @Id @GeneratedValue
    private Long id;

    private String name;

    // 스터디 입장에서는 한개의 주인장이 있으므로 One로 끝나야 한다.
    @ManyToOne
    private Account owner;
}
