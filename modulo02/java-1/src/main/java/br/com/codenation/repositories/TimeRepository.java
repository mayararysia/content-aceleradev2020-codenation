package br.com.codenation.repositories;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TimeRepository {
    private List<Time> listTime;
    private List<Long> listIdentifiers;
    private Time time;

    public TimeRepository() {
        this.listTime = new ArrayList<>();
        this.listIdentifiers = new ArrayList<>();
        this.time = new Time();
    }

    public void add(Time time) {
        if (checkIdentifierExists(time.getId()))
            throw new IdentificadorUtilizadoException();
        this.time = time;
        this.listTime.add(this.time);
    }

    public Time find(Long id) {
        if (id == null || id <= 0)
            throw new TimeNaoEncontradoException();

        Time time = new Time();

        if (checkIdentifierExists(id))
            time = this.listTime.get(this.listIdentifiers.indexOf(id));

        if (time.getNome() == null)
            throw new TimeNaoEncontradoException();

        return time;
    }

    private void fillIdentifiers() {
        this.listIdentifiers = new ArrayList<>();
        if (this.listTime.size() > 0) {
            this.listTime.stream()
                    .forEach(time -> this.listIdentifiers.add(time.getId()));
        }
    }

    public boolean checkIdentifierExists(Long id) {
        fillIdentifiers();
        return this.listIdentifiers.contains(id);
    }

    public List<Long> orderedIdentifiers() {
        fillIdentifiers();
        if (this.listTime.size() > 0)
            Collections.sort(this.listIdentifiers);
        return this.listIdentifiers;
    }

    public void checkIfExistsFootballerAndTeam() {
        if (this.time == null || this.time.getId() == null
                || this.time.getListFootballer() == null || this.time.getListFootballer().size() == 0)
            throw new JogadorNaoEncontradoException();
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
