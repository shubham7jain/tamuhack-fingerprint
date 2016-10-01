package com.tamu.hackday.container.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path ("/login/v0.1")
@Produces (MediaType.APPLICATION_JSON)
public class LoginResource {

	@GET
	@Path("/")
	public String hello() {
		return "hello";
	}
}
