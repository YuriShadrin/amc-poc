package com.exadel.amc.wikipedia.data;

import java.util.List;

public class ArticleStatistics {

	private String project;
	private String month;
	private Integer rank;
	private String title;

	private List<DailyView> dailyViews;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<DailyView> getDailyViews() {
		return dailyViews;
	}

	public void setDailyViews(List<DailyView> dailyViews) {
		this.dailyViews = dailyViews;
	}

	@Override
	public String toString() {
		return "ArticleStatistics [title=" + title + ", project=" + project + ", month=" + month + ", rank=" + rank
				+ ", dailyViews=" + dailyViews + "]";
	}
}
