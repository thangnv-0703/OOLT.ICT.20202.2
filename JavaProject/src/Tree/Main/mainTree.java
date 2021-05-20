package Tree.Main;

import Tree.Binary.BinaryTree;
import Tree.Screen.TreeScreen;

public class mainTree {
	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
//		binaryTree.add(50);
//		binaryTree.add(40);
//		binaryTree.add(30);
//		binaryTree.add(20);
		binaryTree.add(13);
		binaryTree.add(9);
		binaryTree.add(15);
		binaryTree.add(7);
		binaryTree.add(8);
		binaryTree.add(19);
		binaryTree.add(14);
		binaryTree.add(1);
		binaryTree.add(10);

		binaryTree.add(16);
		new TreeScreen(binaryTree);
	} 
}
