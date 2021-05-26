package Tree.Binary;

public class BinaryBalanceTree extends BinaryTree{
	private int balanceFactor = 1;
	
	

	public BinaryBalanceTree() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}
	
	private int height(Node N) {
	    if (N == null)
	    	return 0;
	    return N.getHeight();
	}

	private int max(int a, int b) {
	    return (a > b) ? a : b;
	}
	
	private Node rightRotate(Node y) {
		Node x = y.getLeft();
		Node T2 = x.getRight();
		x.setRight(y);
		y.setLeft(T2);
		y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
		x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
		return x;
	}

	private Node leftRotate(Node x) {
		Node y = x.getRight();
		Node T2 = y.getLeft();
		y.setLeft(x);
		x.setRight(T2);
		x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
		y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
		return y;
	}	
	  
	// Get balance factor of a current
	int getBalance(Node N) {
	    if (N == null)
	    	return 0;
	    return height(N.getLeft()) - height(N.getRight());
	}
	
	public Node selfBalancedAfterInsert(Node current, int value) {
	    current.setHeight(1 + max(height(current.getLeft()), height(current.getRight())));
	    int balance = getBalance(current);
	    if (balance > this.balanceFactor) {
	      if (value < current.getLeft().getValue()) {
	        return rightRotate(current);
	      } else if (value > current.getLeft().getValue()) {
	        current.setLeft(leftRotate(current.getLeft()));
	        return rightRotate(current);
	      }
	    }
	    if (balance < -this.balanceFactor) {
	      if (value > current.getRight().getValue()) {
	        return leftRotate(current);
	      } else if (value < current.getRight().getValue()) {
	        current.setRight(rightRotate(current.getRight()));
	        return leftRotate(current);
	      }
	    }
	    return current;
	}
	
	public Node selfBalancedAfterDelete(Node current, int value) {
	    current.setHeight(1 + max(height(current.getLeft()), height(current.getRight())));
	    int balance = getBalance(current);
	    if (balance > this.balanceFactor) {
	      if (getBalance(current.getLeft()) >= 0) {
	        return rightRotate(current);
	      } else {
	        current.setLeft(leftRotate(current.getLeft()));
	        return rightRotate(current);
	      }
	    }
	    if (balance < - this.balanceFactor) {
	      if (getBalance(current.getRight()) <= 0) {
	        return leftRotate(current);
	      } else {
	        current.setRight(rightRotate(current.getRight()));
	        return leftRotate(current);
	      }
	    }
	    return current;
	}
	
	Node nodeWithMimumValue(Node node) {
		Node current = node;
		while (current.getLeft() != null)
			current = current.getLeft();
		return current;
	}
	
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

	    return selfBalancedAfterInsert(current, value);
	}
	
	Node deleteRecursive(Node current, int value) {
	    if (current == null) {
	        return null;
	    }


	    if (value < current.getValue()) {
	        current.setLeft(deleteRecursive(current.getLeft(), value)); 
	    }
	    else if (value > current.getValue()){
		    current.setRight(deleteRecursive(current.getRight(), value)); 
	    }
	    else  {
	        // Node to delete found
	        
	    	if ((current.getLeft() == null) || (current.getRight() == null)) {
	            Node temp = null;
	            if (temp == current.getLeft())
	              temp = current.getRight();
	            else
	              temp = current.getLeft();
	            if (temp == null) {
	              temp = current;
	              current = null;
	            } else
	              current = temp;
	    	} else {
	            Node temp = nodeWithMimumValue(current.getRight());
	            current.setValue(temp.getValue());
	            current.setRight(deleteRecursive(current.getRight(), temp.getValue()));
	    	}
	    }
	    if (current == null)
	    	return current;
	    return selfBalancedAfterDelete(current, value);
	}
}
