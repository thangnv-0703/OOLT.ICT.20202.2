package Tree.Binary;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import Tree.Node;
import Tree.Tree;

public class BinaryTree extends Tree {
	// ADD
	public Node addRecursive(Node current, int value) {
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
	
	@Override
	public void add(int value) {
		root = addRecursive(root, value);
	}
	
    // SEARCH	
	private boolean containsNodeRecursive(Node current, int value) {
	    if (current == null) {
	        return false;
	    } 
	    if (value == current.getValue()) {
	        return true;
	    } 
	    return value < current.getValue()
	      ? containsNodeRecursive(current.getLeft(), value)
	      : containsNodeRecursive(current.getRight(), value);
	}
	
	@Override
	public boolean search(int value) {
		return containsNodeRecursive(root, value);
	}
	
	//DELETE
	
	public int findSmallestValue(Node root) {
	    return root.getLeft() == null ? root.getValue() : findSmallestValue(root.getLeft());
	}
	
	public Node deleteRecursive(Node current, int value) {
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
	    if (value < current.getValue()) {
	        current.setLeft(deleteRecursive(current.getLeft(), value)); 
	        return current;
	    }
	    current.setRight(deleteRecursive(current.getRight(), value)); 
	    return current;
	}
	
	@Override
	public void delete(int value)
	{
		root = deleteRecursive(root, value);
		
	}
	
	// UPDATE
	public void update(Node current,int value) {
		boolean check = search(current.getValue());
		if ( check = true)
		{
			delete(current.getValue());
			add(value);
		}
		else {
			System.out.println(current.getValue() +"is not in the tree");
		}
	}
	
	// DEPTH-FIRST SEARCH
	  // IN ORDER
	public void traverseInOrder(Node node) {
	    if (node != null) {
	        traverseInOrder(node.getLeft());
	        System.out.print(" " + node.getValue());
	        traverseInOrder(node.getRight());
	    }
	}
	
	  //PRE-ORDER
	public void traversePreOrder(Node node) {
	    if (node != null) {
	        System.out.print(" " + node.getValue());
	        traversePreOrder(node.getLeft());
	        traversePreOrder(node.getRight());
	    }
	}
	
	  //POST-ORDER
	public void traversePostOrder(Node node) {
	    if (node != null) {
	        traversePostOrder(node.getLeft());
	        traversePostOrder(node.getRight());
	        System.out.print(" " + node.getValue());
	    }
	}
	
	// BREADTH-FIRST SEARCH
	public void traverseLevelOrder() {
	    if (root == null) {
	        return;
	    }

	    Queue<Node> nodes = new LinkedList<>();
	    nodes.add(root);

	    while (!nodes.isEmpty()) {

	        Node node = nodes.remove();

	        System.out.print(" " + node.getValue());

	        if (node.getLeft() != null) {
	            nodes.add(node.getLeft());
	        }

	        if (node.getRight() != null) {
	            nodes.add(node.getRight());
	        }
	    }
	}

}
