package tree.balance;

import tree.Tree;
import tree.node.Node;

public class BalanceTree extends Tree {

    protected int distance;
    private final int MAX_NUMBER = 1000000;
    private final int MIN_NUMBER = -1000000;
	
    public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
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
	
	private Node findDeepestNode(Node x, int distance) {
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
	
	private int findMin(Node x, int min) {
		if (x.isLeaf()) {
			return min <= x.getDeepthFromRoot() ? min : x.getDeepthFromRoot();
//			return 1;
		} else {
			for (Node child: x.getChildren()) {
				int temp = findMin(child, min);
				if (temp < min) {
					min = temp;
				}
			}
		}
		return min;
	}
	
	public int getMaxDeepth(Node x) {
		return findMax(x, MIN_NUMBER);
	}
	
	public int getMinDeepth(Node x) {
		return findMin(x, MAX_NUMBER);
	}
	  
    @Override
	public void insert(int parent,int value) {
		Node parentNode = search(this.root, parent);
		if (parentNode != null) {
			if (parentNode.getDeepthFromRoot() + 1 - this.getMinDeepth(this.root) <= distance) {
				Node childNode = new Node(value);
				parentNode.setChild(childNode);
				childNode.setParent(parentNode);
			}
			else {
				System.out.println("cannot insert this value ");
			}
		} else {
			System.out.println("No node in tree has value: " + parent);
		}
	}
    
    @Override
	public void remove(int value) {
    	Node x = search(root, value);
		if (x == null) {
			System.out.println("Node " + value + " not in tree");
		} else {
			Node parentNode = x.getParent();
			if (x.isLeaf()) {
				if ( this.getMaxDeepth(this.root)-x.getDeepthFromRoot()+1<=distance) {
					parentNode.getChildren().remove(x);
				} else {
					System.out.println("Remove make tree unbalanced");
				}
			} else {
//				int distance = x.getDeepthFromRoot() + this.getMaxDeepth(x);
				Node deepestNode = findLeftMost(x);
				deepestNode.getParent().getChildren().remove(deepestNode);
				parentNode.getChildren().remove(x);
				parentNode.setChild(deepestNode);
				deepestNode.setParent(parentNode);
				deepestNode.setChildren(x.getChildren());
				for (Node child: x.getChildren()) {
					child.setParent(deepestNode);
				}
			}
		}
	}

}