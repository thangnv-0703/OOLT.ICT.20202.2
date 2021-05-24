package Tree.Generic;

public class GenericTree {
	private	GenNode root;
	private final int MAX_NODE = 3;

	public GenNode search(GenNode x, int value) {
		if (x.getValue() == value) {
			return x;
		} 
		if (x.getChildren().size() > 0) {
			for (GenNode child: x.getChildren()) {
				if (search(child, value) != null) {
					return child;
				}
			}
		}
		return null;
	}
	
	public GenNode getRoot() {
		return root;
	}

	public int getMAX_NODE() {
		return MAX_NODE;
	}

	public void insert(int parent, int value) {
		if (search(this.root, parent) != null) {
			GenNode parentNode = search(this.root, parent);
			GenNode childNode = new GenNode(value);
			parentNode.setChild(childNode);
			childNode.setParent(parentNode);
//			if (parentNode.getChildren().size() <= this.MAX_NODE) {
//			}
//			else {
//				System.out.println("Node " + parent + " can't have more than 3 nodes");
//			}
		} else {
			System.out.println("No node in tree has value: " + parent);
		}
	}
	
	public void inserRoot(int value) {
		GenNode x = new GenNode(value);
		this.root = x;
		this.root.setParent(null);
	}
	
	private GenNode findLeftMost(GenNode x) {
		if (x.getChildren().size() == 0) {
			return x;
		}
		GenNode child = x.getChildren().get(0);
		return findLeftMost(child);
	}
	
	public void remove(int value) {
		if (search(root, value) == null) {
			System.out.println("Node " + value + " not in tree");
		} else {
			GenNode x = search(root, value);
			GenNode parentNode = x.getParent();
			if (x.getChildren().size() == 0) {
				parentNode.getChildren().remove(x);
			} else {
				GenNode y = findLeftMost(x);
				y.getParent().getChildren().remove(y);
				parentNode.getChildren().remove(x);
				parentNode.setChild(y);
				y.setParent(parentNode);
			}
		}
	}
	
	public void traverse(GenNode x) {
		if (x == null) {
			return;
		}
		if (x.getParent() != null) {
			System.out.println(x.getValue() + "-" + x.getParent().getValue());
		} else {
			System.out.println(x.getValue() + "==");
		}
//		count++;
		for (GenNode child: x.getChildren()) {
			traverse(child);
		}
	}
	
	public void update(int newValue, int outValue) {
		
	}
}
