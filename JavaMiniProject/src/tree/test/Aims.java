package tree.test;

import tree.Tree;
import tree.balance.BalanceTree;
import tree.binary.BinaryTree;

public class Aims {

	public static void main(String[] args) {
//		BinaryBalanceTree tree = new BinaryBalanceTree();
//		tree.add(3);
//		tree.add(9);
//		tree.add(24);
//		tree.add(5);
//		tree.add(7);
//		tree.add(17);
//		tree.add(12);
//		tree.add(20);
//		tree.add(23);
//		tree.add(1);
//		tree.delete(7);
//		tree.add(30);
//		tree.traversePreOrder(tree.getRoot());
		
		BalanceTree gTree = new BalanceTree();
		gTree.setDistance(2);
		gTree.inserRoot(1);
		gTree.insert(1, 2);
		gTree.insert(1, 3);
		gTree.insert(1, 4);
		gTree.insert(2, 5);
		gTree.insert(2, 6);
		gTree.insert(3, 7);
		gTree.insert(6, 8);
		gTree.insert(6, 9);
		gTree.traverse(gTree.getRoot());
		System.out.println(gTree.getMaxDepth()+"   "+gTree.getMinDepth());
	}
}
