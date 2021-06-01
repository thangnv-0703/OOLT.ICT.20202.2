package tree.node;


import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Node {
	private int value;
	private Node parent;
	ArrayList<Node> children = new ArrayList<Node>();
	private double x;
	private double y;
	private double size;
	private Circle circle;
			
	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public boolean isLeaf() {
		return this.getChildren().size() == 0 ? true : false;
	}
			
	public int getDeepthFromRoot() {
		int count = 0;
		Node x = this;
		while (x.getParent() != null) {
			x = x.getParent();
			count++;
		}
		return count;
	}
	
	public Node(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	public void setChild(Node x) {
		this.children.add(x);
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public void drawNode(Pane drawPane, double x, double y, double size) {
		Circle circle = new Circle(x, y, size);
		circle.setStroke(javafx.scene.paint.Color.BLACK);
		circle.setFill(javafx.scene.paint.Color.WHITE);
//		circle.setStrokeWidth(5);
		drawPane.getChildren().add(circle);
		Text txt = new Text(x - size / 5, y, String.valueOf(getValue()));
		drawPane.getChildren().add(txt);
	}
	public void changeColorNode(Pane drawPane) {
		if (this.getParent()!=null) {
			Line line = new Line(getParent().getX(), getParent().getY() + size * 10 / 9.25, x, y);
			line.setStrokeWidth(5);
//			line.setStroke(Color.LIGHTGREEN);
			drawPane.getChildren().add(line);
		}
		changeCircle(drawPane,javafx.scene.paint.Color.LIGHTGREEN, 5);
//		Circle circle = new Circle(x, y, size);
//		circle.setStroke(javafx.scene.paint.Color.BLACK);
//		circle.setFill(javafx.scene.paint.Color.LIGHTGREEN);
//		circle.setStrokeWidth(5);
//		drawPane.getChildren().add(circle);
//		Text txt = new Text(x - size / 5, y, String.valueOf(getValue()));
//		drawPane.getChildren().add(txt);
	}
	
//	public void changeCircleStroke(Pane drawPane) {
////		Circle circle = new Circle(x, y, size);
////		circle.setStroke(javafx.scene.paint.Color.BLACK);
////		circle.setFill(javafx.scene.paint.Color.WHITE);
////		circle.setStrokeWidth(5);
////		Text txt = new Text(x - size / 5, y, String.valueOf(getValue()));
////		drawPane.getChildren().add(circle);
////		drawPane.getChildren().add(txt);
//	}
	public void changeCircle(Pane drawPane,Paint string,int strokeWidth) {
		Circle circle = new Circle(x, y, size);
		circle.setStroke(javafx.scene.paint.Color.BLACK);
		circle.setFill(string);
		circle.setStrokeWidth(strokeWidth);
		Text txt = new Text(x - size / 5, y, String.valueOf(getValue()));
		drawPane.getChildren().add(circle);
		drawPane.getChildren().add(txt);
	}
}
