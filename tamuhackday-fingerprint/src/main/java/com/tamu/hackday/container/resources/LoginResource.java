package com.tamu.hackday.container.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tamu.hackday.container.exception.LoginException;
import com.tamu.hackday.core.entities.AccountCreateRequest;
import com.tamu.hackday.core.entities.AccountLoginRequest;
import com.tamu.hackday.core.entities.LoginResponse;
import com.tamu.hackday.core.service.LoginService;

@Path ("/login/v0.1/user")
@Produces (MediaType.APPLICATION_JSON)
public class LoginResource {

	public static final LoginService loginService = LoginService.getInstance();
	
	@GET
	@Path("/")
	public String hello() {
		return "hello";
	}
	
	@POST
	@Path("/")
	public void createAccount(AccountCreateRequest accountCreateRequest) throws ClassNotFoundException, SQLException {
		loginService.createAccount(accountCreateRequest);
	}
	
	@POST
	@Path("/authenticate/uin")
	public LoginResponse authenticateByUin(AccountLoginRequest request) throws LoginException, ClassNotFoundException, SQLException {
		return loginService.authenticateByUin(request);
	}
	
	@POST
    @Path("/authenticate/token/{tokenId}")
    public LoginResponse authenticateByToken(@PathParam("tokenId") String tokenId) throws ClassNotFoundException, SQLException, LoginException {
        return loginService.authenticateByToken(tokenId);
    }
}