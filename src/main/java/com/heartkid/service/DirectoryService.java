package com.heartkid.service;

import org.springframework.stereotype.Service;

@Service
public class DirectoryService {
	
	public boolean con(String username, String password){
		
        String myname = "heartkiduser";
		String mypass = "heartkidpass";
		Boolean loginvalue = false;
	
		if (username.equalsIgnoreCase(myname) && password.equalsIgnoreCase(mypass))
			loginvalue = true;
		else
			loginvalue = false;
		return loginvalue;
		
}

}
