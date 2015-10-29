package com.exadel.amc.wikipedia.data;

import java.util.Date;

public class DailyView {

	private Date date;
	private Integer viewsCount;

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getViewsCount() {
		return viewsCount;
	}
	public void setViewsCount(Integer viewsCount) {
		this.viewsCount = viewsCount;
	}

	@Override
	public String toString() {
		return "DailyView [date=" + date + ", viewsCount=" + viewsCount + "]";
	}
}
