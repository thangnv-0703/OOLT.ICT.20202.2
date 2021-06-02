package controller;



import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import tree.Tree;
import tree.balance.BalanceTree;
import tree.balance.BalancedBinaryTree;
import tree.binary.BinaryTree;
import tree.dialog.Dialog;
import tree.exception.NotExitException;
import tree.node.Node;

public class ScreenController {
	private Tree tree;
	private Tree treeBefore;
	private Tree treeAfter;
	private BinaryTree binaryTree;
	private Tree genTree;
	private BalanceTree balanceTree;
	private BalancedBinaryTree bbTree;
	@FXML
	private TitledPane titledPaneSetDiastance;
	@FXML
	private Label lbCode;
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
	@FXML
	private TextField tfDistance;
	@FXML
	private Button btnSetDistance;
	@FXML
	private Label lbTreeName;
	@FXML
	private Button btnUndo;
	@FXML
	private Button btnRedo;
	@FXML
	private TextArea taCode;
	private Timeline timeline;
	
	public ScreenController(Tree genTree,BinaryTree binaryTree,BalanceTree balanceTree,BalancedBinaryTree bbTree) {
		super();
		this.binaryTree = binaryTree;
		this.genTree=genTree;
		this.balanceTree=balanceTree;
		this.bbTree=bbTree;
//		this.root=root;
	}
	public ScreenController() {
		
	}
	@FXML
	void emptyButtonPressed(ActionEvent event) {
		taCode.setText("");
		if(tree instanceof BinaryTree) {
			treeBefore=new BinaryTree();
		}
		else if(tree instanceof BalancedBinaryTree) {
			treeBefore=new BalancedBinaryTree();
		}else if(tree instanceof BalanceTree) {
			treeBefore=new BalanceTree();
		}else {
			treeBefore=new Tree();
		}
		btnUndo.setDisable(false);
		treeBefore=tree.dupplicate(treeBefore, tree.getRoot());
		drawPane.getChildren().clear();
		if(tree instanceof BinaryTree) {
			this.binaryTree=new BinaryTree();
			this.tree=this.binaryTree;
		}
		else if(tree instanceof BalancedBinaryTree) {
			this.bbTree=new BalancedBinaryTree();
			this.tree=this.bbTree;
			titledPaneSetDiastance.setDisable(false);
		}else if(tree instanceof BalanceTree) {
			this.balanceTree=new BalanceTree();
			this.tree=this.balanceTree;
			titledPaneSetDiastance.setDisable(false);
		}else {
			this.genTree=new Tree();
			this.tree=this.genTree;
		}
	}
	@FXML
	void btnBalancePressed() {
		taCode.setText("");
		lbTreeName.setText("BALANCED TREE");
		btnUndo.setDisable(true);
		btnRedo.setDisable(true);
		if (balanceTree.getRoot()!=null)drawPane.getChildren().clear();
		this.tree=balanceTree;
		tree.drawTree(drawPane);
	}
	@FXML
	void btnBinaryTreePressed() {
		taCode.setText("");
		lbTreeName.setText("BINARY TREE");
		btnUndo.setDisable(true);
		btnRedo.setDisable(true);
		drawPane.getChildren().clear();
		this.tree=binaryTree;
		if (binaryTree.getRoot()!=null) binaryTree.drawTree(drawPane);
		titledPaneSetDiastance.setDisable(true);
	}
	@FXML
	void btnGenTreePressed() {
		taCode.setText("");
		lbTreeName.setText("GENERIC TREE");
		btnUndo.setDisable(true);
		btnRedo.setDisable(true);
		drawPane.getChildren().clear();
		this.tree=genTree;
		if (genTree.getRoot()!=null)genTree.drawTree(drawPane);
		titledPaneSetDiastance.setDisable(true);
	}
	@FXML
	void btnBbTreePressed() {
		taCode.setText("");
		lbTreeName.setText("BALANCED BINARY TREE");
		btnUndo.setDisable(true);
		btnRedo.setDisable(true);
		if (bbTree.getRoot()!=null)drawPane.getChildren().clear();
		this.tree=bbTree;
		tree.drawTree(drawPane);
	}
	@FXML
	void btnInsertPressed() {
		taCode.setText("");
		drawPane.getChildren().clear();
		if (tree.getRoot()!=null)tree.drawTree(drawPane);
		if(tree instanceof BinaryTree) {
			treeBefore=new BinaryTree();
		}
		else if(tree instanceof BalancedBinaryTree) {
			treeBefore=new BalancedBinaryTree();
		}else if(tree instanceof BalanceTree) {
			treeBefore=new BalanceTree();
		}else {
			treeBefore=new Tree();
		}

		btnUndo.setDisable(false);
		if (tree!=null) treeBefore=tree.dupplicate(treeBefore, tree.getRoot());
		String sParent = tfParentInsert.getText();
		String sValue = tfValueInsert.getText();
		if (sParent !="" && sValue!="" && tree.getRoot()!=null) {
			int parent=Integer.parseInt(sParent);
			int value = Integer.parseInt(sValue);
			timeline=tree.visualInsert(drawPane, parent, value,lbCode,taCode);
			timeline.play();
		}
		if (sValue!="" && tree.getRoot()==null) {
				tree.insert(true, Integer.parseInt(sValue));
				tree.drawTree(drawPane);
			}
	}

