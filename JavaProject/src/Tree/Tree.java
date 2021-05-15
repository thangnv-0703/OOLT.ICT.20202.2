package Tree;

public abstract class Tree {

	protected Node root;
	
	
	public abstract void add(int value);                    
	
	public abstract boolean search (int value);
	
	public abstract void delete(int value);
	
	public abstract void update (Node current, int value);
	
	// Depth-First Search
	public abstract void traverseInOrder(Node node);
	
	public abstract void traversePreOrder(Node node);
	
	public abstract void traversePostOrder(Node node);
	
	//Breadth-First Search
	public abstract void traverseLevelOrder();
	
	
}
