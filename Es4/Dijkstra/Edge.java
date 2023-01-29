package dijkstra;

/**
 * T type Edge to be used in Graph, has a source Node, a destination Node
 * and a double type label
 * @author paschetta 
 * @author parusso 
 * @author lombardi
 * @param <T> type of element of nodes
 */

public class Edge<T>{

    private Node<T> source;
    private Node<T> destination;
    private Double label;

    /**
     * creates edge with source, destination and label given
     * @param source Node source of edge
     * @param destination Node destination of edge
     * @param label Double label of edge
     */
    public Edge (Node<T> source, Node<T> destination, Double label) {
        this.source = source;
        this.destination = destination;
        this.label = label;
    }

    
    /** 
     * @param e1 edge to be compared with
     * @return boolean true if all fields of edge are equals to e1 fields, false otherwise
     */
    public boolean equals(Edge<T> e1) {
        boolean ret = (this.source.equals(e1.getSource()));
        ret = ret && (this.destination.equals(e1.getDestination()));
        ret = ret && (this.label.equals(e1.getLabel()));
        return ret;
    }

    
    /** 
     * @return Node<T> source of edge
     */
    public Node<T> getSource () {
        return this.source;
    }

    
    /** 
     * @return Node<T> destination of edge
     */
    public Node<T> getDestination() {
        return this.destination;
    }

    
    /** 
     * @return Double label of edge
     */
    public Double getLabel() {
        return this.label;
    }
    /**
     * prints source value, destination value and label of edge
     */
    public void printEdge() {
        System.out.println("Src: "+this.getSource().getValue()+" Dest: "+this.getDestination().getValue()+" Label: "+this.getLabel());
    }
}


