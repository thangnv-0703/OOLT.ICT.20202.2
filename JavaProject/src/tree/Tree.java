package tree;

import tree.exception.DuplicateValue;
import tree.exception.NotExitException;
import tree.node.Node;

public class Tree {
	protected Node root;
	
	public Node getRoot() {
		return root;
	}
	
	private int findMax(Node x, int max) {
		if (x.isLeaf()) {
			return max > x.getDeepthFromRoot() ? max : x.getDeepthFromRoot();
//			return 0;
		} else {
			for (Node child: x.getChildren()) {
				int temp = findMax(child, max);
				if (temp > max) {
					max = temp;
				}
			}
		}
		return max;
	}

	
	public Node findDeepestNode(Node x, int distance) {
		if (x.isLeaf()) {
			return x.getDeepthFromRoot() == distance ? x : null;
		} else {
			for (Node child: x.getChildren()) {
				Node y = findDeepestNode(child, distance);
				if (y != null) {
					return y;
				}
			}
		} 
		return null;
	}
	
	public int getMaxDeepth(Node x) {
		return findMax(x, -1000000);
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
		try {
			if (this.search(this.root, value) == null) {
				if (search(this.root, parent) != null) {
					Node parentNode = search(this.root, parent);
					Node childNode = new Node(value);
					parentNode.setChild(childNode);
					childNode.setParent(parentNode);
				} else {
					throw new NotExitException("No node in tree has value: " + parent);
				}
			} else {
				throw new DuplicateValue("Value " + value + " already exit");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public void insert(boolean isRoot, int value) {
		if (isRoot) {
			this.root = new Node(value);
			this.root.setParent(null);
		}
	}
	
	public void remove(int value) throws NotExitException {
		try {
			if (search(root, value) == null) {
				throw new NotExitException("No node in tree has value: " + value);
			} else {
				Node x = search(root, value);
				Node parentNode = x.getParent();
				if (x.getChildren().size() == 0) {
					parentNode.getChildren().remove(x);
				} else {
					Node y = this.findDeepestNode(this.root, this.getMaxDeepth(this.root));
					y.getParent().getChildren().remove(y);
					parentNode.getChildren().remove(x);
					parentNode.setChild(y);
					y.setParent(parentNode);
					y.setChildren(x.getChildren());
					for(Node child: x.getChildren()) {
						child.setParent(y);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
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
