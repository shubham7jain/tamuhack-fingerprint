package com.tamu.hackday.container;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.tamu.hackday.container.resources.LoginResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class LoginApplication extends Application<LoginConfiguration> {

	@Override
	public void run(LoginConfiguration configuration, Environment environment) throws Exception {
		environment.lifecycle().manage(new LoginServiceManager());

		/* Adding Resources */
		environment.jersey().register(new LoginResource());

		environment.getObjectMapper().setPropertyNamingStrategy(
		        PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	}

	@Override
	public void initialize(Bootstrap<LoginConfiguration> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws Exception {

		LoginApplication application = new LoginApplication();
		application.run(args);
	}
}
