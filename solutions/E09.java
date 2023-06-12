import java.util.Scanner;

class Tree {
    Node root;

    Tree() {root = null;}

    void setRoot(Node n) { root = n; }
    Node getRoot() { return root; }

    void insert(int value) { insert(value, root); }
    void insert(int value, Node n) {
        if (value == n.getValue()) {
            n.incrCount();
            return;
        }
        if (value > n.getValue()) {
            n.incrCount();
            if (n.getRight() == null) {
                n.setRight(new Node(value));
                return;
            }
            else {
                insert(value, n.getRight());
                return;
            }
        }
        if (n.getLeft() == null) {
            n.setLeft(new Node(value));
            return;
        }
        insert(value, n.getLeft());
        return;
    }

    int getCam(int val) {return getCam(val, root, 0);}
    int getCam(int val, Node n, int count) {
        if (n.getValue() == val) return n.getCount() + count;
        if (val > n.getValue()) {
            if (n.getRight() == null) return count;
            return getCam(val, n.getRight(), count);
        }
        count += n.getCount();
        if (n.getLeft() == null) return count;
        return getCam(val, n.getLeft(), count);
    }
}

class Node {
    int value, count;
    Node left, right;
    Node(int val) {
        value = val;
        count = 1;
        left = null;
        right = null;
    }
    void setRight(Node r) { right = r; }
    void setLeft(Node l)  { left = l; }
    Node getRight()       { return right; }
    Node getLeft()        { return left; }
    int getValue()        { return value; }
    int getCount()        { return count; }
    void incrCount()      { count ++; } 
}


public class E09 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Tree postos = new Tree();
        int N = in.nextInt();                   // numero de postos de controlo
        int M = in.nextInt();                   // numero de participantes

        postos.setRoot(new Node(in.nextInt()));
        for (int i=1; i<M; i++)
            postos.insert(in.nextInt());
        int Q = in.nextInt();
        for (int i=0; i<Q; i++)
            System.out.println(postos.getCam(in.nextInt()));
    }
}