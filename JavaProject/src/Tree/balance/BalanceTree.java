package tree.balance;

import javafx.scene.layout.Pane;
import tree.Tree;
import tree.node.Node;

public class BalanceTree extends Tree {

    protected int distance;
    

    public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int calDepth(Node node) {
		int count=0;
		while(node.getParent()!=null){
			count++;
			node=node.getParent();
		}
		return count;
	}

	private  int maxLevel=-1;
	void findMaxDepth(Node node, int level)
    {
        if (node != null)
        
        	level++;
            if (level > maxLevel)
            {
                maxLevel = level;
            }
 
            for (Node child: node.getChildren()) {
				findMaxDepth(child, level);
			}
        }
    public  int getMaxDepth()
    {
        findMaxDepth(root, 0);
        return	maxLevel;
    }
    
    private  int minLevel=1000;
	void findMinDepth(Node node, int level)
    {
        if (node != null)
         
        	level++;
            if (level < minLevel && node.getChildren().size()==0)
            {
                minLevel = level;
            }
 
            for (Node child: node.getChildren()) {
				findMinDepth(child, level);
			}
        }
    public  int getMinDepth()
    {
        findMinDepth(root, 0);
        return	minLevel;
    }
    
    @Override
	public void insert(int parent,int value) {
		Node parentNode = search(this.root, parent);
		if (parentNode != null) {
			Node childNode = new Node(value);
			parentNode.setChild(childNode);
			childNode.setParent(parentNode);
			if (getMaxDepth()-getMinDepth()<=distance) {}
			else {
				remove(value);
				System.out.println("canot insert this value ");
			}
		} else {
			System.out.println("No node in tree has value: " + parent);
		}
	}

	@Override
	public void drawTree(Pane drawPane) {
		// TODO Auto-generated method stub
		
	}


}