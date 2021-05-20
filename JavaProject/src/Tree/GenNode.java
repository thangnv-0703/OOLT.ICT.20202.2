package Tree;

import java.util.ArrayList;

public class GenNode {
	private int value;
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
	
	public void setChild(int value) {
		GenNode newNode = new GenNode(value);
		this.children.add(newNode);
	}
}
