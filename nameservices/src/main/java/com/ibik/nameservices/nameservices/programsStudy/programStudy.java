package com.ibik.nameservices.nameservices.programsStudy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "programStudy")
public class programStudy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    @NotEmpty(message = "Name is Required")
    private String name;

    @Column(length = 250)
    @NotEmpty(message = "description is Required")
    private String description;

    @Column(length = 5)
    @NotEmpty(message = "code is Required")
    private String code;

    @Min(value = 1, message = "program_id is Required")
    private Integer program_id;

    @Min(value = 1, message = "faculty is Required ")
    private Integer faculty_id;

    @Min(value = 1, message = "department is required")
    private Integer department_id;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean is_active;

    public programStudy() {

    }

    public programStudy(int id, String name, String description, String code, Integer program_id,
            Integer faculty_id, Integer department_id, boolean is_active) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.program_id = program_id;
        this.faculty_id = faculty_id;
        this.department_id = department_id;
        this.is_active = is_active;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getProgram_id() {
        return program_id;
    }

    public void setProgram_id(Integer program_id) {
        this.program_id = program_id;
    }

    public Integer getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(Integer faculty_id) {
        this.faculty_id = faculty_id;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

}
