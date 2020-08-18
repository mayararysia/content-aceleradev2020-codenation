package com.challenge.service.impl;

import com.challenge.entity.Acceleration;
import com.challenge.repository.AccelerationRepository;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccelerationsService implements AccelerationServiceInterface {

    private AccelerationRepository accelerationRepository;

    @Override
    public Optional<Acceleration> findById(Long id) {
        return accelerationRepository.findById(id);
    }

    @Override
    public List<Acceleration> findByCompanyId(Long companyId) {
        return accelerationRepository.findByCandidatesIdCompanyId(companyId);
    }

    @Override
    public Acceleration save(Acceleration acceleration) {
        return  Optional.ofNullable(acceleration).isPresent() ? this.accelerationRepository.save(acceleration) : new Acceleration();
    }

}
