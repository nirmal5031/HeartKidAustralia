package com.heartkid.util;

import com.heartkid.exception.HeartkidBadRequestException;

public class UserValidation {
	
	    private UserValidation() {
	    }

	    public static void validateUser(String userId) {
	        try {
	            Integer.parseInt(userId);
	        } catch (NumberFormatException e) {
	         
	            throw new HeartkidBadRequestException(
	                    Constants.LOGIN_INVALID_CREDENTIALS);
	        }
	    }
	}

