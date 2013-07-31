package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.restful.controller.NodeController;
import br.com.restful.model.Node;

@Path("/segment")
public class ApiResource {
	
	@GET
	@Path("/nodes")
	@Produces("application/json")
	public ArrayList<Node> listTodos(){
		return new NodeController().listTodo();
	}
	
}
