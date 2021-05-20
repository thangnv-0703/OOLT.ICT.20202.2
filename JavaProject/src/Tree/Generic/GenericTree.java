package Tree.Generic;


import Tree.GenNode;

public class GenericTree {
	private	GenNode root;
	private final int MAX_NODE = 3;
	
	public GenericTree create() {
		return new GenericTree();
	}
	
	public GenNode search(GenNode root, int value) {
		if (this.root.getValue() == value) {
			return this.root;
		} 
		if (this.root.getChildren().size() > 0) {
			for (GenNode child: this.root.getChildren()) {
				if (search(child, value) != null) {
					return child;
				}
			}
		}
		return null;
	}
	
	public void insert(int parent, int value) {
		if (search(this.root, parent) != null) {
			GenNode parentNode = search(this.root, parent);
			if (parentNode.getChildren().size() <= this.MAX_NODE) {
				parentNode.setChild(value);
			}
			else {
				System.out.println("Node " + parent + " can't have more than 3 nodes");
			}
		} else {
			System.out.println("No node in tree has value: " + parent);
		}
	}
	
//	public void removeRecursive(GenNode current, int value) {
//		if (current.getChildren().contains(current))
//	}
	
	public void remove(int value) {
		if (this.root.getValue() == value) {
			System.out.println("Can't not delete root");
			return;
		} 
	}
}
