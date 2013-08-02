package br.com.restful.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resources {
	private int street_id;
	private Segments segment;
	
	public int getStreetId() {
		return street_id;
	}
	public void setStreetId(int street_id) {
		this.street_id = street_id;
	}
	public Segments getSegment() {
		return segment;
	}
	public void setSegment(Segments segment) {
		this.segment = segment;
	}
}
