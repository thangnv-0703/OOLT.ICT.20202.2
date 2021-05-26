package tree.Test;

import tree.balance.BalanceTree;
import tree.node.Node;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BalanceTree gTree = new BalanceTree();
		gTree.setDistance(2);
		gTree.insertRoot(1);
		gTree.insert(1, 2);
		gTree.insert(1, 3);
		gTree.insert(1, 4);
		gTree.insert(2, 5);
		gTree.insert(5, 10);
		gTree.insert(2, 6);
		gTree.insert(3, 7);
		gTree.insert(6, 8);
		gTree.insert(6, 9);
		gTree.remove(6);
		gTree.traverse(gTree.getRoot());
//		gTree.insert(9, 10);
	}

}
