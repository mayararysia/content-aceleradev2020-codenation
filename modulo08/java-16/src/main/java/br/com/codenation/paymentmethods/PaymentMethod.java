package br.com.codenation.paymentmethods;

public enum PaymentMethod {

    CASH(new PaymentCashStrategy()), DEBIT_CARD(new PaymentDebitCardStrategy()), CREDIT_CARD(new PaymentCreditCardStrategy()), TRANSFER(new PaymentTransferStrategy());

    private PriceStrategy priceStrategy;

    PaymentMethod(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public PriceStrategy getPaymentStrategy() {
        return priceStrategy;
    }
}