package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.entity.Acceleration;
import com.challenge.entity.Submission;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionMapper submissionMapper;


    @PostMapping
    public ResponseEntity<SubmissionDTO> create(@Valid @RequestBody Submission submission) {
        SubmissionDTO savedSubmission = convertEntityToDTO(this.submissionService.save(submission));
        return Optional.ofNullable(savedSubmission).isPresent() ?
                new ResponseEntity<SubmissionDTO>(savedSubmission, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<SubmissionDTO> update(@Valid @RequestBody Submission submission) {
        SubmissionDTO updatedSubmission = convertEntityToDTO(this.submissionService.save(submission));
        return Optional.ofNullable(updatedSubmission).isPresent() ?
                new ResponseEntity<SubmissionDTO>(updatedSubmission, HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> findSubmissionsByChallengeAndByAcceleration(
            @RequestParam(name = "challengeId") Long challengeId,
            @RequestParam(name = "accelerationId")  Long accelerationId) {
        List<SubmissionDTO> listDTO = convertListEntityToListDTO(Optional.ofNullable(accelerationId).isPresent() ?
                this.submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId) : new ArrayList<>());

        return new ResponseEntity<>(listDTO, listDTO.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    private List<SubmissionDTO> convertListEntityToListDTO(List<Submission> submissions) {
        return  Optional.ofNullable(submissions).isPresent() ? this.submissionMapper.map(submissions) :  new ArrayList<>();
    }

    private SubmissionDTO convertEntityToDTO(Submission candidate) {
        return  Optional.ofNullable(candidate).isPresent() ? this.submissionMapper.map(candidate) :  new SubmissionDTO();
    }
}
