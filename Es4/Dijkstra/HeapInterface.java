package dijkstra;
import java.util.ArrayList;

/**
 * 
 * It represents a minimum heap of T type objects
 * @author paschetta 
 * @author parusso 
 * @author lombardi
 * @param <T> type of heap elements
 */

public interface HeapInterface<T>{

    /**
     * It inserts T type parameter key into heap
     * @param key: the element to be inserted
     */
    void insert(T element);

    /**
     * @return the number of elements currently stored in the Heap
     */
    int size();

    /**
     * 
     * @return true if heap is empty, false otherwise
     */
    boolean isEmpty();
    
    /**
     * 
     * @param value: the T element which the parent is looked for
     * @return the T type parent of the element given as parameter 
     */
    T getParent(T value);

    /**
     * 
     * @param value the T parent whose LeftChild is searched
     * @return the T type left child of the element given as parameter
     */
    T getLeftChild(T value);

    /**
     * 
     * @param value the T parent whose LeftChild is searched
     * @return the T type left child of the element given as parameter
     */
    T getRightChild(T value);

    /**
     * @return T type element corresponding to the minimum element in the heap
     */
    T popMinimum();
    
    /**
     * Decreases oldValue element in heap to newValue and puts it in the right spot of the heap
     * @param oldValue the oldValue to be replaced in the Heap
     * @param newValue the newValue to be inserted in the Heap
     */
    void decreaseElement(T oldValue, T newValue);

    /*
     * Inserts all the elements of the array given as parameter in the heap
     * @param elemArray the ArrayList of elem to be inserted
     */
    void buildHeap(ArrayList<T> elemArray);

    /*
     * Prints all the elements of the heap
     */
    void printHeap();
}
