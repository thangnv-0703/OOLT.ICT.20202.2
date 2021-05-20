package Tree.Test;

import Tree.Binary.BinaryBalanceTree;

public class Aims {

	public static void main(String[] args) {
		BinaryBalanceTree tree = new BinaryBalanceTree();
		tree.add(3);
		tree.add(9);
		tree.add(24);
		tree.add(5);
		tree.add(7);
		tree.add(17);
		tree.add(12);
		tree.add(20);
		tree.add(23);
		tree.add(1);
		tree.delete(7);
		tree.add(30);
		tree.traversePreOrder(tree.getRoot());
	}
}
