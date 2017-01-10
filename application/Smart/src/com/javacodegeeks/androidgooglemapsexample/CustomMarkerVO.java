package com.javacodegeeks.androidgooglemapsexample;

public class CustomMarkerVO {

	private String id;
	private Double latitude;
	private Double longitude;

	public CustomMarkerVO(String id, Double latitude, Double longitude) {

		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public CustomMarkerVO() {
		this.id = "";
		this.latitude = 0.0;
		this.longitude = 0.0;
	}

	public String getCustomMarkerId() {
		return id;
	}

	public void setCustomMarkerId(String id) {
		this.id = id;
	}

	public Double getCustomMarkerLatitude() {
		return latitude;
	}

	public void setCustomMarkerLatitude(Double mLatitude) {
		this.latitude = mLatitude;
	}

	public Double getCustomMarkerLongitude() {
		return longitude;
	}

	public void setCustomMarkerLongitude(Double mLongitude) {
		this.longitude = mLongitude;
	}
}
