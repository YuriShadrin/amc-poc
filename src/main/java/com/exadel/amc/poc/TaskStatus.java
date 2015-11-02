package com.exadel.amc.poc;

public interface TaskStatus {

    enum State {
        running, completed, completed_with_error
    }

    public State getState();

    public long getStartTime();

    public long getEndTime();

    public int getSpentLimits();

    public TaskException getException();

}
