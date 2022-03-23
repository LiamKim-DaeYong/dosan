package com.samin.dosan.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(length = 10)
    private String zipCode;

    @Column(length = 100)
    private String roadAddress;

    @Column(length = 50)
    private String detailAddress;
}
