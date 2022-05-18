package com.accenture.gradetool.core.error;

/**
 * @author Severin Weigold
 */
public interface ExceptionLogger {

    /**
     * Logs an Exception and returns a UUID for later retrieval
     */
    String logException(Exception e);

}
