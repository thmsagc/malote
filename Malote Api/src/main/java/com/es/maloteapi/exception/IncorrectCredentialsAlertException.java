package com.es.maloteapi.exception;

import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class IncorrectCredentialsAlertException extends AlertException {

    private static final StatusType STATUS = Status.FORBIDDEN;

    public IncorrectCredentialsAlertException(ProblemKey problemKey) {
        super(STATUS, problemKey);
    }

    public IncorrectCredentialsAlertException(ProblemKey problemKey, Object... messageArgs) {
        super(STATUS, problemKey, messageArgs);
    }
}
