package com.exadel.amc.poc.youtube;

import com.exadel.amc.poc.AbstractTask;
import com.exadel.amc.poc.TaskConfiguration;
import com.exadel.amc.poc.TaskStatus;

public class YoutubeTask extends AbstractTask<YoutubeMetricsData> {

	private YoutubeConnector connector;
	private YoutubeSaver saver;
	
	public YoutubeTask(TaskConfiguration config) {
		super(config);
		this.connector = new YoutubeConnector(config);
		this.saver = new YoutubeSaver(config); 
	}
	
	@Override
	protected YoutubeConnector getConnector() {
		return connector;
	}

	@Override
	protected YoutubeSaver getSaver() {
		return saver;
	}

	@Override
	protected TaskStatus getTaskStatus() {
		return new YoutubeTaskStatus();
	}

}
