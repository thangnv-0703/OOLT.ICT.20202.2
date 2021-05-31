package tree.balance;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import tree.Tree;
import tree.exception.DuplicateValue;
import tree.exception.NotExitException;
import tree.exception.UnbalancedException;
import tree.node.Node;

public class BalanceTree extends Tree {

    protected int distance;

	
    public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	private int findMin(Node x, int min) {
		if (x.isLeaf()) {
			return min <= x.getDeepthFromRoot() ? min : x.getDeepthFromRoot();
		} else {
			for (Node child: x.getChildren()) {
				int temp = findMin(child, min);
				if (temp < min) {
					min = temp;
				}
			}
		}
		return min;
	}
	
	public int getMinDeepth(Node x) {
		return findMin(x, 1000000);
	}
	  
//    @Override
//	public void insert(int parent,int value) {
//    	try {
//        	if (search(root, value) == null) {
//        		Node parentNode = search(this.root, parent);
//        		if (parentNode != null) {
//        			if (parentNode.getDeepthFromRoot() + 1 - this.getMinDeepth(this.root) <= distance) {
//        				Node childNode = new Node(value);
//        				parentNode.setChild(childNode);
//        				childNode.setParent(parentNode);
//        			}
//        			else {
//        				throw new UnbalancedException("Insert " + value + " make tree unbalanced");
//        			}
//        		} else {
//        			throw new NotExitException("No node in tree has value: " + parent);
//        		}
//        	} else {
//        		throw new DuplicateValue("Value " + value + " already exit");
//        	}
//    	} catch (Exception e) {
//			// TODO: handle exception
//    		System.out.println(e.getMessage());
//		}
//	}
    @Override
    public Timeline visualInsert(Pane drawPane,int parent, int value,Label lbCode) {
    	Timeline timeline=visualSearch(drawPane, parent,lbCode);
		Duration duration=timeline.getTotalDuration().add(Duration.seconds(1));
    	try {
        	if (search(root, value) == null) {
        		Node parentNode = search(this.root, parent);
        		if (parentNode != null) {
        			if (parentNode.getDeepthFromRoot() + 1 - this.getMinDeepth(this.root) <= distance
        					&& this.getMaxDeepth(root)-(parentNode.getDeepthFromRoot() + 1) <= distance) {
        				KeyFrame keyFrame = new KeyFrame(duration,evt-> {
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
        				throw new UnbalancedException("Insert " + value + " make tree unbalanced");
        			}
        		} else {
        			throw new NotExitException("No node in tree has value: " + parent);
        		}
        	} else {
        		throw new DuplicateValue("Value " + value + " already exit");
        	}
    	} catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
		return timeline;
	}
    @Override
	public void remove(int value) {
    	try {
        	Node x = search(root, value);
    		if (x == null) {
    			throw new NotExitException("No node in tree has value: " + value);
    		} else {
    			Node parentNode = x.getParent();
    			if (x.isLeaf()) {
    				if ( this.getMaxDeepth(this.root)-x.getDeepthFromRoot()+1<=this.distance) {
    					parentNode.getChildren().remove(x);
    				} else {
//    					find the max deep from node x
    					int distance = this.getMaxDeepth(this.root);
//    					find the node have max deep
    					Node deepestNode = this.findDeepestNode(this.root, distance);
//    					System.out.println("Remove make tree unbalanced");
    					
//    					remove similar to tree
    					deepestNode.getParent().getChildren().remove(deepestNode);
    					parentNode.getChildren().remove(x);
    					parentNode.getChildren().remove(x);
    					parentNode.setChild(deepestNode);
    					deepestNode.setParent(parentNode);
    				}
    			} else {
//    				find the max deep from node x
    				int distance = this.getMaxDeepth(this.root);
//    				find the node have max deep
    				Node deepestNode = this.findDeepestNode(this.root, distance);
    				
//    				remove similar to tree
    				deepestNode.getParent().getChildren().remove(deepestNode);
    				parentNode.getChildren().remove(x);
    				parentNode.setChild(deepestNode);
    				deepestNode.setParent(parentNode);
    				deepestNode.setChildren(x.getChildren());

//    				set children node
    				for (Node child: x.getChildren()) {
    					child.setParent(deepestNode);
    				}
    			}
    		}
    	} catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
	}
    @Override
    public Timeline visualRemove(Pane drawPane,int value,Label lbCode) {
    	Timeline timeline=visualSearch(drawPane, value,lbCode);
    	try {
        	Node x = search(root, value);
        	
    		if (x == null) {
    			throw new NotExitException("No node in tree has value: " + value);
    		} else {
    			x.changeCircle(drawPane, javafx.scene.paint.Color.WHITE, 1);
    			Duration duration=timeline.getTotalDuration().add(Duration.seconds(1));
//    			Node nodeValue = search(root, value);
    			Node parentNode = x.getParent();
    			if (x.isLeaf()) {
    				if ( this.getMaxDeepth(this.root)-x.getDeepthFromRoot()+1<=this.distance) {
    					parentNode.getChildren().remove(x);
    					KeyFrame keyFrame = new KeyFrame(duration,evt-> {
    						drawPane.getChildren().clear();
    			        	drawTree(drawPane);
    			        });
    					timeline.getKeyFrames().add(keyFrame);
    				} else {
    					KeyFrame keyFrame = new KeyFrame(duration,evt-> {
    						drawPane.getChildren().clear();
    			        	drawTree(drawPane);
    			        	Circle circle=new Circle(x.getX()+50,x.getY()+50,5);
    			        	circle.setFill(javafx.scene.paint.Color.RED);
    			        	drawPane.getChildren().add(circle);
    			        });
    					timeline.getKeyFrames().add(keyFrame);
    					Node y = this.findDeepestNode(root, this.getMaxDeepth(root));
    					ArrayList<Node>   list=new ArrayList<Node>();
    					list=searchArrayList(root,y.getValue());
    					for(int i=0;i<list.size();i++) {
    						Node node=list.get(i);
    						Duration duration1 = duration.add(Duration.seconds(i+1));
    				        keyFrame = new KeyFrame(duration1,evt-> {
    				        	node.changeColorNode(drawPane);
    				        });
    				        timeline.getKeyFrames().add(keyFrame);
    				        if(node.getValue()!=y.getValue()) {
    					        KeyFrame keyFrame1 = new KeyFrame(duration1.add(Duration.seconds(1)),evt-> {
    					        	node.changeCircle(drawPane,javafx.scene.paint.Color.WHITE,5);
    					        });
    					        timeline.getKeyFrames().add(keyFrame1);
    				        }
    					}
    					keyFrame = new KeyFrame(duration.add(Duration.seconds(list.size()+1)),evt-> {
    						y.getParent().getChildren().remove(y);
    						x.setValue(y.getValue());
    						drawPane.getChildren().clear();
    			        	drawTree(drawPane);
    			        	x.changeCircle(drawPane, javafx.scene.paint.Color.LIGHTGREEN, 5);
    			        });
    			        timeline.getKeyFrames().add(keyFrame);
    				}
    			} else {
    				KeyFrame keyFrame = new KeyFrame(duration,evt-> {
					drawPane.getChildren().clear();
		        	drawTree(drawPane);
		        	Circle circle=new Circle(x.getX()+50,x.getY()+50,5);
		        	circle.setFill(javafx.scene.paint.Color.RED);
		        	drawPane.getChildren().add(circle);
		        });
				timeline.getKeyFrames().add(keyFrame);
				Node y = this.findDeepestNode(root, this.getMaxDeepth(x));
				ArrayList<Node>   list=new ArrayList<Node>();
				list=searchArrayList(x,y.getValue());
				for(int i=1;i<list.size();i++) {
					Node node=list.get(i);
					Duration duration1 = duration.add(Duration.seconds(i));
			        keyFrame = new KeyFrame(duration1,evt-> {
			        	node.changeColorNode(drawPane);
			        });
			        timeline.getKeyFrames().add(keyFrame);
			        if(node.getValue()!=value) {
				        KeyFrame keyFrame1 = new KeyFrame(duration1.add(Duration.seconds(1)),evt-> {
				        	node.changeCircle(drawPane,javafx.scene.paint.Color.WHITE,5);
				        });
				        timeline.getKeyFrames().add(keyFrame1);
			        }
				}
				keyFrame = new KeyFrame(duration.add(Duration.seconds(list.size()+1)),evt-> {
					y.getParent().getChildren().remove(y);
//					parentNode.getChildren().remove(x);
					x.setValue(y.getValue());
//					parentNode.setChild(y);
//					y.setParent(parentNode);
//					y.setChildren(x.getChildren());
//					for(Node child: x.getChildren()) {
//						child.setParent(y);
//					}
					drawPane.getChildren().clear();
		        	drawTree(drawPane);
		        	x.changeCircle(drawPane, javafx.scene.paint.Color.LIGHTGREEN, 5);
		        });
		        timeline.getKeyFrames().add(keyFrame);
			}
    			}
    	} catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
		
		return timeline;
	}

}