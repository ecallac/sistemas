/**
 * 
 */
package com.security.web.bean;

import java.util.List;

/**
 * @author ecalla
 *
 */
public class TreeNode {
	private String id;
	private String parent;
	private String text;
	private String icon;
	private State state;
	private List<TreeNode> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", parent=" + parent + ", text=" + text + ", icon=" + icon + ", state=" + state
				+ ", children=" + children + "]";
	}
	
}
