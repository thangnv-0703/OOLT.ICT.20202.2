package tree;


import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import tree.dialog.Dialog;
import tree.exception.DuplicateValue;
import tree.exception.NotExitException;
import tree.node.Node;

public class Tree {
	protected Node root;

	public Node getRoot() {
		return root;
	}

	private int findMax(Node x, int max) {
		if (x.isLeaf()) {
			return max > x.getDeepthFromRoot() ? max : x.getDeepthFromRoot();
//			return 0;
		} else {
			for (Node child : x.getChildren()) {
				int temp = findMax(child, max);
				if (temp > max) {
					max = temp;
				}
			}
		}
		return max;
	}

	public Node findDeepestNode(Node x, int distance) {
		if (x.isLeaf()) {
			return x.getDeepthFromRoot() == distance ? x : null;
		} else {
			for (Node child : x.getChildren()) {
				Node y = findDeepestNode(child, distance);
				if (y != null) {
					return y;
				}
			}
		}
		return null;
	}

	public int getMaxDeepth(Node x) {
		return findMax(x, -1000000);
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
		try {
			if (this.search(this.root, value) == null) {
				if (search(this.root, parent) != null) {
					Node parentNode = search(this.root, parent);
					Node childNode = new Node(value);
					parentNode.setChild(childNode);
					childNode.setParent(parentNode);
				} else {
					new Dialog("", "No node in tree has value: " + parent);
					throw new NotExitException("No node in tree has value: " + parent);
				}
			} else {
				new Dialog("", "Value " + value + " already exit");
				throw new DuplicateValue("Value " + value + " already exit");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public void insert(boolean isRoot, int value) {
		if (isRoot) {
			this.root = new Node(value);
			this.root.setParent(null);
		}
	}

	public void remove(int value) throws NotExitException {
		try {
			if (search(root, value) == null) {
				new Dialog("","No node in tree has value: " + value);
				throw new NotExitException("No node in tree has value: " + value);
			} else {
				Node x = search(root, value);
				Node parentNode = x.getParent();
				if (x.getChildren().size() == 0) {
					parentNode.getChildren().remove(x);
				} else {
					Node y = this.findDeepestNode(this.root, this.getMaxDeepth(this.root));
					y.getParent().getChildren().remove(y);
					parentNode.getChildren().remove(x);
					parentNode.setChild(y);
					y.setParent(parentNode);
					y.setChildren(x.getChildren());
					for (Node child : x.getChildren()) {
						child.setParent(y);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public void traverse(Node x) {
		if (x == null) {
			return;
		}
		if (x.getParent() != null) {
			System.out.println(x.getValue() + "-" + x.getParent().getValue());
		} else {
			System.out.println(x.getValue());
		}
		for (Node child : x.getChildren()) {
			traverse(child);
		}
	}

	public void drawTree(Pane drawPane) {
		if (this != null)
			drawGenTree(drawPane, 600, 30, root, 30, 0, 1200);
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
			drawGenTree(drawPane, beginX + space / 2 + space * i, y + 100, node.getChildren().get(i),
					(size * 9.25 / 10), beginX + space * i, beginX + space * i + space);
		}
	}

//	public ArrayList<Node> searchArrayList(ArrayList<Node>listNodes,Node node,int value){
//		if (node == null) {
//			return null;
//		}
//		else {
//			listNodes.add(node);
//			if (node.getValue() == value) {
//				return listNodes;
//			}
//			if (node.getChildren().size() > 0) {
//				for (Node child : node.getChildren()) {
//					if (search(child, value) != null) {
//						 return searchArrayList(listNodes,child, value);
//					}
//				}
//			}}
//		return null;
//	}
	public ArrayList<Node> searchArrayList(Node x, int value) {
		ArrayList<Node> list1 = new ArrayList<Node>();
		list1 = traversalArrayList(list1, x);
		ArrayList<Node> list2 = new ArrayList<Node>();
		for (Node node : list1) {
			list2.add(node);
			if (node.getValue() == value)
				return list2;
		}
		return list2;
	}

	public Timeline visualSearch(Pane drawPane, int value, Label lbCode, TextArea taCode) {
		Timeline timeline = new Timeline();
		boolean check=false;
		ArrayList<Node> list = new ArrayList<Node>();
		list = searchArrayList(root, value);
//		if (list!=null)
		for (int i = 0; i < list.size(); i++) {
			Node node = list.get(i);
			String string = "Searching for Node:" + value;
			taCode.setText("");
			taCode.setText(string);
			if (i % 3 == 0)
				string = string + ".";
			if (i % 3 == 1)
				string = string + "..";
			if (i % 3 == 2)
				string = string + "...";
			String s = string;
			Duration duration = Duration.seconds(i);
			KeyFrame keyFrame = new KeyFrame(duration, evt -> {
				node.changeColorNode(drawPane);
				lbCode.setText(s);
				lbCode.setVisible(true);
			});
			timeline.getKeyFrames().add(keyFrame);
			if (node.getValue() != value) {
				KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(i + 1), evt -> {
					node.changeCircle(drawPane, javafx.scene.paint.Color.WHITE, 2);
				});
				timeline.getKeyFrames().add(keyFrame1);
			}
			else {
				check=true;
				KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(i + 1), evt -> {
					String string2 = taCode.getText() + "\n-Searched Node :"+value;
					taCode.setText(string2);
					lbCode.setText("Searched Node :"+value);
				});
				timeline.getKeyFrames().add(keyFrame1);
			}
		}
		timeline.autoReverseProperty().set(false);
		timeline.cycleCountProperty().set(1);
		if (check==false) {
			KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(list.size() + 1), evt -> {
				String string2 = taCode.getText() + "\n-Cannot Find :"+value;
				taCode.setText(string2);
				lbCode.setText("Cannot Find :"+value);
				new Dialog("","No node in tree has value: " + value);
			});
			timeline.getKeyFrames().add(keyFrame1);
		}
		return timeline;
	}

	public ArrayList<Node> traversalArrayList(ArrayList<Node> listNodes, Node node) {
		if (node == null) {
			return null;
		} else {
			listNodes.add(node);
			for (Node child : node.getChildren()) {
				traversalArrayList(listNodes, child);
			}
		}
		return listNodes;
	}

	public Timeline visualTraversal(Pane drawPane, TextArea taCode) {
		Timeline timeline = new Timeline();
		ArrayList<Node> list = new ArrayList<Node>();
		list = traversalArrayList(list, root);
		int n = list.size();
		for (int i = 0; i < list.size(); i++) {
			Node node = list.get(i);
			Duration duration = Duration.seconds(i);
			int j = i;
			String string = "Traversing";
			if (i % 3 == 0)
				string = string + ".";
			if (i % 3 == 1)
				string = string + "..";
			if (i % 3 == 2)
				string = string + "...";
			String s = string;
			KeyFrame keyFrame = new KeyFrame(duration, evt -> {
				node.changeColorNode(drawPane);
				taCode.setText(s);
			});
			Node nodeResult = new Node(node.getValue());
			KeyFrame keyFrame2;
			if (n < 11)
				keyFrame2 = new KeyFrame(duration, evt -> {
					nodeResult.drawNode(drawPane, 100 + 100 * j, 600, 25);
				});

			else {
				if (i < 11)
					keyFrame2 = new KeyFrame(duration, evt -> {
						nodeResult.drawNode(drawPane, 100 + 100 * j, 550, 25);
					});
				else
					keyFrame2 = new KeyFrame(duration, evt -> {
						nodeResult.drawNode(drawPane, 100 * (j-10), 600, 25);
					});
			}
			timeline.getKeyFrames().add(keyFrame);
			timeline.getKeyFrames().add(keyFrame2);
			KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(i + 1), evt -> {
				node.changeCircle(drawPane, javafx.scene.paint.Color.WHITE, 2);
			});
			timeline.getKeyFrames().add(keyFrame1);
		}
		timeline.autoReverseProperty().set(false);
		timeline.cycleCountProperty().set(1);
		return timeline;
	}

	public Timeline visualInsert(Pane drawPane, int parent, int value, Label lbCode, TextArea taCode) {
		Timeline timeline = visualSearch(drawPane, parent, lbCode, taCode);
		Duration duration = timeline.getTotalDuration().add(Duration.seconds(1));
		KeyFrame keyFrame = new KeyFrame(duration, evt -> {
			lbCode.setText("Insert " + value + " to " + parent);
			String string = taCode.getText() + "\n-Insert " + value + " to " + parent;
			taCode.setText(string);
			insert(parent, value);
			drawPane.getChildren().clear();
			drawTree(drawPane);
			Node node = search(root, value);
			node.changeColorNode(drawPane);
		});
		timeline.getKeyFrames().add(keyFrame);
		return timeline;
	}

//	public Timeline visualRemove(Pane drawPane, int value, Label lbCode, TextArea taCode) throws NotExitException{
//		Timeline timeline = visualSearch(drawPane, value, lbCode, taCode);
//		Duration duration = timeline.getTotalDuration().add(Duration.seconds(1));
//		if (search(root, value) != null) {
//			Node x = search(root, value);
//			Node parentNode = x.getParent();
//			if (x.isLeaf()) {
//				parentNode.getChildren().remove(x);
//				KeyFrame keyFrame = new KeyFrame(duration, evt -> {
//					lbCode.setText("Remove value: " + value);
//					String string = taCode.getText() + "\n-Remove value: " + value;
//					taCode.setText(string);
//					drawPane.getChildren().clear();
//					drawTree(drawPane);
//				});
//				timeline.getKeyFrames().add(keyFrame);
//			} else {
//				KeyFrame keyFrame = new KeyFrame(duration, evt -> {
//					lbCode.setText("Find the deepest Node\n   of :" + value);
//					String string = taCode.getText() + "\n-Find the deepest Node\n   of :" + value;
//					taCode.setText(string);
//					drawPane.getChildren().clear();
//					drawTree(drawPane);
//					Circle circle = new Circle(x.getX() + 25, x.getY() + 25, 2);
//					circle.setFill(javafx.scene.paint.Color.RED);
//					drawPane.getChildren().add(circle);
//				});
//				timeline.getKeyFrames().add(keyFrame);
//				Node y = this.findDeepestNode(x, this.getMaxDeepth(x));
//				ArrayList<Node> list = new ArrayList<Node>();
//				list = searchArrayList(x, y.getValue());
//				for (int i = 1; i < list.size(); i++) {
//					Node node = list.get(i);
//					Duration duration1 = duration.add(Duration.seconds(i));
//					keyFrame = new KeyFrame(duration1, evt -> {
//						node.changeColorNode(drawPane);
//					});
//					timeline.getKeyFrames().add(keyFrame);
//					if (node.getValue() != y.getValue()) {
//						KeyFrame keyFrame1 = new KeyFrame(duration1.add(Duration.seconds(1)), evt -> {
//							node.changeCircle(drawPane, javafx.scene.paint.Color.WHITE, 2);
//						});
//						timeline.getKeyFrames().add(keyFrame1);
//					}
//				}
//
//				keyFrame = new KeyFrame(duration.add(Duration.seconds(list.size() + 1)), evt -> {
//					lbCode.setText("Replace :" + value + " by " + y.getValue());
//					String string = taCode.getText() + "\n-The Deepest Node :" + y.getValue() + "\n-eplace :" + value
//							+ " by " + y.getValue();
//					taCode.setText(string);
//					y.getParent().getChildren().remove(y);
//					x.setValue(y.getValue());
//					drawPane.getChildren().clear();
//					drawTree(drawPane);
//					x.changeCircle(drawPane, javafx.scene.paint.Color.LIGHTGREEN, 2);
//				});
//				timeline.getKeyFrames().add(keyFrame);
//			}
//		} else {
//			new Dialog("","No node in tree has value: " + value);
//			throw new NotExitException("No node in tree has value: " + value);
//		}
//		return timeline;
//	}

	public Timeline visualRemove(Pane drawPane, int value, Label lbCode, TextArea taCode) throws NotExitException {
		Timeline timeline = visualSearch(drawPane, value, lbCode, taCode);
		Duration duration = timeline.getTotalDuration().add(Duration.seconds(1));
		Node x = search(root, value);
		if(x!=null) {Node parentNode = x.getParent();
		if (x.getChildren().size() == 0) {
			
			parentNode.getChildren().remove(x);
			KeyFrame keyFrame = new KeyFrame(duration, evt -> {
				lbCode.setText("Remove value: " + value);
				String string = taCode.getText() + "\n-Remove value: " + value;
				taCode.setText(string);
				drawPane.getChildren().clear();
				drawTree(drawPane);
			});
			timeline.getKeyFrames().add(keyFrame);
		} else {
			KeyFrame keyFrame = new KeyFrame(duration, evt -> {
				lbCode.setText("Find the deepest Node\n   of :" + value);
				String string = taCode.getText() + "\n-Find the deepest Node\n   of :" + value;
				taCode.setText(string);
				drawPane.getChildren().clear();
				drawTree(drawPane);
				Circle circle = new Circle(x.getX() + 25, x.getY() + 25, 2);
				circle.setFill(javafx.scene.paint.Color.RED);
				drawPane.getChildren().add(circle);
			});
			timeline.getKeyFrames().add(keyFrame);
			Node y = this.findDeepestNode(x, this.getMaxDeepth(x));
			ArrayList<Node> list = new ArrayList<Node>();
			list = searchArrayList(x, y.getValue());
			for (int i = 1; i < list.size(); i++) {
				Node node = list.get(i);
				Duration duration1 = duration.add(Duration.seconds(i));
				keyFrame = new KeyFrame(duration1, evt -> {
					node.changeColorNode(drawPane);
				});
				timeline.getKeyFrames().add(keyFrame);
				if (node.getValue() != y.getValue()) {
					KeyFrame keyFrame1 = new KeyFrame(duration1.add(Duration.seconds(1)), evt -> {
						node.changeCircle(drawPane, javafx.scene.paint.Color.WHITE, 2);
					});
					timeline.getKeyFrames().add(keyFrame1);
				}
			}

			keyFrame = new KeyFrame(duration.add(Duration.seconds(list.size() + 1)), evt -> {
				lbCode.setText("Replace :" + value + " by " + y.getValue());
				String string = taCode.getText() + "\n-The Deepest Node :" + y.getValue() + "\n-eplace :" + value
						+ " by " + y.getValue();
				taCode.setText(string);
				y.getParent().getChildren().remove(y);
				x.setValue(y.getValue());
				drawPane.getChildren().clear();
				drawTree(drawPane);
				x.changeCircle(drawPane, javafx.scene.paint.Color.LIGHTGREEN, 2);
			});
			timeline.getKeyFrames().add(keyFrame);
		}} 
		
		return timeline;
	}
	public Tree dupplicate(Tree treeDup, Node node) {
		if (node == null) {
			return null;
		} else {
			if (node.getParent() == null)
				treeDup.insert(true, node.getValue());
			else
				treeDup.insert(node.getParent().getValue(), node.getValue());
			for (Node child : node.getChildren()) {
				dupplicate(treeDup, child);
			}
		}
		return treeDup;
		
	}
}
