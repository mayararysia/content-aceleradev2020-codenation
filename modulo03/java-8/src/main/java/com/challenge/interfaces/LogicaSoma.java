package com.challenge.interfaces;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

public interface LogicaSoma {

    void preencherListaValores(Object objeto, Field atributo, List<BigDecimal> numerosBigDecimal)
            throws IllegalAccessException;

    boolean validarAtributoComAnnotattion(Field atributo, Class<? extends Annotation> anotacao);

    BigDecimal obterResultadoOperacao(Class<? extends Annotation> anotacao, List<BigDecimal> numerosBigDecimal);

    BigDecimal obterCalculoAtributosBigDecimalDaClasse(Object objeto, Class<? extends Annotation> anotacao) throws IllegalAccessException;
}
