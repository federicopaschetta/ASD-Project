package graph;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Comparator;

/**
 * 
 * It represents a graph of T type Nodes and S label edges. For the implementation there are two Hashtables,
 * one of type <T, ArrayList<Edge<T,S>>> in order to access each adjacency list from any T type node values.
 * The other represents nodes, in order to speed up any access. Then there's a boolean direct, true if graph is 
 * oriented, false otherwise, and two comparators, one of type T and one of type S, used to compare values and labels,
 * with equals method 
 * @author paschetta parusso lombardi
 * @param <T> type of elements of Node
 * @param <S> type of label of edge
 */
public class Graph<T, S> implements GraphInterface<T,S> {

    private Hashtable<T, ArrayList<Edge<T,S>>> adjList = new Hashtable<T, ArrayList<Edge<T,S>>>();
    private Hashtable<T, Node<T>> nodes = new Hashtable<T, Node<T>>();
    private boolean direct;
    public Comparator<T> comp1;
    public Comparator<S> comp2;


    /**
     * It creates an empty graph with all variables at 0, ArrayList of nodes empty and
     * direct true by default, otherwise it's specified with the parameter
     * @param direct true if graph is directed, false otherwise
     */

    public Graph(Comparator<T> comp1, Comparator<S> comp2) throws GraphException{
        if (comp1 == null || comp2 == null) {
            throw new GraphException("GraphConstructor: Cannot have null comparator for class Graph");
        }
        this.nodes = new Hashtable<>();
        this.adjList = new Hashtable<>();
        this.direct = true;
        this.comp1 = comp1;
        this.comp2 = comp2;

    }
    public Graph(boolean direct, Comparator<T> comp1, Comparator<S> comp2) throws GraphException {
        if (comp1 == null || comp2 == null) {
            throw new GraphException("GraphConstructor: Cannot have null comparator for class Graph");
        }
        this.nodes = new Hashtable<>();
        this.adjList = new Hashtable<>();
        this.direct = direct;
        this.comp1 = comp1;
        this.comp2 = comp2;
    }

