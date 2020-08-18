package com.challenge.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @Column
    private String fullName;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @Email
    @Column
    private String email;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    @Column
    private String nickname;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    @Column
    private String password;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "submissionIdentity.user")
    private List<Submission> submissions;

    @OneToMany(mappedBy = "candidateIdentity.user")
    private List<Candidate> candidates;
}
