package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.interfaces.LogicaSoma;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class LogicaCalculadorSoma implements LogicaSoma {
    private  boolean existeAnnotationNoAtributoBigDecimal;

    public LogicaCalculadorSoma() {
        this.existeAnnotationNoAtributoBigDecimal = false;
    }

    @Override
    public void preencherListaValores(Object objeto, Field atributo, List<BigDecimal> numerosBigDecimal) throws IllegalAccessException{
        atributo.setAccessible(true);
        numerosBigDecimal.add(atributo.get(objeto) != null ? new BigDecimal(atributo.get(objeto).toString()) : BigDecimal.ZERO);
    }

    @Override
    public  boolean validarAtributoComAnnotattion(Field atributo, Class<? extends Annotation> anotacao) {
        return atributo.isAnnotationPresent(anotacao) && atributo.getType().equals(BigDecimal.class);
    }

    @Override
    public BigDecimal obterResultadoOperacao(Class<? extends Annotation> anotacao, List<BigDecimal> numerosBigDecimal) {
        return !this.existeAnnotationNoAtributoBigDecimal ?  BigDecimal.ZERO :
                (anotacao.equals(Somar.class) ?  numerosBigDecimal.stream().reduce(BigDecimal.ZERO, BigDecimal::add)
                        : numerosBigDecimal.stream().reduce(BigDecimal.ZERO, BigDecimal::subtract));
    }

    @Override
    public BigDecimal obterCalculoAtributosBigDecimalDaClasse(Object objeto, Class<? extends Annotation> anotacao) throws IllegalAccessException {
        this.existeAnnotationNoAtributoBigDecimal = false;
        List<BigDecimal> numerosBigDecimal = new ArrayList<>();

        if (objeto == null)
            throw new NullPointerException();

        for (Field atributo : objeto.getClass().getDeclaredFields()) {
            if (this.validarAtributoComAnnotattion(atributo, anotacao)) {
                this.existeAnnotationNoAtributoBigDecimal = true;
                this.preencherListaValores(objeto, atributo, numerosBigDecimal);
            }
        }
        return this.obterResultadoOperacao(anotacao, numerosBigDecimal);
    }

}
