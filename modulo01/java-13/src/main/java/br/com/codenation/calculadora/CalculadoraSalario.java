package br.com.codenation.calculadora;


public class CalculadoraSalario {

    public long calcularSalarioLiquido(double salarioBase) {
        return Math.round(calcularIrrf(calcularInss(salarioBase)));
    }

    private double calcularInss(double salarioBase) {
        double discount = 0.0;
        if (salarioBase <= 1500.00)
            discount = (salarioBase * 8.0) / 100.0;
        if (salarioBase >= 1500.01 && salarioBase <= 4000.00)
            discount = (salarioBase * 9.0) / 100.0;
        if (salarioBase > 4000.00)
            discount = (salarioBase * 11.0) / 100.0;
        return (salarioBase - discount);
    }

    private double calcularIrrf(double salarioBaseINSS) {
        double discount = 0.0;
        if (salarioBaseINSS >= 1039.00) {
            if (salarioBaseINSS <= 3000.0)
                discount = 0.0;
            if (salarioBaseINSS >= 3000.01 && salarioBaseINSS <= 6000.00)
                discount = (salarioBaseINSS * 7.5) / 100.0;
            if (salarioBaseINSS > 6000.00)
                discount = (salarioBaseINSS * 15.0) / 100.0;
            return (salarioBaseINSS - discount);
        }
        return discount;
    }

}
