package com.ibik.nameservices.nameservices.programsStudy;

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
@RequestMapping("/api/programsStudy")
public class programStudyController {

    @Autowired
    private programStudyServices programStudyServices;

    @PostMapping
    public ResponseEntity<ResponseData<programStudy>> postStudent(@Valid @RequestBody programStudy programStudy,
            Errors errors) {

        ResponseData<programStudy> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {

                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

        }
        responseData.setResult(true);
        List<programStudy> value = new ArrayList<>();
        value.add(programStudyServices.save(programStudy));
        responseData.setData(value);
        return ResponseEntity.ok(responseData);
    }
    // public programStudy postPrograms(@RequestBody programStudy programStudy) {
    // return programStudyServices.save(programStudy);
    // }

    @GetMapping
    public ResponseEntity<ResponseData<programStudy>> fetchProgramStudy() {
        ResponseData<programStudy> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            Iterable<programStudy> value = programStudyServices.findAll();
            for (programStudy programStudy : value) {
                System.out.println(programStudy.getName());
            }
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    // public Iterable<programStudy> fetchProgram() {
    // return programStudyServices.findAll();
    // }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<programStudy>> fetchProgramStudyById(@PathVariable("id") int id) {
        ResponseData<programStudy> responseData = new ResponseData<>();

        try {
            responseData.setResult(true);
            List<programStudy> value = new ArrayList<>();
            value.add(programStudyServices.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    // public programStudy fetchProgramsById(@PathVariable("id") int id) {
    // return programStudyServices.findOne(id);
    // }

    @PutMapping
    public ResponseEntity<ResponseData<programStudy>> updateProgramStudy(@Valid @RequestBody programStudy programStudy,
            Errors errors) {
        ResponseData<programStudy> responseData = new ResponseData<>();
        if (programStudy.getId() != 0) {
            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }

                responseData.setResult(false);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            responseData.setResult(true);
            List<programStudy> value = new ArrayList<>();
            value.add(programStudyServices.save(programStudy));
            // value.add(programStudyServices.update(programStudy));
            responseData.setData(value);
            return ResponseEntity.ok(responseData);

        } else {
            responseData.getMessage().add("Id is Required");
            responseData.setResult(false);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    // public programStudy updatePrograms(@RequestBody programStudy programStudy) {
    // return programStudyServices.save(programStudy);

    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteprogramStudyById(@PathVariable("id") int id) {
        ResponseData<Void> responseData = new ResponseData<>();
        try {
            programStudyServices.removeOne(id);
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
    // programStudyServices.removeOne(id);
    // }

}
