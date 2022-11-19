package com.ibik.nameservices.nameservices.Students;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepo extends CrudRepository<Students, Integer> {

    @Query("SELECT a FROM Students a WHERE a.firstname LIKE :name OR a.middlename LIKE :name OR a.lastname LIKE :name OR a.npm LIKE :name")
    public Iterable<Students> findStudentByName(@PathParam("name") String name);
}
