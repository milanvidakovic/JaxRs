package com.minja.services;

import java.security.Key;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.minja.beans.User;
import com.minja.interceptor.annotations.JwtKey;
import com.minja.repositories.UserRepo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Path("/user")
public class LoginService {

	/**
	 * https://github.com/jwtk/jjwt
	 */
	@JwtKey
	public static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	UserRepo userRepo = new UserRepo();

    @POST
    @Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public User login(User u) {

        User fromDatabase = userRepo.getUser(u.username);
        if (fromDatabase == null || !fromDatabase.equals(u)) {
            throw new RuntimeException("Wrong username or password!");
        }

        String jwt = Jwts.builder().setSubject(u.username).setExpiration(new Date(new Date().getTime() + 1000L*60L*60L*24L*30L)).setIssuedAt(new Date()).signWith(key).compact();
        fromDatabase.jwt = jwt;

        return fromDatabase;
    }
}