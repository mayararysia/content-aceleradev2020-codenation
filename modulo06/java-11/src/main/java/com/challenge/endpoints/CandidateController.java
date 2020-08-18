package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @PostMapping
    public ResponseEntity<CandidateDTO> create(@Valid @RequestBody Candidate candidate) {
        CandidateDTO savedCandidate = convertEntityToDTO(this.candidateService.save(candidate));
        return Optional.ofNullable(savedCandidate).isPresent() ?
                new ResponseEntity<CandidateDTO>(savedCandidate, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<CandidateDTO> update(@Valid @RequestBody Candidate candidate) {
        CandidateDTO updatedCandidate = convertEntityToDTO(this.candidateService.save(candidate));
        return Optional.ofNullable(updatedCandidate).isPresent() ?
                new ResponseEntity<CandidateDTO>(updatedCandidate, HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/{userId}/{companyId}/{accelerationId}")
    public ResponseEntity<CandidateDTO> findById(@PathVariable("userId") Long userId,
                                                 @PathVariable("companyId") Long companyId,
                                                 @PathVariable("accelerationId") Long accelerationId) {
        Optional<Candidate> optionalCandidate = this.candidateService.findById(userId, companyId, accelerationId);
        return optionalCandidate.isPresent() ?
                new ResponseEntity<CandidateDTO>(convertEntityToDTO(optionalCandidate.get()), HttpStatus.OK) :
                new ResponseEntity<CandidateDTO>(new CandidateDTO(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findCandidatesCompanyIdAccelerationId(
            @RequestParam(required = false, name = "companyId") Long companyId,
            @RequestParam(required = false, name = "accelerationId") Long accelerationId) {

        List<CandidateDTO> listDTO =
                convertListEntityToListDTO(Optional.ofNullable(companyId).isPresent() ?
                        this.candidateService.findByCompanyId(companyId)
                        : (Optional.ofNullable(accelerationId).isPresent() ?
                        this.candidateService.findByAccelerationId(accelerationId) : new ArrayList<>()));
        return new ResponseEntity<List<CandidateDTO>>(listDTO, listDTO.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    private List<CandidateDTO> convertListEntityToListDTO(List<Candidate> candidates) {
        return Optional.ofNullable(candidates).isPresent() ? this.candidateMapper.map(candidates) : new ArrayList<>();
    }

    private CandidateDTO convertEntityToDTO(Candidate candidate) {
        return Optional.ofNullable(candidate).isPresent() ? this.candidateMapper.map(candidate) : new CandidateDTO();
    }
}
