package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.NodeDAO;
import br.com.restful.model.Node;

public class NodeController {
	public ArrayList<Node> listTodo(){
		System.out.println("Node controller");
		return NodeDAO.getInstance().listTodos();
	}
}
