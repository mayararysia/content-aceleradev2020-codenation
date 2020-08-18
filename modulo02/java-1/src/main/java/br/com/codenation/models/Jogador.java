package br.com.codenation.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador extends CommonData{

    private Long idTime;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;
    private Boolean isCaptain;

    public Jogador() {
        super();
        this.isCaptain = false;
    }

    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        super(id, nome, "Jogador");
        this.isCaptain = false;
        if (validateParamsRequired(idTime, dataNascimento, nivelHabilidade,salario)){
            this.idTime = idTime;
            this.dataNascimento = dataNascimento;
            this.nivelHabilidade = nivelHabilidade;
            this.salario = salario;
        }
    }

    private boolean validateParamsRequired(Long idTime, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        final String MESSAGE_DEFAULT = "Todos os dados do jogador são obrigatórios!";
        if (idTime == null ||  idTime <= 0 || dataNascimento == null || nivelHabilidade == null || nivelHabilidade < 0 || salario == null)
            throw new  NullPointerException("Algum valor passado está inválido. ".concat(MESSAGE_DEFAULT));
        return true;
    }

    public Boolean getCaptain() {
        return isCaptain;
    }

    public void setCaptain(Boolean captain) {
        isCaptain = captain;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
