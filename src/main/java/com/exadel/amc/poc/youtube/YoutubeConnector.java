package com.exadel.amc.poc.youtube;

import com.exadel.amc.poc.Connector;
import com.exadel.amc.poc.TaskConfiguration;

public class YoutubeConnector implements Connector<YoutubeMetricsData> {

	public YoutubeConnector(TaskConfiguration config) {
	}

	@Override
	public YoutubeMetricsData getMetricsData(String id) {
		return null;
	}

}
