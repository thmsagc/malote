package com.es.maloteapi.exception;

import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;

public class AlertException extends AbstractThrowableProblem {
    @Getter
    private final ProblemKey problemKey;

    @Getter
    private Object[] messageArgs;

    public AlertException(StatusType status, ProblemKey problemKey) {
        super(null, null, status);
        this.problemKey = problemKey;
    }

    public AlertException(StatusType status, ProblemKey problemKey, Object... messageArgs) {
        super(null, null, status);
        this.problemKey = problemKey;
        this.messageArgs = messageArgs;
    }
}