    /**
     * adds the Node n to the graph if it isn't already there, inserts it in hashtable and increments nodesnumber variable
     * @param n value of Node to be added to the graph
     */
    @Override
    public void addNode(T n) {
        try{
            if(n == null) {
                throw new GraphException("addNode: the parameter n cannot be null");
            }
            if(!containsNode(n)) {
            this.nodes.put(n, new Node<T>(n, comp1));
            addNode(this.nodes.get(n));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * adds the Node n to the graph if it isn't already there, inserts it in hashtable and increments nodesnumber variable
     * @param n Node to be added to the graph
     */
    private void addNode(Node<T> n) {
        this.adjList.put(n.getValue(), new ArrayList<>());
    }

    /**
     * adds the Edge e to the graph. If any of the nodes of the edge isn't already there, it's added.
     * @param source value of node source of edge to be added
     * @param dest value of node destination of edge to be added
     * @param label label of edge to be added
     */
    @Override
    public void addEdge(T source, T dest, S label) {
        try {
            if(source == null) {
                throw new GraphException("addEdge: the parameter source cannot be null");
            } else if(dest == null) {
                throw new GraphException("addEdge: the parameter destination cannot be null");
            } else if(label == null) {
                throw new GraphException("addEdge: the parameter label cannot be null");
            } else {
                if(!this.containsNode(source)) {
                    addNode(source);
                }
                if(!this.containsNode(dest)) {
                    addNode(dest);
                }
                this.addEdge(new Edge<T,S>(nodes.get(source), nodes.get(dest), label, this.comp2));
            }
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }

    /**
     * adds the Edge e to the graph. If any of the nodes of the edge isn't already there, it's added.
     * if graph is not directed, it's added the reverse edge
     * @param e
     */
    private void addEdge(Edge<T,S> e) {
        try {
            if(e == null) {
                throw new GraphException("addEdge: the parameter e cannot be null");
            }
            this.adjList.get(e.getSource().getValue()).add(e);
            if(!direct) {
                this.adjList.get(e.getDestination().getValue()).add(new Edge<>(e.getDestination(), e.getSource(), e.getLabel(), comp2));
            }
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }

    /**
     * 
     * @return true if graph is directed, false if not
     */
    @Override
    public boolean direct(){
        return this.direct;
    }

    /**
     * Searches if Node n is already in the graph, using the Hashtable
     * @param n Node to be searched for
     * @return true if Node n is in the graph, false if not
     */
    @Override
    public boolean containsNode(T n) {
        try{
            if(n == null) {
                throw new GraphException("containsNode: the parameter n cannot be null");
            }
            return(this.nodes.containsKey(n));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * 
     * @param source node source of edge to be searched
     * @param dest node destination of edge to be searched
     * @param label node label of edge to be searched
     * @return true if edge composed by parameters is in the graph, false if not
     */
    @Override
    public boolean containsEdge(T source, T dest, S label) {
        try {
            if(source == null) {
                throw new GraphException("addEdge: the parameter source cannot be null");
            } else if(dest == null) {
                throw new GraphException("addEdge: the parameter destination cannot be null");
            } else if(label == null) {
                throw new GraphException("addEdge: the parameter label cannot be null");
            } else {
                return this.containsEdge(new Edge<T,S>(nodes.get(source), nodes.get(dest), label, comp2));
            }
        } catch (Exception exc) {
            System.out.println(exc);
            return false;
        }
    }

    /**
     * Searches if Edge e is already in the graph
     * @param e Edge to be searched for
     * @return true if Edge e is in the graph, false if not
     */
    private boolean containsEdge(Edge<T, S> e) {
        ArrayList<Edge<T,S>> curr = this.adjList.get(e.getSource().getValue());
        for(int i = 0; i<curr.size(); i++) {
            if(curr.get(i).equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes Node n from the graph and all edges whose n is source or destination
     * @param n Node to remove from the graph
     * @throws GraphException if n is null
     */
    @Override
    public void deleteNode(T n) {
        try{
            if(n == null) {
                throw new GraphException("deleteNode: the parameter n cannot be null");
            }
            this.deleteNode(this.nodes.get(n));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes Node n from the graph and all edges whose n is source or destination
     * @param n Node to remove from the graph
     * @throws GraphException if n is null
     */
    private void deleteNode(Node<T> n) {
        Enumeration<T> e = this.adjList.keys();
        ArrayList<Edge<T,S>> curr;
        while(e.hasMoreElements()) {
            curr = adjList.get(e.nextElement());
            for(int i = 0; i<curr.size(); i++) {
                if(n.getValue().equals(curr.get(i).getDestination().getValue())) {
                    curr.remove(i);
                }
            }
        }
        this.adjList.remove(n.getValue());
        this.nodes.remove(n.getValue());
    }

    /**
     * Deletes Edge with source, dest and label given from the graph
     * @param source Node source of edge to delete
     * @param dest Node destination of edge to delete
     * @param label label of edge to delete
     */
    @Override
    public void deleteEdge(T source, T dest, S label) {
        try {
            if(source == null) {
                throw new GraphException("addEdge: the parameter source cannot be null");
            } else if(dest == null) {
                throw new GraphException("addEdge: the parameter destination cannot be null");
            } else if(label == null) {
                throw new GraphException("addEdge: the parameter label cannot be null");
            } else {
                this.deleteEdge(new Edge<T,S>(nodes.get(source), nodes.get(dest), label, comp2));
            }
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }

    /**
     * Deletes Edge e from the graph
     * @param e edge to be removed
     */
    private void deleteEdge(Edge<T, S> e) {
        try{
            if(e == null) {
                throw new GraphException("deleteEdge: the parameter e cannot be null");
            }
            ArrayList<Edge<T,S>> srcArrayList = this.adjList.get(e.getSource().getValue());
            for(int i = 0; i<srcArrayList.size(); i++) {
                if(srcArrayList.get(i).equals(e)) {
                    srcArrayList.remove(i);
                }
            }
            if(!direct) {
                Edge<T,S> revEdge = new Edge<T,S>(e.getDestination(), e.getSource(), e.getLabel(), comp2);
                ArrayList<Edge<T,S>> curr = this.adjList.get(e.getDestination().getValue());
                for(int i = 0; i<curr.size(); i++) {
                    if(curr.get(i).equals(revEdge)) {
                        curr.remove(i);
                        break;
                    }
                }
            }
        } catch (Exception exc) {
            System.out.println(e);
        }
    }

    /**
     * 
     * @return number of nodes in the graph
     */
    @Override
    public int nodeNumber(){
        return this.nodes.size();
    }

    /**
     * 
     * @return number of edges in the graph
     */
    @Override
    public int edgeNumber(){
        int counter = 0;
        Enumeration<T> e = this.adjList.keys();
        T key;
        int loopCount = 0;
        while(e.hasMoreElements()) {
            key = e.nextElement();
            counter = counter + adjList.get(key).size();
            if(this.label(key, key) != null) {
                loopCount++;
            }
        }
        if(!direct) {
            return counter/2+loopCount;
        }
        return counter;
    }

    /**
     * 
     * @return arraylist of all nodes present in the graph
     */
    @Override
    public ArrayList<T> getNodes(){
        Enumeration<T> e = this.nodes.keys();
        T key;
        ArrayList<T> totNodes = new ArrayList<>();
        while(e.hasMoreElements()) {
            key = e.nextElement();
            totNodes.add(key);
        }
        return totNodes;
    }

    /**
     * 
     * @return arraylist of all edges present in the graph
     */
    @Override
    public ArrayList<Edge<T, S>> getEdges(){
        ArrayList<Edge<T, S>> edgesList = new ArrayList<>();
        Enumeration<T> e = this.adjList.keys();
        while(e.hasMoreElements()) {
            edgesList.addAll(adjList.get(e.nextElement()));
        }
        return edgesList;
    }

    /**
     * 
     * @param n Node whose adjlist to be returned
     * @return arraylist of nodes in n adjacency list
     */
    @Override
    public ArrayList<T> getAdjNodes(T n) {
        try{
            if (n == null) {
                throw new GraphException("getAdjNodes: the parameter n cannot be null");
            }
            ArrayList<Edge<T,S>> curr = this.adjList.get(n);
            ArrayList<T> adjNodes = new ArrayList<>();
            for(int i = 0; i<curr.size(); i++) {
                adjNodes.add(curr.get(i).getDestination().getValue());
            }
            return adjNodes;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

        /**
     * 
     * @param n1 edge source node value whose label is looked for
     * @param n2 edge destination node value whose label is looked for
     * @return label of edge with source n1 and destination n2, null if edge is not present in graph
     */
    @Override
    public S label(T n1, T n2) {
        try{
            if(n1 == null || n2 == null) {
                throw new GraphException("label: the parameters n1 and n2 cannot be null");
            }
            return this.label(this.nodes.get(n1), this.nodes.get(n2));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 
     * @param n1 edge source node whose label is looked for
     * @param n2 edge destination node whose label is looked for
     * @return label of edge with source n1 and destination n2, null if edge is not present in graph
     */
    private S label(Node<T> n1, Node<T> n2) {
        try{
            if(n1 == null || n2 == null) {
                throw new GraphException("label: the parameters n1 and n2 cannot be null");
            }
            ArrayList<Edge<T,S>> toSeek = this.adjList.get(n1.getValue());
            for(int i = 0; i<toSeek.size(); i++) {
                if(toSeek.get(i).getDestination().equals(n2)) {
                    return toSeek.get(i).getLabel();
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * prints nodes of graph
     */
    @Override
    public void printGraphNodes() {
        Enumeration<T> e = this.adjList.keys();
        T key;
        while(e.hasMoreElements()) {
            key = e.nextElement();
            System.out.println(key);
        }
    }

    /**
     * prints all edges in all adjacency lists
     */
    @Override
    public void printAdjList() {
        Enumeration<T> e = this.adjList.keys();
        T key;
        ArrayList<Edge<T,S>> curr ;
        while(e.hasMoreElements()) {
            key = e.nextElement();
            curr = this.adjList.get(key);
            for(int i = 0; i<curr.size(); i++) {
                System.out.println();
                curr.get(i).printEdge();
            }
        }
    }
}

