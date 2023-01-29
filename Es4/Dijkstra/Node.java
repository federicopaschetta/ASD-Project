package dijkstra;

/*
 * T type Node to be used in graph class, with class variable T type value, 
 * @author paschetta
 * @author parusso
 * @author lombardi
 * @param <T> type of element of nodes
 */

public class Node<T> {
    private T value;

    /**
     * creates a Node with T value
     * @param value to be inserted in node
     */
    public Node(T value) {
        this.value = value;
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
        return (n1.value.equals(this.getValue()));
    }
}
