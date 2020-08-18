package com.challenge.service;

import com.challenge.entity.Company;
import com.challenge.repository.CompanyRepository;
import com.challenge.service.interfaces.CompanyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements CompanyServiceInterface {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Optional<Company> findById(Long id) {
        return this.companyRepository.findById(id);
    }

    @Override
    public List<Company> findByUserId(Long userId) {
        return this.companyRepository.findByCandidatesIdUserId(userId);
    }

    @Override
    public List<Company> findByAccelerationId(Long accelerationId) {
        return this.companyRepository.findDistinctByCandidatesIdAccelerationId(accelerationId);
    }

    @Override
    public Company save(Company company) {
        return this.companyRepository.save(company);
    }
}
