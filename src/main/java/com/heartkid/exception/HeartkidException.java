package com.heartkid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HeartkidException extends RuntimeException {

	    private static final long serialVersionUID = 2930512070636071126L;

	    public HeartkidException(final String message) {
	        super(message);
	    }
		
	    public HeartkidException(final String message, final Throwable rootCause) {
	        super(message, rootCause);
	    }
	}
