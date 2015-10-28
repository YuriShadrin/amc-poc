package com.exadel.amc.youtube.data.video;

import com.exadel.amc.youtube.data.AbstractStatus;

public class VideoStatus extends AbstractStatus {

	private String uploadStatus;
	private String license;
	private Boolean embeddable;
	private Boolean publicStatsViewable;

	public String getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public Boolean getEmbeddable() {
		return embeddable;
	}
	public void setEmbeddable(Boolean embeddable) {
		this.embeddable = embeddable;
	}
	public Boolean getPublicStatsViewable() {
		return publicStatsViewable;
	}
	public void setPublicStatsViewable(Boolean publicStatsViewable) {
		this.publicStatsViewable = publicStatsViewable;
	}
	@Override
	public String toString() {
		return "VideoStatus [privacyStatus=" + getPrivacyStatus() + ", uploadStatus=" + getUploadStatus()
				+ ", license=" + getLicense() + ", embeddable=" + getEmbeddable()
				+ ", publicStatsViewable=" + getPublicStatsViewable() + "]";
	}

}
