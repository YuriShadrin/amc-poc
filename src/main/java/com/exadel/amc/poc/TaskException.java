package com.exadel.amc.poc;

public class TaskException extends MCException {

    private static final long serialVersionUID = 1L;

    private int spentlimits;
    private TaskException exception = null;

    public TaskException getException() {
        return exception;
    }

    public boolean hasError() {
        return getException() != null;
    }

    public int getSpentlimits() {
        return spentlimits;
    }

}
