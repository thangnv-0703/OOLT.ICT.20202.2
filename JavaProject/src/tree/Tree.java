package tree;

import tree.node.Node;

public class Tree {
	protected Node root;
	public Node getRoot() {
		return root;
	}
	public Node search(Node x, int value) {
		if (x.getValue() == value) {
			return x;
		} 
		if (x.getChildren().size() > 0) {
			for (Node child: x.getChildren()) {
				if (search(child, value) != null) {
					return search(child, value);
				}
			}
		}
		return null;
	}
	
	

	public void insert(int parent, int value) {
		if (this.search(this.root, value) != null) {
			if (search(this.root, parent) != null) {
				Node parentNode = search(this.root, parent);
				Node childNode = new Node(value);
				parentNode.setChild(childNode);
				childNode.setParent(parentNode);
			} else {
				System.out.println("No node in tree has value: " + parent);
			}
		} else {
			System.out.println("Value already exit");
		}
	}
	
	public void insertRoot(int value) {
		Node x = new Node(value);
		this.root = x;
		this.root.setParent(null);
	}
	
	protected Node findLeftMost(Node x) {
		if (x.getChildren().size() == 0) {
			return x;
		}
		Node child = x.getChildren().get(0);
		return findLeftMost(child);
	}
	
	public void remove(int value) {
		if (search(root, value) == null) {
			System.out.println("Node " + value + " not in tree");
		} else {
			Node x = search(root, value);
			Node parentNode = x.getParent();
			if (x.getChildren().size() == 0) {
				parentNode.getChildren().remove(x);
			} else {
				Node y = findLeftMost(x);
				y.getParent().getChildren().remove(y);
				parentNode.getChildren().remove(x);
				parentNode.setChild(y);
				y.setParent(parentNode);
			}
		}
	}
	
	public void traverse(Node x) {
		if (x == null) {
			return;
		}
		if (x.getParent() != null) {
			System.out.println(x.getValue() + "-" + x.getParent().getValue());
		} else {
			System.out.println(x.getValue());
		}
		for (Node child: x.getChildren()) {
			traverse(child);
		}
	}
}	
