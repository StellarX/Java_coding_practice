package algorithm.tree;

public class Bst {

	static class Node{	
		int key;
		Node lc, rc;
		public Node(int k) { this.key = k; }
	}
	public static int lastVisited = -99999;
	
	public static void main(String[] args) {
		int[] a = {3,2,4,7,6,8};
		Node node = createBst(a);
//		System.out.println(searchBstNode(node, 71));
//		System.out.println(searchBstWithParentNode(node, 1, null).key);
		dispBst(node);
//		inOrder(node);
		searchPath(node, 0);
		 
//		System.out.print("\n");
//		dispBst(node);
	}
	

	
	
	public static Node createBst(int[] a) {
		Node node = null;
		for(int i = 0; i < a.length; i++) {
			node = insertBst(node, a[i]);
		}
		return node;
	}
	public static Node insertBst(Node node, int key) {
		if(node == null) {
			node = new Node(key);
			node.lc = null;
			node.rc = null;
			return node;
		}
		if(key < node.key) node.lc = insertBst(node.lc, key);
		else node.rc = insertBst(node.rc, key);
		return node;
	}
	public static void dispBst(Node node) {
		if(node != null) {
			System.out.print(node.key);
			if(node.rc != null || node.lc != null) {
				System.out.print("(");
				if(node.lc != null)
					dispBst(node.lc);
				if(node.rc != null) {
					System.out.print(",");
					dispBst(node.rc);
				}
				System.out.print(")");
			}
		}
	}
	public static Node searchBstNode(Node node, int key) {
		if(node != null) {
			if(node.key == key) return node;
			if(node.key > key) return searchBstNode(node.lc, key);
			else return searchBstNode(node.rc, key);
		}
		return null;
	}
	public static Node searchBstWithParentNode(Node node, int key, Node p) {
		if(node == null) {
			p = null;
			return null;
		}
		if(node.key == key) return p;
		if(node.key < key) return searchBstWithParentNode(node.rc, key, node);
		else return searchBstWithParentNode(node.lc, key, node);
	}
	public static void inOrder(Node node) {
		if(node != null) {
			inOrder(node.lc);
			System.out.println(node.key);
			inOrder(node.rc);
		}
	}
	public static boolean isBst(Node node) {
		if(node == null) return true;
		if(!isBst(node.lc)) return false;
		if(node.key < lastVisited) return false;
		lastVisited = node.key;
		if(!isBst(node.rc)) return false;
		return true;
	}
	public static void searchPath(Node node, int key) {
		if(node != null) {
			if(node.key == key) {
				System.out.print(node.key + " ");
				return;
			}
			else if(key < node.key) searchPath(node.lc, key);
			else searchPath(node.rc, key);
			System.out.print(node.key + " ");
		}
		else System.out.print("no key ");
	}
}
