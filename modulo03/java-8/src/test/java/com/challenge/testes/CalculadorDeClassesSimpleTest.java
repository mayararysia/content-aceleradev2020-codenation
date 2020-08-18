package com.challenge.testes;

import com.challenge.desafio.CalculadorDeClasses;
import com.challenge.models.AtributoNumber;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculadorDeClassesSimpleTest {

    @Test(expected = NullPointerException.class)
    public void deveRetornarErroEmSomarQuandoObjetoNulo() throws IllegalAccessException {
        final CalculadorDeClasses desafio = new CalculadorDeClasses();
        desafio.somar(null);
    }

    @Test(expected = NullPointerException.class)
    public void deveRetornarErroEmSubtrairQuandoObjetoNulo() throws IllegalAccessException {
        final CalculadorDeClasses desafio = new CalculadorDeClasses();
        desafio.subtrair(null);
    }

    @Test(expected = NullPointerException.class)
    public void deveRetornarErroTotalizarQuandoObjetoNulo() throws IllegalAccessException {
        final CalculadorDeClasses desafio = new CalculadorDeClasses();
        desafio.totalizar(null);
    }

    @Test
    public void deveSomarValoresAtributosBigDecimal() throws IllegalAccessException {
        final CalculadorDeClasses desafio = new CalculadorDeClasses();

        assertEquals(new BigDecimal(2), desafio.somar(
                new AtributoNumber(null,
                        new BigDecimal(1),
                        new BigDecimal(1)))
        );

        assertEquals(new BigDecimal(3), desafio.somar(
                new AtributoNumber(new BigDecimal(1),
                        new BigDecimal(1),
                        new BigDecimal(1),
                        new BigDecimal(1), 1,
                        new BigDecimal(1), 1.0)
        ));

        assertEquals(new BigDecimal(-3), desafio.somar(
                new AtributoNumber(new BigDecimal(-1),
                        new BigDecimal(-1),
                        new BigDecimal(-1),
                        new BigDecimal(-1), 1,
                        new BigDecimal(-1), 10.0)
        ));
    }

    @Test
    public void deveSubtrairValoresAtributosBigDecimal() throws IllegalAccessException {
        final CalculadorDeClasses desafio = new CalculadorDeClasses();
        assertEquals(new BigDecimal(3),
                desafio.subtrair(new AtributoNumber(new BigDecimal(1), new BigDecimal(-2))));

        assertEquals(new BigDecimal(1), desafio.subtrair(
                new AtributoNumber(null,
                        new BigDecimal(100),
                        new BigDecimal(1),
                        new BigDecimal(1), 1, null, 1.0))
        );

        assertEquals(new BigDecimal(0),
                desafio.subtrair(new AtributoNumber(
                        new BigDecimal(-1),
                        new BigDecimal(-10),
                        new BigDecimal(-1),
                        new BigDecimal(-1), 1,
                        new BigDecimal(-1), 10.0)));
    }

    @Test
    public void deveTotalizar() throws IllegalAccessException {
        final CalculadorDeClasses desafio = new CalculadorDeClasses();
        assertEquals(new BigDecimal(9),
                desafio.totalizar(
                        new AtributoNumber(new BigDecimal(3),
                                new BigDecimal(3),
                                new BigDecimal(3),
                                new BigDecimal(2), 1,
                                new BigDecimal(2), 1.0)));

        assertEquals(new BigDecimal(-3), desafio.totalizar(
                new AtributoNumber(new BigDecimal(-1),
                        new BigDecimal(-1),
                        new BigDecimal(1),
                        new BigDecimal(1), 1,
                        new BigDecimal(-1), 1.0)));

        assertEquals(new BigDecimal(-1), desafio.totalizar(
                new AtributoNumber(null, null, null, new BigDecimal(1), 1, null, 1.0)));
    }

}
