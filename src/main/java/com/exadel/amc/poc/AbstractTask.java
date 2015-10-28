package com.exadel.amc.poc;

import com.exadel.amc.poc.TaskStatus.State;

public abstract class AbstractTask<D extends MetricsData> implements Task {

	protected TaskConfiguration config;
	
	protected AbstractTask(TaskConfiguration config) {
		this.config = config;
	}
	
	@Override
	public TaskStatus execute(String id) {
		TaskStatus status = getTaskStatus();
		
//		D data = getConnector().getMetricsData(id);
		
		if(status.getState() == State.running ) {
			
		}
		
//		getSaver().saveMetricsData(id, data);
		
		return status ;
	}

	protected abstract Connector<D> getConnector();
	
	protected abstract Saver<D> getSaver();
	
	protected abstract TaskStatus getTaskStatus();
	
}
