package com.es.maloteapi.exception;

import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class NotFoundAlertException extends AlertException {

    private static final StatusType STATUS = Status.NOT_FOUND;

    public NotFoundAlertException(ProblemKey problemKey) {
        super(STATUS, problemKey);
    }

    public NotFoundAlertException(ProblemKey problemKey, Object... messageArgs) {
        super(STATUS, problemKey, messageArgs);
    }
}
