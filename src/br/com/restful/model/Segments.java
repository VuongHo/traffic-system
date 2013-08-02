package br.com.restful.model;

public class Segments {
	private Node node_start;
	private Node node_end;
	private float speed;
	public Node getNodeStart() {
		return node_start;
	}
	public void setNodeStart(Node node_start) {
		this.node_start = node_start;
	}
	public Node getNodeEnd() {
		return node_end;
	}
	public void setNodeEnd(Node node_end) {
		this.node_end = node_end;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
