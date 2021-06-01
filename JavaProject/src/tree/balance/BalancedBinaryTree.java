package tree.balance;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
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
    @Override
	public void drawTree(Pane drawPane) {
		drawBinaryTree(drawPane, 600, 30,root, 30, 600);
		
	}
	public void drawBinaryTree(Pane drawPane, double x, double y, Node node, double size, double Xbefore) {
		if (node!=root) {
			Line line = new Line(node.getParent().getX(), node.getParent().getY() + size * 10 / 9.25, x, y);
			drawPane.getChildren().add(line);
		}
		node.drawNode(drawPane,x, y,size);
		node.setX(x);
		node.setY(y);
		node.setSize(size);
		if (node.getChildren().size()>0)
		if (node.getChildren().get(0) != null)
			drawBinaryTree(drawPane, x - Xbefore / 2, y + 75, node.getChildren().get(0), (size * 9.25 / 10), Xbefore / 2);
		if (node.getChildren().size()==2)
			if (node.getChildren().get(1) != null)
			drawBinaryTree(drawPane, x + Xbefore / 2, y + 75, node.getChildren().get(1), size * 9.25 / 10, Xbefore / 2);
	}
}
