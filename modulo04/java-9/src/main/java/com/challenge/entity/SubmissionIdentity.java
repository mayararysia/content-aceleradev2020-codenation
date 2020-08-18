package com.challenge.entity;

import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class SubmissionIdentity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
}
