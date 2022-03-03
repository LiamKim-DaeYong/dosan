package com.samin.dosan.core.code;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {
    private String zipCode;
    private String roadAddress;
    private String detailAddress;
}
