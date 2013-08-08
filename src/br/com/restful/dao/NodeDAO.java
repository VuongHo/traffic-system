package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Node;

public class NodeDAO extends ConnectionFactory{
	private static NodeDAO instance;
	
	public static NodeDAO getInstance(){
		if(instance == null)
			instance = new NodeDAO();
		return instance;
	}
	
	public ArrayList<Node> listTodos(){
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Node> nodes = null;
		connect = createConnection();
		nodes = new ArrayList<Node>();
		try{
			pstmt = connect.prepareStatement("select * from node limit 3");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Node node = new Node(123,rs.getFloat("node_lat"), rs.getFloat("node_lon"));
				nodes.add(node);
			}
		} catch (Exception e){
			System.out.println("An Error occurred: " + e);
		} finally {
			disConnect(connect, pstmt, rs);
		}
		return nodes;
		
	}
}
