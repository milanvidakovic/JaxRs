package com.minja.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.minja.beans.NaseljenoMesto;
import com.minja.beans.Student;
import com.minja.interceptor.annotations.JwtSecurity;

@Path("/student")
public class StudentService {

	private static Map<Long, Student> students = new HashMap<Long, Student>();

	static {
		students.put(1L, new Student(1L, "Pera", "Perić", new NaseljenoMesto(1L, "Novi Sad")));
	}

	@JwtSecurity
	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Student> getStudenti(@Context HttpServletRequest request) {
		return students.values();
	}

	@JwtSecurity(role = "ROLE_ADMIN")
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student update(Student fromRequest, @Context HttpServletRequest request) {
		Student fromDatabase = students.get(fromRequest.getId());
		if (fromDatabase != null) {
			students.replace(fromDatabase.getId(), fromDatabase, fromRequest);
			return fromRequest;
		} else
			throw new RuntimeException("Unknown student with ID: " + fromRequest.getId());
	}

	@JwtSecurity(role = "ROLE_ADMIN")
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student insert(Student s, @Context HttpServletRequest request) {
		if (students.containsKey(s.getId()))
			throw new RuntimeException("Student with this ID already exists");
		students.put(s.getId(), s);
		return s;
	}

	@JwtSecurity(role = "ROLE_ADMIN")
	@DELETE
	@Path("/delete/{id}")
	public boolean delete(@PathParam("id") long id, @Context HttpServletRequest request) {
		if (students.remove(id) == null)
			return false;
		return true;
	}

}
