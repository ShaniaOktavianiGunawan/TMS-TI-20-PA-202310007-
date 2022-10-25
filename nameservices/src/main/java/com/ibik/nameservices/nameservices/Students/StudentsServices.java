package com.ibik.nameservices.nameservices.Students;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentsServices {

    @Autowired
    private StudentsRepo StudentsRepo;

    public Students save(Students Students) {
        return StudentsRepo.save(Students);

    }

    public Students findOne(int id) {
        return StudentsRepo.findById(id).get();

    }

    public Iterable<Students> findAll() {
        return StudentsRepo.findAll();

    }

    public void removeOne(int id) {
        StudentsRepo.deleteById(id);
    }

    // public Students update(Students students) {
    // Students result = findOne(students.getId());

    // result.setFirstname(students.getFirstname());
    // result.setMiddlename(students.getMiddlename());
    // result.setLastname(students.getLastname());
    // result.setNpm(students.getNpm());
    // result.setProgram_id(students.getProgram_id());
    // result.setDepartment_id(students.getDepartment_id());

    // return result;
    // }

    // public void setStudentsRepo(StudentsRepo studentsRepo) {
    // StudentsRepo = studentsRepo;
    // }

    // public Students update(@Valid Students students) {
    // return null;
    // }

}
