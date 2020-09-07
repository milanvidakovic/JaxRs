package com.minja.application;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyExceptionMapper implements ExceptionMapper<RuntimeException>{

    @Override
    public Response toResponse(RuntimeException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"" + exception.getMessage() + "\"}").build();
    }

}