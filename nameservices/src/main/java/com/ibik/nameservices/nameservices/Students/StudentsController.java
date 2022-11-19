package com.ibik.nameservices.nameservices.Students;

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
import com.ibik.nameservices.nameservices.dto.searchData;

@RestController
@RequestMapping("/api/Students")
public class StudentsController {

    @Autowired
    private StudentsServices StudentsServices;

    @PostMapping
    // public Students postStudents(@RequestBody Students Students, Errors errors) {
    public ResponseEntity<ResponseData<Students>> postStudent(@Valid @RequestBody Students students, Errors errors) {

        ResponseData<Students> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                // System.out.println(error.getDefaultMessage());
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            // throw new RuntimeException("Validation Error");
        }
        responseData.setResult(true);
        List<Students> value = new ArrayList<>();
        value.add(StudentsServices.save(students));
        responseData.setData(value);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    // public Iterable<Students> fetchProgram() {
    public ResponseEntity<ResponseData<Students>> fetchStudents() {
        ResponseData<Students> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Students> value = (List<Students>) StudentsServices.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    // return StudentsServices.findAll();

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Students>> fetchStudentsById(@PathVariable("id") int id) {
        ResponseData<Students> responseData = new ResponseData<>();

        try {
            responseData.setResult(true);
            List<Students> value = new ArrayList<>();
            value.add(StudentsServices.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    // public Students fetchProgramsById(@PathVariable("id") int id) {
    // return StudentsServices.findOne(id);
    // }

    @PutMapping
    public ResponseEntity<ResponseData<Students>> updateStudent(@Valid @RequestBody Students students, Errors errors) {
        ResponseData<Students> responseData = new ResponseData<>();
        if (students.getId() != 0) {
            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }

                responseData.setResult(false);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            responseData.setResult(true);
            List<Students> value = new ArrayList<>();
            value.add(StudentsServices.save(students));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.getMessage().add("Id is Required");
            responseData.setResult(false);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    // public Students updatePrograms(@RequestBody Students Students) {
    // return StudentsServices.save(Students);

    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteStudentsById(@PathVariable("id") int id) {
        ResponseData<Void> responseData = new ResponseData<>();
        try {
            StudentsServices.removeOne(id);
            responseData.setResult(true);
            responseData.getMessage().add("Successfull remove");

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

    }

    @PostMapping("/search")
    public ResponseEntity<ResponseData<Students>> getStudentByName(@RequestBody searchData searchData) {
        ResponseData<Students> responseData = new ResponseData<>();

        try {
            Iterable<Students> values = StudentsServices.findByName(searchData.getSearchKey());
            responseData.setResult(true);
            responseData.getMessage();
            responseData.setData(values);
            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.getMessage().add(ex.getMessage());
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    // public void deleteProgramsById(@PathVariable("id") int id) {
    // StudentsServices.removeOne(id);
    // }
}
