package com.challenge.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class CandidateIdentity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "acceleration_id")
    private Acceleration acceleration;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
