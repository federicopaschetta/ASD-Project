package graph;

import java.util.Comparator;

/*
 * T,S type Node to be used in graph class, with class variable T type value
 * @param <T> type of element of nodes
 */

public class Node<T> {
    private T value;
    private Comparator<T> comparator;
    

    /**
     * creates a Node with T value
     * @param value
     */
    public Node(T value, Comparator<T> comparator) {
        this.value = value;
        this.comparator = comparator;
    }

    
    /** 
     * @return T value of node
     */
    public T getValue() {
        return this.value;
    }

    /**
     * prints T value of node
     */
    public void printNode() {
        System.out.println(this.getValue());
    }

    /**
     * 
     * @param n1 node to be compared with
     * @return true if value of this node is equal to n1 value,
     * 1 if this value greater than n1 value, -1 otherwise
     */
    public boolean equals(Node<T> n1) {
        return (this.comparator.compare(n1.value, this.getValue()) == 0);
    }

    
    /** 
     * @param n1 node to be compared with
     * @return Node<T> if n1 is equals with current node, null otherwise
     */
    public Node<T> equalsNode(Node<T> n1) {
        if(this.equals(n1)) {
            return this;
        } else {
            return null;
        }
    }
}
