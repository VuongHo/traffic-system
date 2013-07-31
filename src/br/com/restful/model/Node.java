package br.com.restful.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class Node {
	private int id;
	private float latitude;
	private float longitude;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
}
