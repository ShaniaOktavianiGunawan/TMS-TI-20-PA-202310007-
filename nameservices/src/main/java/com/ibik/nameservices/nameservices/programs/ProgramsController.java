package com.ibik.nameservices.nameservices.programs;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibik.nameservices.nameservices.dto.ResponseData;

@RestController
@RequestMapping("/api/programs")
public class ProgramsController {

    @Autowired
    private ProgramsServices programsServices;

    @PostMapping
    public ResponseEntity<ResponseData<Programs>> postStudent(@Valid @RequestBody Programs Programs,
            Errors errors) {

        ResponseData<Programs> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {

                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

        }
        responseData.setResult(true);
        List<Programs> value = new ArrayList<>();
        value.add(programsServices.save(Programs));
        responseData.setData(value);
        return ResponseEntity.ok(responseData);
    }
    // public Programs postPrograms(@RequestBody Programs programs) {
    // return programsServices.save(programs);
    // }

    @GetMapping
    public ResponseEntity<ResponseData<Programs>> fetchPrograms() {
        ResponseData<Programs> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Programs> value = (List<Programs>) programsServices.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    // public Iterable<Programs> fetchProgram() {
    // return programsServices.findAll();
    // }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Programs>> fetchProgramsById(@PathVariable("id") int id) {
        ResponseData<Programs> responseData = new ResponseData<>();

        try {
            responseData.setResult(true);
            List<Programs> value = new ArrayList<>();
            value.add(programsServices.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    // public Programs fetchProgramsById(@PathVariable("id") int id) {
    // return programsServices.findOne(id);
    // }

    @PutMapping
    public ResponseEntity<ResponseData<Programs>> updatePrograms(@Valid @RequestBody Programs Programs,
            Errors errors) {
        ResponseData<Programs> responseData = new ResponseData<>();
        if (Programs.getId() != 0) {
            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }

                responseData.setResult(false);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            responseData.setResult(true);
            List<Programs> value = new ArrayList<>();
            value.add(programsServices.save(Programs));
            // value.add(programStudyServices.update(programStudy));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.getMessage().add("Id is Required");
            responseData.setResult(false);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    // public Programs updatePrograms(@RequestBody Programs programs) {
    // return programsServices.save(programs);

    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteProgramsById(@PathVariable("id") int id) {
        ResponseData<Void> responseData = new ResponseData<>();
        try {
            programsServices.removeOne(id);
            responseData.setResult(true);
            responseData.getMessage().add("Successfull remove");

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    // public void deleteProgramsById(@PathVariable("id") int id) {
    // programsServices.removeOne(id);
    // }

    // @GetMappingS
    // public String HellowWolrd() {
    // return "<h1>Hellow</h1>";
    // }

}
