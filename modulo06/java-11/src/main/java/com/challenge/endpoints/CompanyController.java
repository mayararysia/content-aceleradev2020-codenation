package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> create(@Valid @RequestBody Company company) {
        Company savedCompany = this.companyService.save(company);
        return Optional.ofNullable(savedCompany).isPresent() ?
                new ResponseEntity<Company>(savedCompany, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<Company> update(@Valid @RequestBody Company company) {
        Company updatedCompany = this.companyService.save(company);
        return Optional.ofNullable(updatedCompany).isPresent() ?
                new ResponseEntity<Company>(updatedCompany, HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
        Optional<Company> optionalCompany = this.companyService.findById(id);
        return optionalCompany.isPresent() ?
                new ResponseEntity<Company>(optionalCompany.get(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Company>> findCompaniesByAccelerationIdByUserId(
            @RequestParam(required = false, name = "accelerationId") Long accelerationId,
            @RequestParam(required = false, name = "userId") Long userId) {
        List<Company> list = Optional.ofNullable(accelerationId).isPresent() ?
                this.companyService.findByAccelerationId(accelerationId) :
                (Optional.ofNullable(userId).isPresent() ?
                        this.companyService.findByUserId(userId) : new ArrayList<>());
        return new ResponseEntity<>(list, list.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
