package Tree.Binary;

import Tree.Node;

public class BinaryTree {
	private Node root;

	public Node getRoot() {
		return root;
	}

	public BinaryTree() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// ADD
	Node addRecursive(Node current, int value) {
	    if (current == null) {
	        return new Node(value);
	    }

	    if (value < current.getValue()) {
	        current.setLeft(addRecursive(current.getLeft(), value));
	    } else if (value > current.getValue()) {
	        current.setRight(addRecursive(current.getRight(), value));  
	    } else {
	        // value already exists
	        return current;
	    }

	    return current;
	}	

	public void add(int value) {
		root = addRecursive(root, value);
	}
	
    // SEARCH	
	public Node search(Node root, int value)
	{
	    // Base Cases: root is null or value is present at root
	    if (root==null || root.getValue() ==value)
	        return root;
	 
	    // value is greater than root's value
	    if (root.getValue() < value)
	       return search(root.getRight(), value);
	 
	    // value is smaller than root's value
	    return search(root.getLeft(), value);
	}
	
	//DELETE
	
	int findSmallestValue(Node root) {
	    return root.getLeft() == null ? root.getValue() : findSmallestValue(root.getLeft());
	}
	
	Node deleteRecursive(Node current, int value) {
	    if (current == null) {
	        return null;
	    }

	    if (value == current.getValue()) {
	        // Node to delete found
	        
	    	if (current.getLeft() == null && current.getRight() == null) {
	    	    return null;
	    	}
	    	if  (current.getRight() == null) {
	    	    return current.getLeft();
	    	}

	    	if (current.getLeft() == null) {
	    	    return current.getRight();
	    	}
	    	else {
	    		int smallestValue = findSmallestValue(current.getRight());
	    		current.setValue(smallestValue);
	    		current.setRight(deleteRecursive(current.getRight(), smallestValue));
	    		return current;
			}    		
	    } 
	    else if (value < current.getValue()) {
	        current.setLeft(deleteRecursive(current.getLeft(), value)); 
	        return current;
	    }
	    else {
		    current.setRight(deleteRecursive(current.getRight(), value)); 
		    return current;
	    }
	}
	
	public void delete(int value)
	{
		root = deleteRecursive(root, value);
		
	}
	
	  //PRE-ORDER
	public void traversePreOrder(Node node) {
	    if (node != null) {
	        System.out.print(" " + node.getValue());
	        traversePreOrder(node.getLeft());
	        traversePreOrder(node.getRight());
	    }
	}
}
