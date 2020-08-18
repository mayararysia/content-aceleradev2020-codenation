package com.rysia.codenation.aceleradevjava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Quote {
    Integer numeroCasas;
    String token;
    String cifrado;
    String decifrado;
    String resumoCriptografico;

    public Quote() {
    }

    public Quote(Integer numeroCasas, String token, String cifrado, String decifrado, String resumoCriptografico) {
        this.numeroCasas = numeroCasas;
        this.token = token;
        this.cifrado = cifrado;
        this.decifrado = decifrado;
        this.resumoCriptografico = resumoCriptografico;
    }

    public Integer getNumeroCasas() {
        return numeroCasas;
    }

    public void setNumeroCasas(Integer numeroCasas) {
        this.numeroCasas = numeroCasas;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCifrado() {
        return cifrado;
    }

    public void setCifrado(String cifrado) {
        this.cifrado = cifrado;
    }

    public String getDecifrado() {
        return decifrado;
    }

    public void setDecifrado(String decifrado) {
        this.decifrado = decifrado;
    }

    public String getResumoCriptografico() {
        return resumoCriptografico;
    }

    public void setResumoCriptografico(String resumoCriptografico) {
        this.resumoCriptografico = resumoCriptografico;
    }
}
