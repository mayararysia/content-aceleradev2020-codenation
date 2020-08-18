package com.challenge.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Acceleration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(max=100)
    @Column
    private String name;

    @NotNull
    @NotEmpty
    @Size(max=50)
    @Column(nullable = false)
    private String slug;

    @NotNull
    @CreatedDate
    @Column(name="created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @OneToMany(mappedBy = "candidateIdentity.acceleration")
    private List<Candidate> candidates;
}
