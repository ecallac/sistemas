/**
 * 
 */
package com;

import java.io.Serializable;
import java.util.Date;


/**
 * @author efrain.calla
 *
 */
public class BaseEntity implements Serializable {

	public String toString() {
		return this.getClass().getCanonicalName() + " : ID=" + this.id;
	}
	private Long id;
	private Date dateCreated;
	private Date dateUpdated;
	private String createdBy;
	private String updatedBy;
	private int version;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
