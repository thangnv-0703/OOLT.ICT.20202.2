package tree.balance;

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




//	public BalanceTree() {
//        super();
//        this.distance = 1;  //Default value
//        this.minLeaf = 1000000; // this is initialize for
//        this.maxLeaf = -1;        // a tree with only root
//    }
//
//
//
//    @Override
//    public Node insert(int parentValue, int newNode) throws TreeException {
//        boolean isParentInTree = isInTree(root, parentValue);
//        if (isParentInTree) {
//            Node foundParentNode = searchNode(root, parentValue);
//            if (foundParentNode.getDepth() + 1 - this.minLeafDepth <= this.limitDistance) {
//                newNode.setDepth(foundParentNode.getDepth() + 1);
//                foundParentNode.children.add(newNode);
//                updateMaxMin(this.root);
//                return newNode;
//            } else {
//                throw new TreeException("Cannot insert! The node make the tree imbalanced");
//            }
//        } else {
//            throw new TreeException("Cannot findMaxDepth node with value " + parentValue);
//        }
//    }
//
//    public void updateMaxMin(Node root) {
//        if (root.getNbChildren() == 0 && root != this.root) {
//            if (root.getDepth() < this.minLeafDepth)
//                this.minLeafDepth = root.getDepth();
//            if (root.getDepth() > this.MaxLeafDepth)
//                this.MaxLeafDepth = root.getDepth();
//        }
//        for (Node child : root.children)
//            updateMaxMin(child);
//    }
//
//}
}