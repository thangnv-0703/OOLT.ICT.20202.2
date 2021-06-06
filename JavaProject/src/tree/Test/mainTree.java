package tree.Test;

import controller.TreeScreen;
import tree.Tree;
import tree.balance.BalanceTree;
import tree.balance.BalancedBinaryTree;
import tree.binary.BinaryTree;

public class mainTree {
	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
		
		binaryTree.insert(true,13);
		binaryTree.insert(13,9);
		binaryTree.insert(13,15);
		binaryTree.insert(15,7);
		binaryTree.insert(9,8);
		binaryTree.insert(9,19);
		binaryTree.insert(8,14);
		binaryTree.insert(7,1);
		binaryTree.insert(1,10);
		Tree genTree=new Tree() {
		};
		genTree.insert(true,13);
		genTree.insert(13, 5);

		genTree.insert(13, 6);

		genTree.insert(13, 8);
		genTree.insert(8, 9);
		genTree.insert(8, 10);
		genTree.insert(6, 15);
		BalanceTree balanceTree=new BalanceTree();
		balanceTree.insert(true,30);
		balanceTree.setDistance(2);
		balanceTree.insert(30, 15);
		balanceTree.insert(30,9);
//		balanceTree.insert(30, 50);
		balanceTree.insert(9,17);
		balanceTree.insert(9,18);
		balanceTree.insert(9,19);
		balanceTree.insert(15,20);
		balanceTree.insert(18,22);
		balanceTree.insert(18,23);
		balanceTree.insert(23, 50);
		BalancedBinaryTree bbTree=new  BalancedBinaryTree();
		bbTree.insert(true,30);
		bbTree.setDistance(2);
		bbTree.insert(30, 15);
		bbTree.insert(30,9);
		bbTree.insert(9,17);
		bbTree.insert(9,18);
		bbTree.insert(15,20);
		bbTree.insert(18,22);
		bbTree.insert(18,23);
		bbTree.insert(15,29);
		new TreeScreen(genTree,binaryTree,balanceTree,bbTree);
	} 
}
