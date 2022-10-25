package com.ibik.nameservices.nameservices.Students;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;

import com.ibik.nameservices.nameservices.courses.Courses;
import com.ibik.nameservices.nameservices.programs.Programs;
import com.ibik.nameservices.nameservices.programsStudy.programStudy;

@Entity
@Table(name = "Students")
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 15)
    @NotEmpty(message = "NPM is Required")
    private String npm;

    @Column(length = 10)
    @NotEmpty(message = " Firstname is Required")
    private String firstname;

    @Column(length = 10)
    @NotEmpty(message = " Middlename is Required")
    private String middlename;

    @Column(length = 10)
    @NotEmpty(message = " Lastname is Required")
    private String lastname;

    // @Min(value = 1, message = "Program is Required")
    // private int program_id;

    // @Min(value = 1, message = "Program is Required")
    // private int department_id;
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Programs programs;

    // @Min(value = 1, message = "Program is Required")
    // private int department_id;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private programStudy programStudy;

    @ManyToMany
    @JoinTable(name = "student_rel_courses", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Courses> courses;

    public Students() {

    }

    public Students(int id, String npm, String firstname, String middlename, String lastname, Programs programs) {
        this.id = id;
        this.npm = npm;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.programs = programs;
        this.programStudy = programStudy;
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

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Programs getPrograms() {
        return programs;
    }

    public void setPrograms(Programs programs) {
        this.programs = programs;
    }

    public programStudy getProgramStudy() {
        return programStudy;
    }

    public void setProgramStudy(programStudy programStudy) {
        this.programStudy = programStudy;
    }

    public Set<Courses> getCourses() {
        return courses;
    }

    public void setCourses(Set<Courses> courses) {
        this.courses = courses;
    }

}
