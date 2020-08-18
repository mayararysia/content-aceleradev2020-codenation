package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<Challenge> create(@Valid @RequestBody Challenge challenge) {
        Challenge savedChallenge = this.challengeService.save(challenge);
        return Optional.ofNullable(savedChallenge).isPresent() ?
                new ResponseEntity<Challenge>(savedChallenge, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    ResponseEntity<Challenge> update(@Valid @RequestBody Challenge challenge) {
        Challenge updatedChallenge = this.challengeService.save(challenge);
        return Optional.ofNullable(updatedChallenge).isPresent() ?
                new ResponseEntity<Challenge>(updatedChallenge, HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> findByAccelerationIdAndUserId(@RequestParam(name = "accelerationId") Long accelerationId,
                                                                         @RequestParam(name = "userId") Long userId) {
        List<Challenge> list = Optional.ofNullable(accelerationId)
                .isPresent() && Optional.ofNullable(userId).isPresent() ?
                this.challengeService.findByAccelerationIdAndUserId(accelerationId, userId) : new ArrayList<>();
        return new ResponseEntity<>(list, list.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
