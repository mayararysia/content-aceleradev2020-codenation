package br.com.codenation.exceptions;

public class TimeNaoEncontradoException extends RuntimeException{
    public TimeNaoEncontradoException() {
        super("Time não encontrado!");
    }
}
