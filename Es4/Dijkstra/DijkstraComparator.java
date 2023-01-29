package dijkstra;
import java.util.Comparator;

/**
 * Comparator used in Dijkstra minimum path
 */

public class DijkstraComparator<T> implements Comparator<DijkstraNode<T>> {
    
    Comparator<T> comp = null;

    public DijkstraComparator(Comparator<T> comparator) throws DijkstraException {
        if(comparator == null) {
            throw new DijkstraException("the comparator of the data type cannot be null");
        }
        this.comp = comparator;
    }
    /** 
     * Compares the two given nodes by distance first, if distance is the same, by comparator of type T
     * given by the user
     * @param n1 first Node of Graph given
     * @param n2 second Node of Graph given
     * @return if distances of nodes aren't equals: -1 if n1 has distance minor than n2, 1 if n1 distance is greater than n2
     * if distances of nodes are equals: -1 if n1 node label is minor than n2 node label, 1 if n1 node label is greater than n2,
     * 0 if equals
     */
    public int compare(DijkstraNode<T> n1, DijkstraNode<T> n2) {
        if(n1.getDist()!=(n2.getDist())) {
            return (n1.getDist().compareTo(n2.getDist()));
        } else {
            return comp.compare(n1.getValue(), n2.getValue());
        }
    }
}
