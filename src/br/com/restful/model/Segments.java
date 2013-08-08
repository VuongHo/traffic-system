package br.com.restful.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Segments {
	private Node node_start;
	private Node node_end;
	private float speed;
	public Segments(){
		this.setNodeS(null);
		this.setNodeE(null);
		this.setSpeed(0);
	}
	public Segments(Node node_start, Node node_end, float speed){
		this.setNodeS(node_start); this.setNodeE(node_end); this.setSpeed(speed);
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public Node getNodeE() {
		return node_end;
	}
	public void setNodeE(Node node_end) {
		this.node_end = node_end;
	}
	public Node getNodeS() {
		return node_start;
	}
	public void setNodeS(Node node_start) {
		this.node_start = node_start;
	}
}
