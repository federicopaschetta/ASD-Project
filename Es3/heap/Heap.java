package heap;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Comparator;

/**
 * 
 * It represents a minimum heap of T type objects using an ArrayList sorted in non descending order T type objects
 * The class uses a HashTable <T, Integer> to keep track of indexes of elements for safety purposes. 
 * It also has a comparator, in order to compare T type elements
 * @author paschetta 
 * @author parusso 
 * @author lombardi
 * @param <T> type of heap elements
 */

public class Heap<T> implements HeapInterface<T>{

    ArrayList<T> array;
    private Hashtable<T,Integer> tables = new Hashtable<T,Integer>();
    Comparator<T> comparator = null;

    /**
     * It creates an empty heap with an empty ArrayList of T elements
     * @throws HeapException iff comparator is null
     */
    public Heap(Comparator<T> comparator) throws HeapException {
        if(comparator==null) {
            throw new HeapException("HeapConstructor: comparator cannot be null");
        }
        this.array = new ArrayList<T>();
        this.comparator = comparator;

    }

    /**
     * It inserts T type parameter key into ArrayList at the end of the array and puts it in the HashTable 
     * @param key: the element to be inserted
     */
    @Override
    public void insert(T element) { 
        try{
            if(element == null) {
                throw new HeapException("insertElement: the parameter elem cannot be null");
            }    
            if(tables.get(element) == null) {
                this.array.add(element);
                int count = this.size()-1;
                tables.put(element, count);
                heapifyBottomTop(count);
            } else {
                throw new HeapException("insertElement: the parameter is already present in the Heap");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    /**
     * @return the number of elements currently stored in the Heap
     */
    @Override
    public int size(){
        return this.array.size();
    }

    /**
     * 
     * @return true if heap is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (size()==0);
    }
    
    /**
     * 
     * @param value: the T element which the parent is looked for
     * @return the T type parent of the element given as parameter 
     */
    @Override
    public T getParent(T value) {
        try{
            return this.getParent((int)this.getItemIndex(value));    
        } catch(HeapException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 
     * @param value the T parent whose LeftChild is searched
     * @return the T type left child of the element given as parameter
     */
    @Override
    public T getLeftChild(T value) {
        return this.getLeftChild((int)this.getItemIndex(value));
    }

        /**
     * 
     * @param value the T parent whose RightChild is searched
     * @return the T type right child of the element given as parameter
     */
    @Override
    public T getRightChild(T value) {
        return this.getRightChild((int)this.getItemIndex(value));
    }

    /**
     * 
     * @return T type element corresponding to the minimum element in the heap
     */
    @Override
    public T popMinimum() {
            T min = null;
            if(size()>=0) {
                min = array.get(0);
                array.set(0, array.get(size()-1));
                array.remove(size()-1);
                heapifyTopBottom(0);
            }
        return min;
    }
    
    /**
     * Decreases oldValue element in array to newValue and replace its key in the HashTable with its new value
     * and puts it in the right space of the heap
     * @param oldValue the oldValue to be replaced in the Heap
     * @param newValue the newValue to be inserted in the Heap
     */
    @Override
    public void decreaseElement(T oldValue, T newValue) {
        try{
            if(oldValue == null)
                throw new HeapException("decreaseElement: the oldValue given as parameter cannot be null");
            if(newValue == null)
                throw new HeapException("decreaseElement: the newValue given as parameter cannot be null");
            if((this.comparator).compare(newValue, oldValue)>=0) {
                throw new HeapException("decreaseElement: the newValue given as parameter must be minor than the actual element");
            }
            int elemIndex = (int)getItemIndex(oldValue);
            array.set(elemIndex, newValue);
            tables.remove(oldValue, elemIndex);
            tables.put(newValue, elemIndex);
            heapifyBottomTop(elemIndex);
        } catch (Exception e) {
            System.out.println(e);
        }
    }    

    /*
     * Inserts all the elements of the array given as parameter in the heap
     * @param elemArray the ArrayList of elem to be inserted
     * 
     */
    @Override
    public void buildHeap(ArrayList<T> elemArray) {
        try{
            for(int i = 0; i<elemArray.size(); i++) {
                this.insert(elemArray.get(i));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    /*
     * Prints all the elements of the heap
     */
    @Override
    public void printHeap(){
        int i;
        int count = this.size();
        for(i=0; i<count; i++){
            System.out.println(this.array.get(i));
        }
    }

    /*
    PRIVATE METHODS
    */
    /**
     * @param item value present in the Heap ArrayList
     * @return the index in the HashTable referred to T item
     */
    private Integer getItemIndex(T item) {
        try{
            if(item == null)
                throw new HeapException("getItemIndex: the item given as parameter cannot be null");
            return tables.get(item).intValue();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * If the element at the index given as parameter minor than his parent, 
     * it switches his place with it in the ArrayList and switches his value in the HashTable and
     * recalls the method on his new place until it's not minor than parent value
     * @param index the index of the element present in the Heap
     */
    private void heapifyBottomTop(int index){
        try{
            if(index>0) {
                int parentIndex = (index-1)/2;
                T currentValue = array.get(index);
                T parentValue = getParent(index);
                if ((this.comparator).compare(currentValue, parentValue)<0) {
                    array.set(parentIndex, currentValue);
                    array.set(index, parentValue);
                    switchTabValues(currentValue, parentValue);
                    heapifyBottomTop(parentIndex);
                }
            }  
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * If the element at the index given as parameter is not minor than both of his children, 
     * it switches his place with the minor of the two in the ArrayList and switches his value in the HashTable and
     * recalls the method on his new place until it's greater than both of his children
     * @param index the index of the element present in the Heap
     */
    private void heapifyTopBottom(int index) {
        try{
            if(isLeaf(index))
                throw new HeapException("heapifyTopBottom: the index given as parameter belongs to a leaf");
            int minIndex = getMinParentsChildrenIndex(index);
            if(minIndex != index) {
                T minValue = array.get(minIndex);
                T currValue = array.get(index);
                array.set(minIndex, currValue);
                array.set(index, minValue);
                switchTabValues(array.get(index), array.get(minIndex));
                heapifyTopBottom(minIndex);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }        
    /**
     * @param index: the T element which the parent is looked for
     * @return the T type Parent of the heap element at index given as parameter
     * @throws HeapException iff the index is out of bounds or belongs to the root
     */
    private T getParent(int index) throws HeapException {
        if(index == 0) 
            throw new HeapException("getParentIndex: the index parameter belongs to root index, cannot have any parent");
        if(index<0 || index>=size()) {
            throw new HeapException("getParentIndex: the index given as parameter is out of the Heap bounds");
        }
        return array.get((index-1)/2);
    }

    /**
     * @param index: the T element which the LeftChild is looked for
     * @return the T type LeftChild of the heap element at index given as parameter or null if is a leaf
     */
    private T getLeftChild(int index) {
        if(index*2+1>=size()) {
            return null;
        } else {
            return array.get(index*2+1);
        }
    }

    /**
     * @param index: the T element which the RightChild is looked for
     * @return the T type RightChild of the heap element at index given as parameter
     */
    private T getRightChild(int index) {
        if(index*2+2>=size()) {
            return null;
        } else {
            return array.get(index*2+2); 
        }
    }

    /**
     * @param firstIndex index in the HashTable of the first element to be compared
     * @param secondIndex index in the HashTable of the second element to be compared
     * @return the index of the HashTable of the minimum between the two elements whose index are given as parameters
     * @throws HeapException iff any of the index parameter is out of the array bounds 
     */
    private int getMinIndex(int firstIndex, int secondIndex) throws HeapException {
        if(firstIndex<0 || firstIndex>=size())
            throw new HeapException("getMinIndex: the param firstIndex is out of the Heap bounds");
        if(secondIndex<0 || secondIndex>=size())
            throw new HeapException("getMinIndex: the param firstIndex is out of the Heap bounds");
        if((this.comparator).compare(array.get(firstIndex), array.get(secondIndex))<0) 
            return firstIndex;
        return secondIndex;
    }

    /**
     * @param index index in the HashTable of the parent element to be compared with children
     * @return the index of the HashTable of the minimum between the element whose index is given as parameters and his children
     * @throws HeapException iff the index parameter is out of bounds
     */
    private int getMinParentsChildrenIndex(int index) throws HeapException {
        if(index<0)
            throw new HeapException("getMinParentsChildrenIndex: index given as parameter is out of array bounds");
        if(2*index+1 < size()-1) {
            return getMinIndex(index, getMinIndex(index*2+1, index*2+2));
        } else if(2*index+1==size()-1) {
            return getMinIndex(index, index*2+1);
        }
        return index;
    }

    /**
     * Switches the values representing the indexes of the elements given as param in the HashTable
     * @param firstKey key of the first element in the HashTable
     * @param secondKey key of the second element in the HashTable
     * @throws HeapException if any of the key given as parameters is null
     */
    private void switchTabValues(T firstKey, T secondKey) throws HeapException {
        if(firstKey == null)
            throw new HeapException("switchTabValues: the firstKey parameter is null");
        if(secondKey == null)
            throw new HeapException("switchTabValues: the secondKey parameter is null");
        int firstKeyIndex = tables.get(firstKey);
        int secondKeyIndex = tables.get(secondKey);
        tables.replace(firstKey, firstKeyIndex, secondKeyIndex);
        tables.replace(secondKey, secondKeyIndex, firstKeyIndex);
    }

    /**
     * @param index the index of the element in the Heap
     * @return true if the element has no child, false otherwise
     * @throws HeapException if the parameter is null or is not present in the Heap
     */
    private boolean isLeaf(Integer index) throws HeapException {
        if (index == null)
            throw new HeapException("isLeafElement: the parameter element cannot be null");
        return ((index*2+1)>=this.size());
    }
}
