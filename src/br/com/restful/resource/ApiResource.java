package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.restful.controller.NodeController;
import br.com.restful.controller.ResourceController;
import br.com.restful.model.Node;
import br.com.restful.model.Resources;

@Path("/segment")
public class ApiResource {
	
	@GET
	@Path("/nodes")
	@Produces("application/json")
	public ArrayList<Node> listTodos(){
		return new NodeController().listTodo();
	}
	
	@GET
	@Path("/speed")
	@Produces("application/json")
	public ArrayList<Resources> listSegments(@MatrixParam("radius") double radius, @MatrixParam("lat") double latitude, 
			@MatrixParam("lng") double longitude){
		System.out.println("Factory has been created....");
		System.out.println(radius);
		System.out.println(latitude);
		System.out.println(longitude);
		return new ResourceController().listSegments(radius,latitude,longitude);
	}
	
}
