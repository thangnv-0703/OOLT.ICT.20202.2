package controller;
import javafx.fxml.FXMLLoader;



import javafx.scene.Parent;
import javafx.scene.Scene;
import tree.Tree;
import tree.balance.BalanceTree;
import tree.binary.BinaryTree;
import tree.node.Node;

import java.io.IOException;
import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class TreeScreen extends JFrame {
	private Node root;
	BinaryTree binaryTree;
	Tree genTree;
	BalanceTree balanceTree;
	public TreeScreen(Tree genTree,BinaryTree binaryTree,BalanceTree balanceTree) {
		// TODO Auto-generated constructor stub
		super();
		this.binaryTree=binaryTree;
		this.genTree=genTree;
		this.balanceTree=balanceTree;
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		this.setSize(3048, 1500);
		this.setTitle("Tree");

		this.setVisible(true);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/view/screen.fxml"));
					ScreenController controller = new ScreenController(genTree,binaryTree,balanceTree);
					loader.setController(controller);
					Parent root = loader.load();
					fxPanel.setScene(new Scene(root));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}