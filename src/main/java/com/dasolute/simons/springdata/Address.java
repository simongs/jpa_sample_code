package com.dasolute.simons.springdata;

import lombok.Data;

import javax.persistence.Embeddable;

// Account 에 속해있는 객체
@Embeddable
@Data
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}
