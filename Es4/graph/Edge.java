package graph;

import java.util.Comparator;

/**
 * T, S type Edge to be used in Graph, has a source Node, a destination Node
 * and a S type label
 * @author paschetta parusso lombardi
 * @param <T> type of element of nodes
 * @param <S> type of label of edges
 */

public class Edge<T,S> {

    private Node<T> source;
    private Node<T> destination;
    private S label;
    private Comparator<S> comparator;

    /**
     * creates edge with source, destination and label given
     * @param source Node source of edge
     * @param destination Node destination of edge
     * @param label S type label of edge
     */
    
    public Edge (Node<T> source, Node<T> destination, S label, Comparator<S> comparator) {
        this.source = source;
        this.destination = destination;
        this.label = label;
        this.comparator = comparator;
    }

    
    
    /** 
     * @param e1 edge to be compared with
     * @return boolean if all fields of current edge are equals to e1 fields
     */
    public boolean equals(Edge<T,S> e1) {
        boolean ret = (this.source.equals(e1.getSource()));
        ret = ret && (this.destination.equals(e1.getDestination()));
        ret = ret && (this.comparator.compare(this.getLabel(), e1.getLabel()) == 0);
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
     * @return S label of edge
     */
    public S getLabel() {
        return this.label;
    }
    /**
     * prints source, destination and label of edge values
     */
    public void printEdge() {
        System.out.println("Src: "+this.getSource().getValue()+" Dest: "+this.getDestination().getValue()+" Label: "+this.getLabel());
    }
}


