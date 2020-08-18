package br.com.codenation.repositories;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.models.Jogador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JogadorRepository {
    private List<Jogador> listJogador;
    private List<Long> listIdentifiers;
    private List<Integer> listSkillsLevel;

    public JogadorRepository() {
        this.listJogador = new ArrayList<>();
        this.listIdentifiers = new ArrayList<>();
        this.listSkillsLevel = new ArrayList<>();
    }

    public void add(Jogador jogador) {
        if (checkIdentifierExists(jogador.getId()))
            throw new IdentificadorUtilizadoException();
        this.listJogador.add(jogador);
    }

    public Jogador find(Long id) {
        if (id == null || id <= 0)
            throw new JogadorNaoEncontradoException();

        Jogador jogador = new Jogador();

        if (checkIdentifierExists(id))
            jogador = this.listJogador.get(this.listIdentifiers.indexOf(id));

        if (jogador.getId() == null)
            throw new JogadorNaoEncontradoException();

        return jogador;
    }

    public List<Jogador> findAll() {
        return this.listJogador;
    }

    public List<Integer> findAllSkillsLevel() {
        this.listSkillsLevel = new ArrayList<>();
        if (this.listJogador.size() > 0) {
            this.listJogador.stream()
                    .forEach(footballer -> this.listSkillsLevel.add(footballer.getNivelHabilidade()));
        }
        return this.listSkillsLevel;
    }

    public List<Integer> findAllAges() {
        List<Integer> listAges = new ArrayList<>();
        if (this.listJogador.size() > 0) {
            this.listJogador.stream()
                    .forEach(footballer ->
                            listAges.add(LocalDate.now().getYear() - footballer.getDataNascimento().getYear()));
        }
        return listAges;
    }

    public List<BigDecimal> findAllSalaries() {
        List<BigDecimal> listSalaries = new ArrayList<>();
        if (this.listJogador.size() > 0) {
            this.listJogador.stream()
                    .forEach(footballer -> listSalaries.add(footballer.getSalario()));
        }
        return listSalaries;
    }

    public List<Long> findTopFootballers(Integer top) {
        Integer bigger;
        Long identifier;
        List<Integer> skills;
        List<Integer> indexes = new ArrayList<>();
        List<Long> identifiers = new ArrayList<>();
        List<Jogador> footballers = this.listJogador;

        int size = identifiers.size(), sizeFootballers = footballers.size();

        if (sizeFootballers > 0) {
            skills = findAllSkillsLevel();
            while (size < top && sizeFootballers > 0) {
                bigger = Collections.max(skills);

                getIndexListWithEqualValue(indexes, skills, bigger);
                identifier = footballers.get(skills.indexOf(bigger)).getId();

                if (indexes.size() > 1)
                    identifier = getFootballerIdentifierTiebreaker(indexes, footballers, identifier);
                else
                    footballers.remove(skills.indexOf(bigger));

                identifiers.add(identifier);
                size = identifiers.size();
                sizeFootballers = footballers.size();
                this.listJogador = footballers;
                skills = findAllSkillsLevel();
                indexes = new ArrayList<>();
            }
        }
        return identifiers;
    }

    public Long findFootballerWithBigSalary() {
        List<Integer> indexes = new ArrayList<>();
        List<BigDecimal> listSalaries = findAllSalaries();
        BigDecimal bigger = Collections.max(listSalaries);
        getIndexListWithEqualValue(indexes, listSalaries, bigger);
        return getFootballerIdentifierTiebreakerWithIndexOfTheBigger(indexes, this.listJogador, listSalaries.indexOf(bigger));
    }

    public Long findOlderFootballer() {
        List<Integer> indexes = new ArrayList<>();
        List<Integer> listAges = findAllAges();
        Integer bigger = Collections.max(listAges);
        getIndexListWithEqualValue(indexes, listAges, bigger);
        return getFootballerIdentifierTiebreakerWithIndexOfTheBigger(indexes, this.listJogador, listAges.indexOf(bigger));
    }

    private void getIndexListWithEqualValue(List<Integer> indexes, List<? extends Object> list, Object bigger) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i).equals(bigger))
                indexes.add(i);
            i++;
        }
    }

    private Long getFootballerIdentifierTiebreaker(List<Integer> indexes, List<Jogador> footballers, Long identifier) {
        int j = 0;
        boolean updateIdentifier = true, removeItems = false;

        while (j < indexes.size()) {
            if (footballers.get(j).getId() < identifier && updateIdentifier)
                identifier = footballers.get(j).getId();
            j++;

            if (j == indexes.size() && !removeItems) {
                updateIdentifier = false;
                removeItems = true;
                j = 0;
            }

            if (removeItems && j < indexes.size())
                footballers.remove(j);
        }
        return identifier;
    }

    private Long getFootballerIdentifierTiebreakerWithIndexOfTheBigger(List<Integer> indexes, List<Jogador> footballers, int indexBigger) {
        Long identifier = footballers.get(indexBigger).getId();
        if (indexes.size() > 0) {
            for (Integer index : indexes)
                if (footballers.get(index).getId() < identifier)
                    identifier = footballers.get(index).getId();
        }
        return identifier;
    }

    private void fillIdentifiers() {
        this.listIdentifiers = new ArrayList<>();
        if (this.listJogador.size() > 0) {
            this.listJogador.stream()
                    .forEach(jogador -> this.listIdentifiers.add(jogador.getId()));
        }
    }

    private boolean checkIdentifierExists(Long id) {
        fillIdentifiers();
        return this.listIdentifiers.contains(id);
    }

    public List<Long> orderedIdentifiers() {
        fillIdentifiers();
        if (this.listJogador.size() > 0) Collections.sort(this.listIdentifiers);
        return this.listIdentifiers;
    }

    public void setListJogador(List<Jogador> listJogador) {
        this.listJogador = listJogador;
    }
}
