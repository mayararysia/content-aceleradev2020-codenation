package br.com.codenation.exceptions;

public class CapitaoNaoInformadoException extends RuntimeException{
    public CapitaoNaoInformadoException() {
        super("Capitão não informado!");
    }
}
