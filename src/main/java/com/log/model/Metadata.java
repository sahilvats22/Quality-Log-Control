package com.log.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Metadata {
    private String parentResourceId;

 // Getters and setters
    
	public String getParentResourceId() {
		return parentResourceId;
	}

	public void setParentResourceId(String parentResourceId) {
		this.parentResourceId = parentResourceId;
	}

    
    
}
