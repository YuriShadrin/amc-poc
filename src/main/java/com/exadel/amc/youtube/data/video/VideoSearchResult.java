package com.exadel.amc.youtube.data.video;

import java.util.List;

import com.exadel.amc.youtube.data.AbstractSearchResult;

public class VideoSearchResult extends AbstractSearchResult<VideoItem> {

	List<VideoItem>items;
	
	@Override
	public List<VideoItem> getItems() {
		return items;
	}

	public void setItems(List<VideoItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "VideoSearchResult [kind=" + getKind() + ", etag=" + getEtag() + ", pageInfo="
				+ getPageInfo() + ", items=" + getItems() + "]";
	}
	
}
