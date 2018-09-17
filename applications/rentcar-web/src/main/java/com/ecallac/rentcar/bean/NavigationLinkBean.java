/**
 * 
 */
package com.ecallac.rentcar.bean;

/**
 * @author efrain.calla
 *
 */
public class NavigationLinkBean {
	private String path;
	private String name;
	private int level;
	private boolean active;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
