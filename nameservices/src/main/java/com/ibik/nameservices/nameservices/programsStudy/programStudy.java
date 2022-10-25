package com.ibik.nameservices.nameservices.programsStudy;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;

import com.ibik.nameservices.nameservices.programs.Programs;

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

    // @Min(value = 1, message = "program_id is Required")
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Programs programs;

    // @Min(value = 1, message = "faculty is Required ")
    // private Integer faculty_id;
    @OneToMany
    @JoinColumn(name = "faculty_id")
    private Set<programStudy> Departments;

    // @Min(value = 1, message = "department is required")
    // private Integer department_id;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean is_active;

    public programStudy() {

    }

    public programStudy(int id, String name, String description, String code, Programs programs, boolean is_active,
            Set<programStudy> Departments) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.programs = programs;
        this.Departments = Departments;
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

    public Programs getPrograms() {
        return programs;
    }

    public void setPrograms(Programs programs) {
        this.programs = programs;
    }

    public Set<programStudy> getDepartments() {
        return Departments;
    }

    public void setDepartments(Set<programStudy> departments) {
        Departments = departments;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

}
