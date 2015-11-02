package com.exadel.amc.poc;

public abstract class AbstractTaskStatus implements TaskStatus {

    private State state;
    private long startTime, endTime;
    private int spentLimits;
    private TaskException exception = null;

    public abstract int getPlannedLimits();

    protected AbstractTaskStatus() {
        setState(State.running);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public long getEndTime() {
        return endTime;
    }

    @Override
    public int getSpentLimits() {
        return spentLimits;
    }

    @Override
    public TaskException getException() {
        return exception;
    }

    public void setState(State state) {
        this.state = state;
        switch (state) {
        case running:
            setStartTime(System.currentTimeMillis());
            break;
        case completed:
        case completed_with_error:
            setEndTime(System.currentTimeMillis());
            break;
        }

    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setSpentLimits(int spentLimits) {
        this.spentLimits = spentLimits;
    }

    public void setException(TaskException exception) {
        this.exception = exception;
    }

}
