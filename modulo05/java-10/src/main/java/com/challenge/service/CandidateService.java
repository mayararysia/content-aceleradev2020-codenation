package com.challenge.service;

import com.challenge.entity.*;
import com.challenge.repository.CandidateRepository;
import com.challenge.service.interfaces.CandidateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements CandidateServiceInterface {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Optional<Candidate> findById(CandidateId id) {
        return this.candidateRepository.findById(id);
    }

    @Override
    public Optional<Candidate> findById(Long userId, Long companyId, Long accelerationId) {
        return this.candidateRepository.findByIdUserIdAndIdCompanyIdAndIdAccelerationId(userId, companyId, accelerationId);
    }

    @Override
    public List<Candidate> findByCompanyId(Long companyId) {
        return this.candidateRepository.findByIdCompanyId(companyId);
    }

    @Override
    public List<Candidate> findByAccelerationId(Long accelerationId) {
        return this.candidateRepository.findByIdAccelerationId(accelerationId);
    }

    @Override
    public Candidate save(Candidate candidate) {
        return this.candidateRepository.save(candidate);
    }
}