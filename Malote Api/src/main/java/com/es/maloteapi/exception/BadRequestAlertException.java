package com.es.maloteapi.exception;

import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class BadRequestAlertException extends AlertException {

    private static final StatusType STATUS = Status.BAD_REQUEST;

    public BadRequestAlertException(ProblemKey problemKey) {
        super(STATUS, problemKey);
    }

    public BadRequestAlertException(ProblemKey problemKey, Object... messageArgs) {
        super(STATUS, problemKey, messageArgs);
    }
}
