package br.com.restful.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resources {
	private int street_id;
	private ArrayList<Segments> segment;
	
	public Resources(){
		this.street_id = 0;
		this.segment = null;
	}
	public Resources(int street_id, ArrayList<Segments> segment){
		this.street_id = street_id; this.segment = segment;
	}
	public int getStreetId() {
		return street_id;
	}
	public void setStreetId(int street_id) {
		this.street_id = street_id;
	}
	public ArrayList<Segments> getSegment() {
		return segment;
	}
	public void setSegment(ArrayList<Segments> segment) {
		this.segment = segment;
	}
}
