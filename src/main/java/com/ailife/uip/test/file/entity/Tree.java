package com.ailife.uip.test.file.entity;

/**
 * Created by chenmm on 9/30/2014.
 */
public class Tree<T> {

	private TreeNode<T> root;

	public Tree() {
	}

	public void addNode(TreeNode<T> node, T newNode) {
		if (node == null) {
			if (null == root) {
				root = new TreeNode<T>(newNode);
			}
		} else {
			TreeNode<T> temp = new TreeNode<T>(newNode);
			node.getChildren().add(temp);
		}
	}

	public TreeNode<T> search(TreeNode<T> input, T newNode) {
		TreeNode<T> temp = null;
		if (input.getT().equals(newNode)) {
			return input;
		}
		for (TreeNode<T> node : input.getChildren()) {
			temp = search(node, newNode);
			if (null != temp) {
				break;
			}
		}
		return temp;
	}

	public TreeNode<T> getNode(T newNode) {
		return search(root, newNode);
	}

}
