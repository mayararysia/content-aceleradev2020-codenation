package br.com.codenation.paymentmethods;

public class PaymentDebitCardStrategy implements PriceStrategy {

    @Override
    public Double calculate(Double price) {
        return price * 0.95;
    }
}
