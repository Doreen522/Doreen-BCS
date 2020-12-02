package com.active.lk.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Court")
public class Court {
	
	@Id
	public String id;
	
	public String name;
	
	public String venue;
	
	public String activId;
	
	public String institutionId;
	
	public String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getActivId() {
		return activId;
	}

	public void setActivId(String activId) {
		this.activId = activId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUpdateDateTime(Date date) {
		// TODO Auto-generated method stub
		
	}

	public void setCreateDateTime(Date date) {
		// TODO Auto-generated method stub
		
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	
	
	
	
	
}
