package com.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Submission {

    @EmbeddedId
    private SubmissionIdentity submissionIdentity;

    @NotNull
    @Column
    private Float score;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
}