	@FXML
	void btnSearchPressed(){
		taCode.setText("");
		drawPane.getChildren().clear();
		tree.drawTree(drawPane);
		String string = tfSearch.getText();
		if (string != null) {
			int value = Integer.parseInt(string);
			timeline=tree.visualSearch(drawPane, value,lbCode,taCode);
			timeline.play();
//			search(tree.getRoot(), value);
		}
	}
	@FXML
	void btnRemovePressed() throws NotExitException{
		taCode.setText("");
		drawPane.getChildren().clear();
		tree.drawTree(drawPane);
		if(tree instanceof BinaryTree) {
			treeBefore=new BinaryTree();
		}
		else if(tree instanceof BalancedBinaryTree) {
			treeBefore=new BalancedBinaryTree();
		}else if(tree instanceof BalanceTree) {
			treeBefore=new BalanceTree();
		}else {
			treeBefore=new Tree();
		}

		btnUndo.setDisable(false);
		treeBefore=tree.dupplicate(treeBefore, tree.getRoot());
		String string = tfRemove.getText();
		if (string != null) {
			int value = Integer.parseInt(string);
			timeline=tree.visualRemove(drawPane, value,lbCode,taCode);
			timeline.play();
			
//			search(tree.getRoot(), value);
		}
		
	}
	@FXML 
	void btnTraversalPressed(){
		drawPane.getChildren().clear();
		tree.drawTree(drawPane);
		timeline=tree.visualTraversal(drawPane,taCode);
		timeline.play();
	}
	@FXML 
	void btnSetDistancePressed() {
		String distance=tfDistance.getText();
		if(distance!=null) {
			if (tree instanceof BalanceTree)
				((BalanceTree) tree).setDistance(Integer.parseInt(distance));
		}
		titledPaneSetDiastance.setDisable(true);
		new Dialog("", "Set limit distance to "+distance);
	}
	
	
	
	@FXML
	private Button btnNextStep;
	@FXML
	private Button btnStop;
	@FXML
	private Button btnPreviousStep;
	@FXML
	private Button btnPlay;
//	@FXML
//	public void nextStep() {
//		this.timeline.pause();
//		Duration duration=timeline.getCurrentTime();
////		duration= round(duration);
////		timeline.setRate(-1);
//		timeline.play();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		timeline.pause();
//	}
//	@FXML
//	public void previousStep() {
//		this.timeline.pause();
//		Duration duration=timeline.getCurrentTime().subtract(Duration.seconds(2));
//		timeline.playFrom(duration);
//		if (timeline.getCurrentTime()==duration.add(Duration.seconds(2))) timeline.pause();
//	}
	
	@FXML
	public void stop() {
		this.timeline.pause();
	}
	
	@FXML
	public void play() {
		this.timeline.play();
	}
	
	@FXML 
	void btnUndoPressed(){
		if(tree instanceof BinaryTree) {
			treeAfter=new BinaryTree();
		}
		else if(tree instanceof BalancedBinaryTree) {
			treeAfter=new BalancedBinaryTree();
		}else if(tree instanceof BalanceTree) {
			treeAfter=new BalanceTree();
		}else {
			treeAfter=new Tree();
		}
		this.treeAfter=tree.dupplicate(treeAfter,this.tree.getRoot());;

		btnUndo.setDisable(true);
		btnRedo.setDisable(false);
		if(tree instanceof BinaryTree) {
			this.binaryTree=(BinaryTree) this.treeBefore;
			this.tree=this.binaryTree;
		}
		else if(tree instanceof BalancedBinaryTree) {
			this.bbTree=(BalancedBinaryTree) this.treeBefore;
		}else if(tree instanceof BalanceTree) {
			this.balanceTree=(BalanceTree) this.treeBefore;
		}else {
			this.genTree=(Tree) this.treeBefore;
		}
		drawPane.getChildren().clear();
		treeBefore.drawTree(drawPane);
	}
	
	@FXML 
	void btnRedoPressed(){
		if(treeAfter instanceof BinaryTree) {
			this.binaryTree=(BinaryTree) this.treeAfter;
		}
		else if(tree instanceof BalancedBinaryTree) {
			this.bbTree=(BalancedBinaryTree) this.treeAfter;
		}else if(tree instanceof BalanceTree) {
			this.balanceTree=(BalanceTree) this.treeAfter;
		}else {
			this.genTree=(Tree) this.treeAfter;
		}
		btnUndo.setDisable(false);
		btnRedo.setDisable(true);
		drawPane.getChildren().clear();
		this.treeAfter.drawTree(drawPane);
	}
	
}
