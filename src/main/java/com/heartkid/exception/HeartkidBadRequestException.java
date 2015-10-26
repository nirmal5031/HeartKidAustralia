package com.heartkid.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HeartkidBadRequestException extends RuntimeException {
    private static final long serialVersionUID = -229518320737989810L;

    
    public HeartkidBadRequestException(final String message) {
        super(message);
    }

    public HeartkidBadRequestException(final String message,
            final Throwable rootCause) {
        super(message, rootCause);
    }
}
