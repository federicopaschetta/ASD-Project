package graph;
import java.util.ArrayList;

/**
 * 
 * It represents a graph of T type Nodes and S label edges
 * @author paschetta parusso lombardi
 * @param <T> type of elements of Node
 * @param <S> type of label of edge
 */
public interface GraphInterface<T,S> {

    /**
     * adds the with value n to the graph if it isn't already there
     * @param n value of element to be added to the graph
     */
    void addNode(T n);


    /**
     * adds the Edge with source, destination and label given to the graph. 
     * If any of the nodes of the edge isn't already there, it's added.
     * @param source value of node source of edge to be added
     * @param dest value of node destination of edge to be added
     * @param label label of edge to be added
     */
    void addEdge(T source, T dest, S label);

    /**
     * @return true if graph is directed, false if not
     */
    boolean direct();

    /**
     * Searches if value n is already in the graph
     * @param n Node to be searched for
     * @return true if Node n is in the graph, false if not
     */
    boolean containsNode(T n);

    /**
     * 
     * @param source value of node source of edge to be searched
     * @param dest value of node destination of edge to be searched
     * @param label node label of edge to be searched
     * @return true if edge composed by parameters is in the graph, false if not
     */
    boolean containsEdge(T source, T dest, S label);


    /**
     * Deletes Node n from the graph and all edges whose n is source or destination
     * @param n Node value to remove from the graph
     * @throws GraphException if n is null
     */
    void deleteNode(T n);

    /**
     * Deletes Edge with source, dest and label given from the graph
     * @param source Node source of edge to delete
     * @param dest Node destination of edge to delete
     * @param label label of edge to delete
     */
    void deleteEdge(T source, T dest, S label);

    /**
     * 
     * @return number of nodes in the graph
     */
    int nodeNumber();
    /**
     * 
     * @return number of edges in the graph
     */
    int edgeNumber();

    /**
     * 
     * @return arraylist of all nodes present in the graph
     */
    ArrayList<T> getNodes();

    /**
     * 
     * @return arraylist of all edges present in the graph
     */
    ArrayList<Edge<T,S>> getEdges();

    /**
     * 
     * @param n Node value whose adjlist to be returned
     * @return arraylist of values in n adjacency list
     */
    ArrayList<T> getAdjNodes(T n);

        /**
     * 
     * @param n1 edge source node value whose label is looked for
     * @param n2 edge destination node value whose label is looked for
     * @return label of edge with source n1 and destination n2, null if edge is not present in graph
     */
    S label(T n1, T n2);

    /**
     * prints nodes of graph
     */
    void printGraphNodes();

    /**
     * prints all edges in all adjacency lists
     */
    void printAdjList();
}

