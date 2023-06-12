import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;
import java.lang.StringBuilder;

class Node {
    int value;
    Node left;
    Node right;
    Node() {
        value = 0;
        left = null;
        right = null;
    }
    Node(int val) {
        value = val;
        left = null;
        right = null;
    }
    Node(int val, Node l, Node r) {
        value = val;
        left = l;
        right = r;
    }
    int getValue() { return value; }
    Node getLeft() { return left; }
    Node getRight() { return right; }
    void setValue(int val) { value = val; }
    void setLeft(Node l) { left = l; }
    void setRight(Node r) { right = r; }
}

class BTree {
    Node root;
    BTree() { root = null; }
    BTree(int value) { root = new Node(value);}
    Node getRoot() { return root; }
    public int depth() {
        return depth(root);
     }
     private int depth(Node n) {
        if (n == null) return -1;
        return 1 + Math.max(depth(n.getLeft()), depth(n.getRight()));
     }
     public int folhas() {
         return folhas(root);
     }
     public int folhas(Node n) {
         if (n==null) return 0;
         if (n.getLeft() == null && n.getRight() == null) return 1;
         return folhas(n.getLeft()) + folhas(n.getRight());
     }


    void read(LinkedList<Integer> preorder, int[] inorder) {
        read(preorder, inorder, root);
    }
    void read(LinkedList<Integer> preorder, int[] inorder, Node n) {
        if (preorder.isEmpty()) return;
        n.setValue(preorder.removeFirst());
        if (preorder.isEmpty()) return;
        int[] inLeft = new int[inorder.length];
        int inLeftlength = 0;
        int[] inRight = new int[inorder.length];
        int inRightlength = 0;
        for (int i=0; i<inorder.length; i++) {
            if (inorder[i] == n.getValue()) {
                inLeft = new int[i];
                inLeftlength = i;
                inRight = new int[inorder.length - i - 1];
                inRightlength = inorder.length - i - 1;
                break;
            }
        }
        for (int i=0; i<inorder.length - 1; i++) {
            if (i<inLeftlength) inLeft[i] = inorder[i];
            if (i>inLeftlength) inRight[i - inLeftlength - 1] = inorder[i]; 
        }
        if (inLeftlength != 0) {
            n.setLeft(new Node());
            /* System.out.print("Left: ");
            for (int i=0; i<inLeft.length; i++) 
                System.out.print(inLeft[i] + " ,");
            System.out.println(); */
            read(preorder, inLeft, n.getLeft());
        }
        if (inRightlength != 0) {
            n.setRight(new Node());
            /* System.out.print("Right: ");
            for (int i=0; i<inRight.length; i++) 
                System.out.print(inRight[i] + " ,");
            System.out.println(); */
            read(preorder, inRight, n.getRight());
        }
    }

    // POSTORDER
    public void printPostOrder() {
        printPostOrder(root.getLeft());
        printPostOrder(root.getRight());
        System.out.print(root.getValue());
        System.out.println();
     }
     private void printPostOrder(Node n) {
        if (n==null) return;
        printPostOrder(n.getLeft());
        printPostOrder(n.getRight());
        System.out.print(n.getValue() + " ");
     }



    public void print() {
        printBinaryTree(root,0);
    }
    public void printBinaryTree(Node root, int level){
        if(root==null)
             return;
        printBinaryTree(root.getRight(), level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
                System.out.println("|-------"+root.getValue());
        }
        else
            System.out.println(root.getValue());
        printBinaryTree(root.getLeft(), level+1);
    }
        
    public String toString() {
        return toString(root);
    }
    public String toString(Node n) {
        if (n == null) return "N";
        else 
            return n.getValue() + " " + toString(n.getLeft()) + " " + toString(n.getRight());
    }    

}

public class E11 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int C = in.nextInt();
        for (int i=0; i<C; i++) {
            int N_NOS = in.nextInt();
            LinkedList<Integer> preorder = new LinkedList<>();
            
            for (int k=0; k<N_NOS; k++)
                preorder.addLast(in.nextInt());

            int[] inorder = new int[N_NOS];
            for (int k=0; k<N_NOS; k++) {
                int ne = in.nextInt();
                inorder[k] = ne;
            }           
           
            BTree arvore = new BTree(0);
            arvore.read(preorder, inorder);
            arvore.printPostOrder();
            System.out.println("Folhas = " + arvore.folhas());
            //System.out.println(arvore);
            //arvore.print();
        }
    }
}