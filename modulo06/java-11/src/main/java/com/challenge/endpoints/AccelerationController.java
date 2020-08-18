package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.service.impl.AccelerationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

    @Autowired
    private AccelerationsService accelerationService;

    @PostMapping
    public ResponseEntity<Acceleration> create(@Valid @RequestBody Acceleration acceleration) {
        return new ResponseEntity<Acceleration>(this.accelerationService.save(acceleration), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Acceleration> update(@Valid @RequestBody Acceleration acceleration) {
        return new ResponseEntity<Acceleration>(this.accelerationService.save(acceleration), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acceleration> findById(@PathVariable("id") Long id) {
        Optional<Acceleration> optionalAcceleration = this.accelerationService.findById(id);
        return optionalAcceleration.isPresent() ?
                new ResponseEntity<Acceleration>(optionalAcceleration.get(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Acceleration>> findByCompanyId(@RequestParam(name = "companyId") Long companyId) {
        List<Acceleration> list = Optional.ofNullable(companyId).isPresent() ?
                this.accelerationService.findByCompanyId(companyId) : new ArrayList<>();
        return new ResponseEntity<>(list, list.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
