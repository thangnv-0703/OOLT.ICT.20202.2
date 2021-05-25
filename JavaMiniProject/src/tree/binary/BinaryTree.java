package tree.binary;

import tree.Tree;
import tree.node.Node;

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
}
