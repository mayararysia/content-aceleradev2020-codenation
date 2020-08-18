package com.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidate {

    @EmbeddedId
    private CandidateIdentity candidateIdentity;

    @NotNull
    @Column
    private Integer status;

    @NotNull
    @CreatedDate
    @Column(name="created_at")
    private Date createdAt;
}
