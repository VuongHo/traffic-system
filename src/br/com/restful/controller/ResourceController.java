package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.ResourceDAO;
import br.com.restful.model.Resources;

public class ResourceController {
	public ArrayList<Resources> listSegments(double radius, double latitude, double longitude){
		System.out.println("Resource controller");
		return ResourceDAO.getInstance().listSegments(radius, latitude, longitude);
	}
}
