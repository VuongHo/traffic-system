package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Resources;

public class ResourceDAO extends ConnectionFactory {
	private static ResourceDAO instance;
	private static final double CELL_D_X = 0.003141;
	private static final double CELL_D_Y = 0.003764;
	
	public static ResourceDAO getInstance(){
		if (instance == null) instance = new ResourceDAO();
		return instance;
	}
	//http://localhost:8080/Restful/segment/speed;radius=0.001;lat=10.777725;lng=106.656064
	public ArrayList<Resources> listSegments(double radius, double latitude, double longitude){
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Resources> resources =  new ArrayList<Resources>();
		connect = createConnection();
		SegmentHelperDAO seg = new SegmentHelperDAO();
		try{
			int[] coordinates = new int[4];
			double[] cells = seg.getCellRoot();
			List<Integer> cellIds = null;
			coordinates[0] = (int) Math.abs((longitude - radius - cells[1])/CELL_D_X); //min_x
			coordinates[1] = (int) Math.abs((longitude + radius - cells[1])/CELL_D_X); //max_x  
			coordinates[2] = (int) Math.abs((latitude - radius - cells[0])/CELL_D_Y);  //min_y
			coordinates[3] = (int) Math.abs((latitude + radius - cells[0])/CELL_D_Y);  //max_y
	        
			cellIds = seg.getCellIds(coordinates);

			resources = seg.get_all_segment_for_each_cells(cellIds);
		}catch (Exception e){
			System.out.println("An Error occurred: ." + e);
		}finally{
			disConnect(connect, pstmt, rs);
		}
		return resources;
	}

}
