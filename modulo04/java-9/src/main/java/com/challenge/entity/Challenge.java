package com.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @Column
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    @Column
    private String slug;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "challenge")
    private List<Acceleration> accelerations;

    @OneToMany(mappedBy = "submissionIdentity.challenge")
    private List<Submission> submissions;
}
