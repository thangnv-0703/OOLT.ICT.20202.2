package tree.balance;

import tree.Tree;
import tree.exception.DuplicateValue;
import tree.exception.NotExitException;
import tree.exception.UnbalancedException;
import tree.node.Node;

public class BalanceTree extends Tree {

    protected int distance;

	
    public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	private int findMin(Node x, int min) {
		if (x.isLeaf()) {
			return min <= x.getDeepthFromRoot() ? min : x.getDeepthFromRoot();
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
	
	public int getMinDeepth(Node x) {
		return findMin(x, 1000000);
	}
	  
    @Override
	public void insert(int parent,int value) {
    	try {
        	if (search(root, value) == null) {
        		Node parentNode = search(this.root, parent);
        		if (parentNode != null) {
        			if (parentNode.getDeepthFromRoot() + 1 - this.getMinDeepth(this.root) <= distance) {
        				Node childNode = new Node(value);
        				parentNode.setChild(childNode);
        				childNode.setParent(parentNode);
        			}
        			else {
        				throw new UnbalancedException("Insert " + value + " make tree unbalanced");
        			}
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
    
    @Override
	public void remove(int value) {
    	try {
        	Node x = search(root, value);
    		if (x == null) {
    			throw new NotExitException("No node in tree has value: " + value);
    		} else {
    			Node parentNode = x.getParent();
    			if (x.isLeaf()) {
    				if ( this.getMaxDeepth(this.root)-x.getDeepthFromRoot()+1<=this.distance) {
    					parentNode.getChildren().remove(x);
    				} else {
//    					find the max deep from node x
    					int distance = this.getMaxDeepth(this.root);
//    					find the node have max deep
    					Node deepestNode = this.findDeepestNode(this.root, distance);
//    					System.out.println("Remove make tree unbalanced");
    					
//    					remove similar to tree
    					deepestNode.getParent().getChildren().remove(deepestNode);
    					parentNode.getChildren().remove(x);
    					parentNode.getChildren().remove(x);
    					parentNode.setChild(deepestNode);
    					deepestNode.setParent(parentNode);
    				}
    			} else {
//    				find the max deep from node x
    				int distance = this.getMaxDeepth(this.root);
//    				find the node have max deep
    				Node deepestNode = this.findDeepestNode(this.root, distance);
    				
//    				remove similar to tree
    				deepestNode.getParent().getChildren().remove(deepestNode);
    				parentNode.getChildren().remove(x);
    				parentNode.setChild(deepestNode);
    				deepestNode.setParent(parentNode);
    				deepestNode.setChildren(x.getChildren());

//    				set children node
    				for (Node child: x.getChildren()) {
    					child.setParent(deepestNode);
    				}
    			}
    		}
    	} catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
	}

}