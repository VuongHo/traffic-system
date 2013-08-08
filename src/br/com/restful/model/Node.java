package br.com.restful.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class Node {
	private int id;
	private double latitude;
	private double longitude;
	
	public Node(){
		this.setId(0);
		this.setLat(0);
		this.setLng(0);
	}
	public Node(int id, double latitude, double longitude){
		this.setId(id); this.setLat(latitude); this.setLng(longitude);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLat() {
		return latitude;
	}
	public void setLat(double latitude) {
		this.latitude = latitude;
	}
	public double getLng() {
		return longitude;
	}
	public void setLng(double longitude) {
		this.longitude = longitude;
	}
}
