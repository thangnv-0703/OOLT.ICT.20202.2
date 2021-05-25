package tree.node;

import java.util.ArrayList;

public class Node {
	private int value;
	private Node parent;
	ArrayList<Node> children = new ArrayList<Node>();
			
	public Node(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}
	
	
	public void setChild(Node x) {
		this.children.add(x);
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
}
