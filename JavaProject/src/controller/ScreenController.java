package controller;



import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tree.Tree;
import tree.balance.BalanceTree;
import tree.balance.BinaryBalanceTree;
import tree.binary.BinaryTree;
import tree.node.Node;

public class ScreenController {
	private Tree tree;
	private BinaryTree binaryTree;
	private Tree genTree;
	private BalanceTree balanceTree;
	private BinaryBalanceTree bbTree;
	@FXML
	private Button btnBinaryTree;
	@FXML
	private Button btnGenTree;
	@FXML
	private Button btnBalanceTree;
	@FXML
	private Button btnBbTree;
	@FXML
	private Node root;
	@FXML
	private Pane drawPane;
	@FXML
	private TextField tfParentInsert;
	@FXML
	private TextField tfValueInsert;
	@FXML
	private Button btnInsert;
	@FXML
	private TextField tfSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private TextField tfRemove;
	@FXML
	private Button btnRemove;
	private Timeline timeline;
	
	public ScreenController(Tree genTree,BinaryTree binaryTree,BalanceTree balanceTree) {
		super();
		this.binaryTree = binaryTree;
		this.genTree=genTree;
		this.balanceTree=balanceTree;
//		this.root=root;
	}
	public ScreenController() {
		
	}
	@FXML
	void emptyButtonPressed(ActionEvent event) {
		drawPane.getChildren().clear();
		tree=null;
	}
	@FXML
	void btnBalancePressed() {
		drawPane.getChildren().clear();
		this.tree=balanceTree;
		tree.drawTree(drawPane);
	}
	@FXML
	void btnBinaryTreePressed() {
		drawPane.getChildren().clear();
		this.tree=binaryTree;
		binaryTree.drawTree(drawPane);
	}
	@FXML
	void btnGenTreePressed() {
		drawPane.getChildren().clear();
		this.tree=genTree;
		genTree.drawTree(drawPane);
	}
	@FXML
	void btnInsertPressed() {
		String sParent = tfParentInsert.getText();
		String sValue = tfValueInsert.getText();
		if (sParent != null	  && sValue!=null) {
			int parent=Integer.parseInt(sParent);
			int value = Integer.parseInt(sValue);
			this.tree.insert(parent, value);;
			drawPane.getChildren().clear();
			tree.drawTree(drawPane);
		}
	}

	@FXML
	void btnSearchPressed(){
		
		String string = tfSearch.getText();
		if (string != null) {
			int value = Integer.parseInt(string);
			timeline=tree.visualSearch(drawPane, value);
			timeline.play();
//			search(tree.getRoot(), value);
		}
	}
	@FXML 
	void btnTraversalPressed(){
		timeline=tree.visualTraversal(drawPane);
		timeline.play();
	}
	
	
	
	
	@FXML
	private Button btnNextStep;
	@FXML
	private Button btnStop;
	@FXML
	private Button btnPreviousStep;
	@FXML
	private Button btnPlay;
	@FXML
	public void nextStep() {
		this.timeline.pause();
		Duration duration=timeline.getCurrentTime();
		this.timeline.play();
		this.timeline.pause();
	}
	@FXML
	public void previousStep() {
		
	}
	
	@FXML
	public void stop() {
		this.timeline.pause();
	}
	
	@FXML
	public void play() {
		this.timeline.play();
	}

	
}
