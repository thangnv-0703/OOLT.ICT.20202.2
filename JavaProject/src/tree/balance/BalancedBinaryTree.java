package tree.balance;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import tree.Tree;
import tree.binary.BinaryTree;
import tree.dialog.Dialog;
import tree.exception.DuplicateValue;
import tree.exception.FullChildException;
import tree.exception.NotExitException;
import tree.exception.UnbalancedException;
import tree.node.Node;

public class BalancedBinaryTree extends BalanceTree {
//    @Override
//	public void insert(int parent,int value) {
//    	try {
//        	if (search(root, value) == null) {
//        		Node parentNode = search(this.root, parent);
//        		if (parentNode != null) {
//        			if (parentNode.getDeepthFromRoot() + 1 - this.getMinDeepth(this.root) <= distance) {
//        				if (parentNode.getChildren().size()<=1) {
//    						Node childNode = new Node(value);
//    						parentNode.setChild(childNode);
//    						childNode.setParent(parentNode);
//    					}
//    					else {
//    						new Dialog("", "Node " + parent + " has enought children");
//    						throw new FullChildException("Node " + parent + " has enought children");
//    					}
//        			}
//        			else {
//        				new Dialog("", "Insert " + value + " make tree unbalanced");
//        				throw new UnbalancedException("Insert " + value + " make tree unbalanced");
//        			}
//        		} else {
//        			new Dialog("", "No node in tree has value: " + parent);
//        			throw new NotExitException("No node in tree has value: " + parent);
//        		}
//        	} else {
//        		new Dialog("", "Value " + value + " already exit");
//        		throw new DuplicateValue("Value " + value + " already exit");
//        	}
//    	} catch (Exception e) {
//			// TODO: handle exception
//    		System.out.println(e.getMessage());
//		}
//	}
    @Override
	public Timeline visualInsert(Pane drawPane, int parent, int value, Label lbCode, TextArea taCode) {
		Timeline timeline = visualSearch(drawPane, parent, lbCode, taCode);
		Duration duration = timeline.getTotalDuration().add(Duration.seconds(1));
		try {
			if (search(root, value) == null) {
				Node parentNode = search(this.root, parent);
				if (parentNode != null) {
					if (parentNode.getDeepthFromRoot() + 1 - this.getMinDeepth(this.root) <= distance
							&& this.getMaxDeepth(root) - (parentNode.getDeepthFromRoot() + 1) <= distance) {
						if (parentNode.getChildren().size()<=1) {
							KeyFrame keyFrame = new KeyFrame(duration, evt -> {
								lbCode.setText("Inserted:" + value + " to " + parent);
								String string = taCode.getText() + "\n-Inserted:" + value + " to " + parent;
								taCode.setText(string);
								Node childNode = new Node(value);
								parentNode.setChild(childNode);
								childNode.setParent(parentNode);
								drawPane.getChildren().clear();
								drawTree(drawPane);
								Node node = search(root, value);
								node.changeColorNode(drawPane);
							});
							timeline.getKeyFrames().add(keyFrame);
						}
						else {
							KeyFrame keyFrame = new KeyFrame(duration, evt -> {
	    						new Dialog("", "Node " + parent + " has enought children");
							});
							timeline.getKeyFrames().add(keyFrame);
    						throw new FullChildException("Node " + parent + " has enought children");
    					}

					} else {
						KeyFrame keyFrame = new KeyFrame(duration, evt -> {
							new Dialog("", "Insert " + value + " make tree unbalanced");
						});
						timeline.getKeyFrames().add(keyFrame);
						throw new UnbalancedException("Insert " + value + " make tree unbalanced");
						
					}
				} else {
					KeyFrame keyFrame = new KeyFrame(duration, evt -> {

						new Dialog("", "No node in tree has value: " + parent);
					});
					timeline.getKeyFrames().add(keyFrame);
					throw new NotExitException("No node in tree has value: " + parent);
				}
			} else {
				KeyFrame keyFrame = new KeyFrame(duration, evt -> {
					new Dialog("", "Value " + value + " already exit");
				});
				timeline.getKeyFrames().add(keyFrame);
				throw new DuplicateValue("Value " + value + " already exit");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return timeline;
	}

    @Override
	public void drawTree(Pane drawPane) {
		drawBinaryTree(drawPane, 600, 30,root, 30, 600);
		
	}
	public void drawBinaryTree(Pane drawPane, double x, double y, Node node, double size, double Xbefore) {
		if (node!=root) {
			Line line = new Line(node.getParent().getX(), node.getParent().getY() + size * 10 / 9.25, x, y);
			drawPane.getChildren().add(line);
		}
		node.drawNode(drawPane,x, y,size);
		node.setX(x);
		node.setY(y);
		node.setSize(size);
		if (node.getChildren().size()>0)
		if (node.getChildren().get(0) != null)
			drawBinaryTree(drawPane, x - Xbefore / 2, y + 75, node.getChildren().get(0), (size * 9.25 / 10), Xbefore / 2);
		if (node.getChildren().size()==2)
			if (node.getChildren().get(1) != null)
			drawBinaryTree(drawPane, x + Xbefore / 2, y + 75, node.getChildren().get(1), size * 9.25 / 10, Xbefore / 2);
	}
	@Override

	 public Tree newTree() {
		 return new BalancedBinaryTree();
	 }
}
