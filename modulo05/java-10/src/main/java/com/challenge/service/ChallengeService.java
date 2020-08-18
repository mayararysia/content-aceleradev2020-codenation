package com.challenge.service;

import com.challenge.entity.Challenge;
import com.challenge.repository.ChallengeRepository;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService implements ChallengeServiceInterface{
    @Autowired
    private ChallengeRepository challengeRepository;

    @Override
    public List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId) {
        return this.challengeRepository.findAllByAccelerationsIdAndSubmissionsIdUserId(accelerationId, userId);
    }

    @Override
    public Challenge save(Challenge challenge) {
        return this.challengeRepository.save(challenge);
    }
}
