package com.ibik.nameservices.nameservices.programsStudy;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class programStudyServices {

    @Autowired
    private programStudyRepo programStudyRepo;

    public programStudy save(programStudy programStudy) {
        return programStudyRepo.save(programStudy);

    }

    public programStudy update(programStudy programStudy) {
        programStudy result = findOne(programStudy.getId());

        result.setName(programStudy.getName());
        result.setDescription(programStudy.getDescription());
        result.setCode(programStudy.getCode());
        result.setProgram_id(programStudy.getProgram_id());
        result.setFaculty_id(programStudy.getFaculty_id());
        result.setDepartment_id(programStudy.getDepartment_id());
        result.setIs_active(programStudy.isIs_active());

        return result;

    }

    public programStudy findOne(int id) {
        return programStudyRepo.findById(id).get();

    }

    public Iterable<programStudy> findAll() {
        return programStudyRepo.findAll();

    }

    public void removeOne(int id) {
        programStudyRepo.deleteById(id);
    }

}
