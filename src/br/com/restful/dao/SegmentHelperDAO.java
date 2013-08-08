package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Node;
import br.com.restful.model.Resources;
import br.com.restful.model.Segments;

public class SegmentHelperDAO extends ConnectionFactory{
	private Connection connect = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public SegmentHelperDAO(){
		connect = createConnection();
	}
	public double[] getCellRoot(){
		double[] cells = new double[2];
		try{
			pstmt = connect.prepareStatement("SELECT cell_lat, cell_lon FROM cell WHERE cell_id = 0 LIMIT 1");
			rs = pstmt.executeQuery();
			while(rs.next()){
				cells[0] = rs.getDouble("cell_lat");
				cells[1] = rs.getDouble("cell_lon");
			}
		}catch (Exception e){
			System.out.println("An Error occurred on getCellRoot: " + e);
		}
		return cells;
	}
	
	public List<Integer> getCellIds(int[] coordinates){
		List<Integer> cellIds = new ArrayList<>();
		try{
			for (int i = coordinates[0]; i <= coordinates[1]; i++) {
				for (int j = coordinates[2]; j <= coordinates[3]; j++) {
					pstmt = connect.prepareStatement("SELECT cell_id FROM cell WHERE cell_x = ? AND cell_y = ? LIMIT 1");
					pstmt.setInt(1, j);
					pstmt.setInt(2, i);
					rs = pstmt.executeQuery();
					while(rs.next()){
						cellIds.add(rs.getInt("cell_id"));
					}
				}
			}
		}catch(Exception e){
			System.out.println("An Error occurred on getCellIds: " + e);
		}
		
		return cellIds;
	}
	public List<Integer> find_street_id(List<Integer> cellIds){
		List<Integer> streetIds = new ArrayList<>();
		String builder = "";
		for (int i = 0; i < cellIds.size(); i++) {
			if (builder == "") builder += "?";
			else builder += ",?";
		}
		
		try{
			pstmt = connect.prepareStatement("SELECT DISTINCT street_id FROM segmentcell JOIN segment ON segmentcell.segment_id = segment.segment_id WHERE cell_id IN ("+builder+")");
			int j = 1;
			Iterator<Integer> itr= cellIds.iterator();
			while(itr.hasNext()){
				pstmt.setInt(j, itr.next());
				j++;
			}
			rs = pstmt.executeQuery(); 
			while(rs.next()){
				streetIds.add(rs.getInt("street_id"));
			}
					
		}catch(Exception e){
			System.out.println("An Error occurred on find_street_id: " + e);
		}
		return streetIds;
	}
	
	public List<Integer> find_segment_id(List<Integer> cellIds){
		List<Integer> segmentIds = new ArrayList<>();
		String builder = "";
		for (int i = 0; i < cellIds.size(); i++) {
			if (builder == "") builder += "?";
			else builder += ",?";
		}
		System.out.println("test " + builder);
		try{
			pstmt = connect.prepareStatement("SELECT DISTINCT segment.segment_id FROM segmentcell JOIN segment ON segmentcell.segment_id = segment.segment_id WHERE cell_id IN ("+builder+")");
			int j = 1;
			Iterator<Integer> itr= cellIds.iterator();
			while(itr.hasNext()){
				pstmt.setInt(j, itr.next());
				j++;
			}
			rs = pstmt.executeQuery(); 
			while(rs.next()){
				segmentIds.add(rs.getInt("segment_id"));
			}
					
		}catch(Exception e){
			System.out.println("An Error occurred on find_street_id: " + e);
		}
		return segmentIds;
	}
	public ArrayList<Resources> find_segment_speed(List<Integer> streetIds, List<Integer> segmentIds){
		ArrayList<Resources> resources = new ArrayList<Resources>();
		String builder = "";
		for (int i = 0; i < segmentIds.size(); i++) {
			if (builder == "") builder += "?";
			else builder += ",?";
		}
		
		Iterator<Integer> st_ids = streetIds.iterator();
		while(st_ids.hasNext()){
			try {
				int st_id = st_ids.next();
				pstmt = connect.prepareStatement("SELECT segment.node_id_start AS s_id, node_s.node_lat AS s_lat, node_s.node_lon AS s_lon, segment.node_id_end AS e_id, node_e.node_lat AS e_lat, node_e.node_lon AS e_lon, segds.speed"
								+ " FROM segmentdetails AS segds"
								+ " JOIN segment ON segment.segment_id = segds.segment_id"
								+ " JOIN node AS node_s ON node_s.node_id = segment.node_id_start"
								+ " JOIN node AS node_e ON node_e.node_id = segment.node_id_end" 
								+ " WHERE segment.street_id = ? AND segment.segment_id IN ( " + builder + " )"
								+ " GROUP BY segment.segment_id");
				pstmt.setInt(1, st_id);
				int j = 2;
				Iterator<Integer> itr= segmentIds.iterator();
				while(itr.hasNext()){
					pstmt.setInt(j, itr.next());
					j++;
				}
				rs = pstmt.executeQuery();
				ArrayList<Segments> segments = new ArrayList<Segments>();
				while(rs.next()){
					segments.add(new Segments(new Node(rs.getInt("s_id"), rs.getDouble("s_lat"), rs.getDouble("s_lon")), new Node(rs.getInt("e_id"), rs.getDouble("e_lat"), rs.getDouble("e_lon")), rs.getFloat("speed")));
				}
				resources.add(new Resources(st_id, segments));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		return resources;
	}
	public ArrayList<Resources> get_all_segment_for_each_cells(List<Integer> cellIds){
		ArrayList<Resources> resources = new ArrayList<Resources>();
		List<Integer> streetIds = this.find_street_id(cellIds);
		List<Integer> segmentIds = this.find_segment_id(cellIds);
		resources = find_segment_speed(streetIds, segmentIds);
		disConnect(connect, pstmt, rs);
		return resources;
	}
}
