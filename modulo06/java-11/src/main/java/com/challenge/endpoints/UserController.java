package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User savedUser = this.userService.save(user);
        return Optional.ofNullable(savedUser).isPresent() ?
                new ResponseEntity<User>(savedUser, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        User updatedUser = this.userService.save(user);
        return Optional.ofNullable(updatedUser).isPresent() ?
                new ResponseEntity<User>(updatedUser, HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        Optional<User> optionalUser = this.userService.findById(id);
        return optionalUser.isPresent() ?
                new ResponseEntity<User>(optionalUser.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<User>> findByAccelerationNameOrByCompanyId(
            @RequestParam(required = false, name = "accelerationName") String name,
            @RequestParam(required = false, name = "companyId") Long companyId) {
        List<User> list = Optional.ofNullable(name).isPresent() ?
                this.userService.findByAccelerationName(name) :
                (Optional.ofNullable(companyId).isPresent() ? this.userService.findByCompanyId(companyId) : new ArrayList<>());
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

}
