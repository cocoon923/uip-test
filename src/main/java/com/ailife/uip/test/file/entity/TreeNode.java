package com.ailife.uip.test.file.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmm on 9/30/2014.
 */
public class TreeNode<T> {

	private int id;
	private T t;
	private TreeNode<T> parent;

	private List<TreeNode<T>> children;

	public TreeNode(T t) {
		this.t = t;
		parent = null;
		children = new ArrayList<TreeNode<T>>();
	}

	public TreeNode(int id, T t, TreeNode<T> parent, List<TreeNode<T>> children) {
		this.id = id;
		this.t = t;
		this.parent = parent;
		this.children = children;
	}

	public int getId() {
		return id;
	}

	public T getT() {
		return t;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public List<TreeNode<T>> getChildren() {
		return children;
	}

	public boolean isRoot() {
		return this.parent == null;
	}

	public boolean isLeaf() {
		return this.children == null || this.children.size() == 0;
	}

	public void addChild(TreeNode<T> child) {
		this.getChildren().add(child);
	}

}
