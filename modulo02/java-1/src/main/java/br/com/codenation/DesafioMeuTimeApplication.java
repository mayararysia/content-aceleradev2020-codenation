package br.com.codenation;

import br.com.codenation.exceptions.*;
import br.com.codenation.models.Jogador;
import br.com.codenation.models.Time;
import br.com.codenation.repositories.JogadorRepository;
import br.com.codenation.repositories.TimeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
    private TimeRepository timeRepository;
    private JogadorRepository jogadorRepository;

    public DesafioMeuTimeApplication() {
        this.timeRepository = new TimeRepository();
        this.jogadorRepository = new JogadorRepository();
    }

    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.timeRepository.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        if (!this.timeRepository.checkIdentifierExists(idTime))
            throw new TimeNaoEncontradoException();

        this.jogadorRepository.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
        this.timeRepository.getTime().setListFootballer(this.jogadorRepository.findAll());
    }

    public void definirCapitao(Long idJogador) {
        this.timeRepository.checkIfExistsFootballerAndTeam();
        List<Jogador> footballers = this.timeRepository
                .getTime()
                .getListFootballer();

        footballers.get(footballers.indexOf(this.jogadorRepository.find(idJogador)))
                .setCaptain(true);

        for (Jogador footballer : footballers)
            if (footballer.getCaptain() && footballer.getId() != idJogador)
                footballers.get(footballers.indexOf(footballer)).setCaptain(false);

        this.timeRepository.getTime().setListFootballer(footballers);
    }

    public Long buscarCapitaoDoTime(Long idTime) {
        this.timeRepository.setTime(this.timeRepository.find(idTime));
        boolean foundCaptain = false;
        Long idFootballer = 0l;
        List<Jogador> footballers = this.timeRepository
                .getTime()
                .getListFootballer();

        for (Jogador footballer : footballers)
            if (footballer.getCaptain().equals(true)) {
                foundCaptain = true;
                idFootballer = footballer.getId();
                break;
            }
        if (!foundCaptain)
            throw new CapitaoNaoInformadoException();
        return idFootballer;
    }

    public String buscarNomeJogador(Long idJogador) {
        this.timeRepository.checkIfExistsFootballerAndTeam();
        this.jogadorRepository.setListJogador(this.timeRepository
                .getTime()
                .getListFootballer());

        Jogador footballer = this.jogadorRepository.find(idJogador);

        if (footballer.getNome() == null)
            throw new JogadorNaoEncontradoException();
        return footballer.getNome();
    }

    public String buscarNomeTime(Long idTime) {
        this.timeRepository.setTime(this.timeRepository.find(idTime));
        return this.timeRepository.getTime().getNome();
    }

    public List<Long> buscarJogadoresDoTime(Long idTime) {
        this.timeRepository.setTime(this.timeRepository.find(idTime));
        this.jogadorRepository.setListJogador(this.timeRepository
                .getTime()
                .getListFootballer());
        return this.jogadorRepository.orderedIdentifiers();
    }

    public Long buscarMelhorJogadorDoTime(Long idTime) {
        this.timeRepository.setTime(this.timeRepository.find(idTime));
        this.jogadorRepository.setListJogador(this.timeRepository.getTime().getListFootballer());

        List<Integer> listSkillsLevel = this.jogadorRepository.findAllSkillsLevel();
        return this.timeRepository.getTime().getListFootballer()
                .get(listSkillsLevel.indexOf(Collections.max(listSkillsLevel))).getId();
    }

    public Long buscarJogadorMaisVelho(Long idTime) {
        this.timeRepository.setTime(this.timeRepository.find(idTime));
        this.jogadorRepository.setListJogador(this.timeRepository
                .getTime()
                .getListFootballer());
        return this.jogadorRepository.findOlderFootballer();
    }

    public List<Long> buscarTimes() {
        return this.timeRepository.orderedIdentifiers();
    }

    public Long buscarJogadorMaiorSalario(Long idTime) {
        this.timeRepository.setTime(this.timeRepository.find(idTime));
        this.jogadorRepository.setListJogador(this.timeRepository
                .getTime()
                .getListFootballer());
        return this.jogadorRepository.findFootballerWithBigSalary();
    }

    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        this.timeRepository.checkIfExistsFootballerAndTeam();
        this.jogadorRepository.setListJogador(this.timeRepository
                .getTime()
                .getListFootballer());
        Jogador footballer = this.jogadorRepository.find(idJogador);
        return footballer.getSalario();
    }

    public List<Long> buscarTopJogadores(Integer top) {
        if (this.timeRepository.getTime().getId() != null
                && this.timeRepository.getTime().getListFootballer().size() > 0 && top > 0) {
            this.jogadorRepository.setListJogador(this.timeRepository
                    .getTime()
                    .getListFootballer());
            return this.jogadorRepository.findTopFootballers(top);
        }
        return new ArrayList<>();
    }

}
