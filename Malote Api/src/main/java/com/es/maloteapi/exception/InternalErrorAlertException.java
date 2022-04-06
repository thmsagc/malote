package com.es.maloteapi.exception;

import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class InternalErrorAlertException extends AlertException {

    private static final StatusType STATUS = Status.INTERNAL_SERVER_ERROR;

    public InternalErrorAlertException(ProblemKey problemKey) {
        super(STATUS, problemKey);
    }

    public InternalErrorAlertException(ProblemKey problemKey, Object... messageArgs) {
        super(STATUS, problemKey, messageArgs);
    }
}
