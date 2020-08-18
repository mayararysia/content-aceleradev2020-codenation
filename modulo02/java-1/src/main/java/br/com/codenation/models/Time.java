package br.com.codenation.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Time extends CommonData {
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private List<Jogador> listFootballer;

    public Time() {
        super();
    }

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        super(id, nome, "Time");
        if(validateParamsRequired(dataCriacao, corUniformePrincipal, corUniformeSecundario)) {
            this.dataCriacao = dataCriacao;
            this.corUniformePrincipal = corUniformePrincipal;
            this.corUniformeSecundario = corUniformeSecundario;
            this.listFootballer = new ArrayList<>();
        }
    }

    private boolean validateParamsRequired(LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        final String MESSAGE_DEFAULT = "Todos os dados de time são obrigatórios!";
        if (dataCriacao == null || corUniformePrincipal == null || corUniformeSecundario == null)
            throw new  NullPointerException("Valor passado está inválido. ".concat(MESSAGE_DEFAULT));
        if (corUniformePrincipal.isEmpty() || corUniformeSecundario.isEmpty())
            throw new IllegalArgumentException("Alguma cor do uniforme está vazia! ".concat(MESSAGE_DEFAULT));
        return true;
    }

    public List<Jogador> getListFootballer() {
        return listFootballer;
    }

    public void setListFootballer(List<Jogador> listFootballer) {
        this.listFootballer = listFootballer;
    }
}
