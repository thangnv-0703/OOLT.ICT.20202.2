package Tree.Balance;

public class BTreeNode {

//    int minDegree; // Minimum degree (defines the range for number of keys)
    private int[] keys; // An array of keys
    private BTreeNode[] Children; // An array of child pointers
    private int numKeys; // Current number of keys
    private boolean leaf; // Is true when node is leaf. Otherwise false
 
    // Constructor
    BTreeNode(int minDegree, boolean leaf) {
        this.leaf = leaf;
        this.keys = new int[2 * minDegree - 1];
        this.Children = new BTreeNode[2 * minDegree];
        this.numKeys = 0;
    }

    public int[] getKeys() {
		return keys;
	}

	public void setKeys(int[] keys) {
		this.keys = keys;
	}

	public BTreeNode[] getChildren() {
		return Children;
	}

	public void setChildren(BTreeNode[] children) {
		Children = children;
	}

	public int getNumKeys() {
		return numKeys;
	}

	public void setNumKeys(int numKeys) {
		this.numKeys = numKeys;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public int Find(int k) {
        for (int i = 0; i < this.getNumKeys(); i++) {
        	if (this.keys[i] == k) {
        		return i;
        	}
        }
        return -1;
    }
}
