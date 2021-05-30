package tree.balance;

import tree.exception.DuplicateValue;
import tree.exception.FullChildException;
import tree.exception.NotExitException;
import tree.exception.UnbalancedException;
import tree.node.Node;

public class BalancedBinaryTree extends BalanceTree {
    @Override
	public void insert(int parent,int value) {
    	try {
        	if (search(root, value) == null) {
        		Node parentNode = search(this.root, parent);
        		if (parentNode != null) {
        			if (parentNode.getDeepthFromRoot() + 1 - this.getMinDeepth(this.root) <= distance) {
        				if (parentNode.getChildren().size()<=1) {
    						Node childNode = new Node(value);
    						parentNode.setChild(childNode);
    						childNode.setParent(parentNode);
    					}
    					else {
    						throw new FullChildException("Node " + parent + " has enought children");
    					}
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
}
