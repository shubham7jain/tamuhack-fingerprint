package com.tamu.hackday.container.exceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.tamu.hackday.container.exception.ExceptionCodes;
import com.tamu.hackday.container.exception.LoginException;

public class LoginExceptionMapper implements ExceptionMapper<LoginException> {

	@Override
	public Response toResponse(LoginException e) {
		if (e.getExceptionCode().equals(ExceptionCodes.ITEM_NOT_FOUND))
			return Response.status(Response.Status.NOT_FOUND).entity(e.toJson()).build();

		return Response.status(Response.Status.BAD_REQUEST).entity(e.toJson()).build();
	}
}
