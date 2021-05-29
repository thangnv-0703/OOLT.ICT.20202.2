package tree;

import java.util.ArrayList;

import controller.ScreenController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import tree.node.Node;

public abstract class Tree {
	protected Node root;

	public Node getRoot() {
		return root;
	}

	public Node search(Node x, int value) {
		if (x.getValue() == value) {
			return x;
		}
		if (x.getChildren().size() > 0) {
			for (Node child : x.getChildren()) {
				if (search(child, value) != null) {
					return search(child, value);
				}
			}
		}
		return null;
	}

	public void insert(int parent, int value) {
		if (search(this.root, parent) != null) {
			Node parentNode = search(this.root, parent);
			Node childNode = new Node(value);
			parentNode.setChild(childNode);
			childNode.setParent(parentNode);
		} else {
			System.out.println("No node in tree has value: " + parent);
		}
	}

	public void inserRoot(int value) {
		Node x = new Node(value);
		this.root = x;
		this.root.setParent(null);
	}

	private Node findLeftMost(Node x) {
		if (x.getChildren().size() == 0) {
			return x;
		}
		Node child = x.getChildren().get(0);
		return findLeftMost(child);
	}

	public void remove(int value) {
		if (search(root, value) == null) {
			System.out.println("Node " + value + " not in tree");
		} else {
			Node x = search(root, value);
			Node parentNode = x.getParent();
			if (x.getChildren().size() == 0) {
				parentNode.getChildren().remove(x);
			} else {
				Node y = findLeftMost(x);
				y.getParent().getChildren().remove(y);
				parentNode.getChildren().remove(x);
				parentNode.setChild(y);
				y.setParent(parentNode);
			}
		}
	}

	public void drawTree(Pane drawPane) {
		drawGenTree(drawPane, 1100, 100, root, 50, 0, 2200);
	}

	public void drawGenTree(Pane drawPane, double x, double y, Node node, double size, double beginX, double endX) {
		if (node != root) {
			Line line = new Line(node.getParent().getX(), node.getParent().getY() + size * 10 / 9.25, x, y);
//			line.setStrokeWidth(5);
			drawPane.getChildren().add(line);
		}
		node.drawNode(drawPane, x, y, size);
		node.setX(x);
		node.setY(y);
		node.setSize(size);
		double space = (endX - beginX) / (node.getChildren().size());
		for (int i = 0; i < node.getChildren().size(); i++) {
			drawGenTree(drawPane, beginX + space / 2 + space * i, y + 150, node.getChildren().get(i),
					(size * 9.25 / 10), beginX + space * i, beginX + space * i + space);
		}
	}
	public ArrayList<Node> searchArrayList(ArrayList<Node>listNodes,Node node,int value){
		if (node == null) {
			return null;
		}
		else {
			listNodes.add(node);
			if (node.getValue() == value) {
				return listNodes;
			}
			if (node.getChildren().size() > 0) {
				for (Node child : node.getChildren()) {
					if (search(child, value) != null) {
						 return searchArrayList(listNodes,child, value);
					}
				}
			}}
		return null;
	}
	public Timeline visualSearch(Pane drawPane,int value) {
		Timeline timeline=new Timeline();
		ArrayList<Node>   list=new ArrayList<Node>();
		list=searchArrayList(list, root, value);
//		if (list!=null)
		for(int i=0;i<list.size();i++) {
			Node node=list.get(i);
			Duration duration = Duration.seconds(i);
	        KeyFrame keyFrame = new KeyFrame(duration,evt-> {
	        	node.changeColorNode(drawPane);
	        });
	        timeline.getKeyFrames().add(keyFrame);
	        if(node.getValue()!=value) {
		        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(i+1),evt-> {
		        	node.changeCircleStroke(drawPane);
		        });
		        timeline.getKeyFrames().add(keyFrame1);
	        }
		}
		timeline.autoReverseProperty().set(false);
		timeline.cycleCountProperty().set(1);
		return timeline;
	}
	public ArrayList<Node> traversalArrayList(ArrayList<Node>listNodes,Node node){
		if (node == null) {
			return null;
		}
		else {
			listNodes.add(node);
			for (Node child: node.getChildren()) {
			traversalArrayList(listNodes, child);
		}}
		return listNodes;
	}
	public Timeline visualTraversal(Pane drawPane) {
		Timeline timeline=new Timeline();
		ArrayList<Node>   list=new ArrayList<Node>();
		list=traversalArrayList(list, root);
		for(int i=0;i<list.size();i++) {
			Node node=list.get(i);
			Duration duration = Duration.seconds(i);
	        KeyFrame keyFrame = new KeyFrame(duration,evt-> {
	        	node.changeColorNode(drawPane);
	        });
	        timeline.getKeyFrames().add(keyFrame);
		        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(i+1),evt-> {
		        	node.changeCircleStroke(drawPane);
		        });
		        timeline.getKeyFrames().add(keyFrame1);
		}
		timeline.autoReverseProperty().set(false);
		timeline.cycleCountProperty().set(1);
		return timeline;
	}

}
