package dijkstra;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Comparator;


public class DijkstraMinPath<T extends Comparable<T>> {
    
    /** minPath gets minimum path distance from source to each node in Graph g
     * the method uses an hashtable, to map T elements from graph to specific Node with parent and distance fields,
     * a comparator, to sort initially nodes in heap, a minimum heap, to take at each time the closest node,
     * and an arrayList to be returned, used to store nodes with value and final distance from source
     * @param g graph of T nodes 
     * @param source T type source of path
     * @param comp comparator of T type, used to order nodes in heap if distances are equals
     * @return ArrayList<DijkstraNode<T>> with nodes value and their distance from source
     */
    public ArrayList<DijkstraNode<T>> minPath(Graph<T> g, T source, Comparator<T> comp) {
        try{
            if(g == null) {
                throw new DijkstraException("Dijkstra.minPath: cannot find a minimum path in an null graph");
            }
            if(source == null) {
                throw new DijkstraException("Dijkstra.minPath: cannot find a minimum path for a null source");
            }
            if(comp == null) {
                throw new DijkstraException("Dijkstra.minPath: cannot find a minimum path with a null comparator");
            }
            Hashtable<T, DijkstraNode<T>> table = new Hashtable<T,DijkstraNode<T>>(); 
            DijkstraComparator<T> comparator = new DijkstraComparator<T>(comp);
            Heap<DijkstraNode<T>> dijkstraHeap = new Heap<DijkstraNode<T>>(comparator);
            ArrayList<T> nodesGraph = g.getNodes();
            ArrayList<DijkstraNode<T>> retArray = new ArrayList<>();

            /*Initialize nodes in Heap with infinity distance and null parent */
            for(int i = 0; i<g.nodeNumber(); i++) {
                T value = nodesGraph.get(i);
                table.put(value, new DijkstraNode<T>(value, Double.POSITIVE_INFINITY, null));
                dijkstraHeap.insert(table.get(value));
            }

            /*Set source node distance to 0 and decrease element in heap */
            DijkstraNode<T> sourceNode = table.get(source);
            DijkstraNode<T> newSource = new DijkstraNode<T>(source, 0.0,null);
            table.remove(source);
            table.put(source, newSource);
            dijkstraHeap.decreaseElement(sourceNode, newSource);

            /*Main loop */

            /* Loops until heap is empty */
            while(!dijkstraHeap.isEmpty()) {

                /* Extracts minimum from heap, removes it from hashtable and adds it in final array */
                DijkstraNode<T> min = dijkstraHeap.popMinimum(); 
                table.remove(min.getValue());
                retArray.add(min);

                /* For each node n in the adjlist of heap minimum does relax:
                 * if the current distance of n is greater than the sum of distance from source to 
                 * minimum element of the heap at the moment and from this one to n, update n distance with
                 * sum value */

                ArrayList<T> adjNodes = g.getAdjNodes(min.getValue());
                for(int i = 0; i<adjNodes.size(); i++) {
                    DijkstraNode<T> curr = table.get(adjNodes.get(i));
                    if(curr!=null) {
                        Double oldDist = table.get(curr.getValue()).getDist();
                        Double newDist = min.getDist()+g.label(min.getValue(), curr.getValue());
                        if(oldDist.compareTo(newDist)>0) {
                            DijkstraNode<T> newNode = new DijkstraNode<T>(curr.getValue(), newDist, min.getValue());
                            table.remove(curr.getValue());
                            table.put(curr.getValue(), newNode);
                            dijkstraHeap.decreaseElement(curr, newNode);
                        }
                    }
                }
            }
            return retArray;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

