package tree.binary;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import tree.Tree;
import tree.node.Node;
import tree.node.*;

public class BinaryTree extends Tree {
	@Override
	public void insert(int parent,int value) {
		Node parentNode = search(this.root, parent);
		if (parentNode != null) {
			if (parentNode.getChildren().size()<=1) {
				Node childNode = new Node(value);
				parentNode.setChild(childNode);
				childNode.setParent(parentNode);
			}
			else {
				System.out.println("parent node has enought children ");
			}
		} else {
			System.out.println("No node in tree has value: " + parent);
		}
	}

	@Override
	public void drawTree(Pane drawPane) {
		drawBinaryTree(drawPane, 1200, 100,root, 50, 1000);
		
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
			drawBinaryTree(drawPane, x - Xbefore / 2, y + 150, node.getChildren().get(0), (size * 9.25 / 10), Xbefore / 2);
		if (node.getChildren().size()==2)
			if (node.getChildren().get(1) != null)
			drawBinaryTree(drawPane, x + Xbefore / 2, y + 150, node.getChildren().get(1), size * 9.25 / 10, Xbefore / 2);
	}
}
