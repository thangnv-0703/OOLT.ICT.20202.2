package Tree.Screen;



import java.sql.Time;
import java.util.concurrent.TimeUnit;

import Tree.Node;
import Tree.Binary.BinaryTree;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
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

public class ScreenController {
	@FXML
	private BinaryTree binaryTree;
	@FXML
	private Button btnBinaryTree;
	@FXML
	private Node root;
	@FXML
	private Pane drawPane;
	@FXML
	private TextField tfInsert;
	@FXML
	private Button btnInsert;
	@FXML
	private TextField tfSearch;
	@FXML
	private Button btnSearch;
	private Thread animationThread;
	public ScreenController(BinaryTree binaryTree) {
		super();
		this.binaryTree=binaryTree;
//		this.root=root;
	}
	@FXML
	 void emptyButtonPressed(ActionEvent event) {
	    	drawPane.getChildren().clear();
	    }
	@FXML
	void btnBinaryTreePressed() {
		drawNodeRecursive(1200, 100, 1200, 100, binaryTree.root,50,1000);
	}
	public void drawNodeRecursive(double x1,double y1,double x,double y,Node node,double size,double Xbefore){
//	    Line line = new Line(x1, y1, x, y);
//	  
//	    drawPane.getChildren().add(line);
//	    Circle circle = new Circle(x,y, size, Paint.valueOf("white"));
//	    circle.setStroke(javafx.scene.paint.Color.BLACK);
//	    circle.setFill(javafx.scene.paint.Color.WHITE);
//	    drawPane.getChildren().add(circle);
//	    Text txt = new Text(x-size/5,y, String.valueOf(node.getValue()));
//	    drawPane.getChildren().add(txt);
//	    if(node.getLeft() != null)
//	        drawNodeRecursive(x,y,x-(size*size*size*size/25000),y+100,node.getLeft(),(size*9.25/10));
//	    if(node.getRight() != null)
//	        drawNodeRecursive(x,y,x+(size*size*size*size/25000),y+100,node.getRight(),size*9.25/10);
		Line line = new Line(x1, y1+size*10/9.25, x, y);
		  
	    drawPane.getChildren().add(line);
	    Circle circle = new Circle(x,y, size, Paint.valueOf("white"));
	    circle.setStroke(javafx.scene.paint.Color.BLACK);
	    circle.setFill(javafx.scene.paint.Color.WHITE);
	    drawPane.getChildren().add(circle);
	    Text txt = new Text(x-size/5,y, String.valueOf(node.getValue()));
	    drawPane.getChildren().add(txt);
	    if(node.getLeft() != null)
	        drawNodeRecursive(x,y,x-Xbefore/2,y+150,node.getLeft(),(size*9.25/10),Xbefore/2);
	    if(node.getRight() != null)
	        drawNodeRecursive(x,y,x+Xbefore/2,y+150,node.getRight(),size*9.25/10,Xbefore/2);
	}
	@FXML
	void btnInsertPressed() {
		String string=tfInsert.getText();
		if (string!=null){
			int value=Integer.parseInt(string);
			this.binaryTree.add(value);
			drawPane.getChildren().clear();
			drawNodeRecursive(1200, 100, 1200, 100, binaryTree.root,50,1000);
			}
	}
	@FXML
	void btnSearchPressed() {
		drawNodeRecursive(1200, 100, 1200, 100, binaryTree.root,50,1000);
		String string=tfSearch.getText();
		if (string!=null){
			int value=Integer.parseInt(string);
//			this.binaryTree.add(value);
			containsNodeRecursive(binaryTree.root, value,1200, 100, 1200, 100,50,1000);
			}
	}
	 boolean containsNodeRecursive(Node node, int value,double x1,double y1,double x,double y,double size,double Xbefore) {
		Line line = new Line(x1, y1+size*10/9.25, x, y);
		line.setFill(Color.GREEN);  
		line.setStrokeWidth(5);
	    drawPane.getChildren().add(line);
	    Circle circle = new Circle(x,y, size, Paint.valueOf("white"));
	    circle.setStroke(javafx.scene.paint.Color.BLACK);
	    circle.setStrokeWidth(5);
	    circle.setFill(javafx.scene.paint.Color.LIGHTGREEN);
	    drawPane.getChildren().add(circle);
	    Text txt = new Text(x-size/5,y, String.valueOf(node.getValue()));
	    txt.setFill(Color.BLACK);
	    drawPane.getChildren().add(txt);
	    
//	    try {
//                Thread.sleep(500);
//                Line line = new Line(x1, y1+10, x, y);
//        		line.setFill(Color.BLUE);  
//        	    drawPane.getChildren().add(line);
//        	    Circle circle = new Circle(x,y, size, Paint.valueOf("white"));
//        	    circle.setStroke(javafx.scene.paint.Color.BLUE);
//        	    circle.setFill(javafx.scene.paint.Color.BLUE);
//        	    drawPane.getChildren().add(circle);
//        	    Text txt = new Text(x-size/5,y, String.valueOf(node.getValue()));
//        	    txt.setFill(Color.WHITE);
//        	    drawPane.getChildren().add(txt);
//        
//        } catch (InterruptedException ie)
//        {
//            Thread.currentThread().interrupt();
//        }
//	    animationThread=new Thread(new Runnable() {
//	    	@Override
//	    	public void run() {
//	    		try {
//	    	        Thread.sleep(2000);
//	    	    } catch (InterruptedException ie) {
//	    	        Thread.currentThread().interrupt();
//	    	    }
//	    	}});

	    
//	    Animation timeline = null;
//		timeline.setDelay( Duration.millis(  100 ) );
	    if (node.getValue()==value) return true;
	    else
	    if(value < node.getValue()) {
	    	if (node.getLeft()==null) return false;
	    	
	    	containsNodeRecursive(node.getLeft(),value,x,y,x-Xbefore/2,y+150,(size*9.25/10),Xbefore/2);
	    }
	    else	{
	    	if (node.getRight()==null) return false;
	    	containsNodeRecursive(node.getRight(),value,x,y,x+Xbefore/2,y+150,size*9.25/10,Xbefore/2);
	    }
		return false;
	}
}
	
