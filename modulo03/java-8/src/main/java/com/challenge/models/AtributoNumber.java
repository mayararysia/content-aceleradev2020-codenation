package com.challenge.models;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;

import java.math.BigDecimal;

public class AtributoNumber {
    @Somar
    private BigDecimal a;
    @Somar
    private BigDecimal b;
    @Somar
    private BigDecimal c;
    @Subtrair
    private BigDecimal d;
    @Subtrair
    private Integer e;
    @Subtrair
    private BigDecimal f;
    private double g;

    public AtributoNumber(BigDecimal d, BigDecimal f) {
        this.d = d;
        this.f = f;
    }

    public AtributoNumber(BigDecimal a, BigDecimal b, BigDecimal c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public AtributoNumber(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal d, BigDecimal f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.f = f;
    }

    public AtributoNumber(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal d, Integer e, BigDecimal f, double g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
}
