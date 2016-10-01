package com.tamu.hackday.container;

import java.io.IOException;

import com.tamu.hackday.core.db.api.DatabaseManager;
import com.tamu.hackday.core.db.api.LoginDbManager;
import com.tamu.hackday.core.service.LoginService;

import io.dropwizard.lifecycle.Managed;

public class LoginServiceManager implements Managed {

	public LoginServiceManager() throws IOException {
	}
	
	public void start() throws Exception {
		LoginService.getInstance().initialize(new LoginDbManager());
		DatabaseManager.getInstance().initialize();
	}

	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
