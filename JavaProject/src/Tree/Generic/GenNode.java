package Tree.Generic;

import java.util.ArrayList;

public class GenNode {
	private int value;
	private GenNode parent;
	ArrayList<GenNode> children = new ArrayList<GenNode>();
			
	public GenNode(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ArrayList<GenNode> getChildren() {
		return children;
	}
	
	
	public void setChild(GenNode x) {
		this.children.add(x);
	}

	public GenNode getParent() {
		return parent;
	}

	public void setParent(GenNode parent) {
		this.parent = parent;
	}
	
}
