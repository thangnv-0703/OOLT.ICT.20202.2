package tree.binary;

import tree.Tree;
import tree.exception.DuplicateValue;
import tree.exception.FullChildException;
import tree.exception.NotExitException;
import tree.node.Node;

public class BinaryTree extends Tree {
	@Override
	public void insert(int parent,int value)  {
		try {
			if (this.search(this.root, value) == null) {
				Node parentNode = search(this.root, parent);
				if (parentNode != null) {
					if (parentNode.getChildren().size()<=1) {
						Node childNode = new Node(value);
						parentNode.setChild(childNode);
						childNode.setParent(parentNode);
					}
					else {
						throw new FullChildException("Node " + parent + " has enought children");
					}
				} else {
					throw new NotExitException("No node in tree has value: " + parent);
				}
			}
			else {
				throw new DuplicateValue("Value " + value + " already exit");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
}
