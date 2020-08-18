package br.com.codenation.exceptions;

public class JogadorNaoEncontradoException extends RuntimeException{
    public JogadorNaoEncontradoException() {
        super("Jogador não encontrado!");
    }
}
