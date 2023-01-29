package dijkstra;
/*
 * Special T type Node to be used in DijkstraMinPath method, with class variables T type value, 
 * Double actual distance from source and T parent, last node of the sequence from source to node
 */

public class DijkstraNode<T> {
    private T value;
    private Double dist;
    private T parent;


    /**
     * creates a Node with value, distance and parent given
     * @param value value of node element
     * @param dist actual distance from source
     * @param parent parent in minimum path
     */
    public DijkstraNode(T value, Double dist, T parent) {
        this.value = value;
        this.dist = dist;
        this.parent = parent;
    }

    /**
     * Constructor used when parent is unknown
     * @param value value of node element
     * @param dist parent in minimum path
     */
    public DijkstraNode(T value, Double dist) {
        this.value = value;
        this.dist = dist;
        this.parent = null;
    }

    
    /** 
     * @return T value of the node
     */
    public T getValue() {
        return this.value;
    }

    
    /** Updates distance of node to the parameter given
     * @param distance distance to be updated to
     */
    public void setDist(Double distance) {
        this.dist = distance;
    }

    
    /** Updates parent of node to the parameter given
     * @param parent parent to be updated to
     */
    public void setParent(T parent) {
        this.parent = parent;
    }

    
    /** 
     * @return Double actual distance from source to node
     */

    public Double getDist() {
        return this.dist;
    }

    
    /** 
     * @return T actual parent of node in the minimum path from source to node
     */
    public T getParent() {
        return this.parent;
    }
    /**
     * prints T value of node and distance from source to node
     */
    public void printNode() {
        System.out.println(this.getValue() + " " + this.getDist());

    }

    /**
     * 
     * @param n1 node to be compared with
     * @return true if value of this node is equal to n1 value
     */
    public boolean equals(DijkstraNode<T> n1) {
        return (n1.value.equals(this.getValue()));
    }


}


