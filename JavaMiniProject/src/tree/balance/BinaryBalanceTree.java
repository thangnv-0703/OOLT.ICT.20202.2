package tree.balance;

import tree.node.Node;

public class BinaryBalanceTree extends BalanceTree {
	 @Override
		public void insert(int parent,int value) {
			Node parentNode = search(this.root, parent);
			if (parentNode != null && parentNode.getChildren().size()<=1) {
				Node childNode = new Node(value);
				parentNode.setChild(childNode);
				childNode.setParent(parentNode);
				if (getMaxDepth()-getMinDepth()<=distance) {}
				else {
					remove(value);
					System.out.println("canot insert this value ");
				}
			} else {
				System.out.println("No node in tree has value: " + parent);
			}
		}
}
