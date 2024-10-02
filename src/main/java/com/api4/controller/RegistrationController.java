package com.api4.controller;

import com.api4.entity.Registration;
import com.api4.payload.RegistrationDto;
import com.api4.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private RegistrationService rs;

    public RegistrationController(RegistrationService rs) {
        this.rs = rs;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistration() {
        List<RegistrationDto> dtos = rs.getRegistrations();
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> createRegistration(
          @Valid @RequestBody RegistrationDto registrationDto , BindingResult result
    ) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage() , HttpStatus.CREATED);
        }
        RegistrationDto ui = rs.createReg(registrationDto);
        return new ResponseEntity<>(ui, HttpStatus.CREATED);


    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam long id) {

        rs.deleteReg(id);
        return new ResponseEntity<>("deleted record", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity <Registration> updateRegistration(@PathVariable long id,
                                                        @RequestBody   Registration registration) {
Registration sd = rs.updateReg(id , registration);
return new ResponseEntity<>(sd , HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity <RegistrationDto> getRegistrationById(@PathVariable long id){
        RegistrationDto dto = rs.getRegById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
