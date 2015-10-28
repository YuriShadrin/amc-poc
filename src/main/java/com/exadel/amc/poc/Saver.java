package com.exadel.amc.poc;

public interface Saver<D extends MetricsData> {

	void saveMetricsData(String id, D data);
	
}
