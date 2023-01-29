package heap;

import java.util.ArrayList;
import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * It specifies a test suite for the Heap library
 * @author paschetta 
 * @author parusso 
 * @author lombardi
 */

public class HeapTests {

    class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
          return i1.compareTo(i2);
        }
      }

    private Integer i1,i2,i3;
    private Heap<Integer> heap;

    @Before
    public void createHeap() throws HeapException{
        i1 = 1;
        i2 = 2;
        i3 = 4;
        heap = new Heap<Integer>(new IntegerComparator());
    }
    
    @Test
    public void testIsEmpty_zeroEl(){
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testIsEmpty_oneEl() throws Exception{
        heap.insert(i1);
        assertFalse(heap.isEmpty());
    }


    @Test
    public void testSize_zeroEl() throws Exception{
        assertEquals(0,heap.size());
    }

    @Test
    public void testSize_oneEl() throws Exception{
        heap.insert(i1);
        assertEquals(1,heap.size());
    }

    @Test
    public void testSize_twoEl() throws Exception{
        heap.insert(i1);
        heap.insert(i2);
        assertEquals(2,heap.size());
    }
    

    
    @Test
    public void testInsert_oneEl() throws Exception{
        heap.insert(i1);
        assertTrue(i1==heap.array.get(0));
    }

    @Test
    public void testInsert_twoEl() throws Exception{
        heap.insert(i2);
        heap.insert(i1);
        assertTrue(i2==heap.array.get(1));
    }

    @Test
    public void testInsert_threeEl() throws Exception{
        heap.insert(i2);
        heap.insert(i1);
        heap.insert(i3);
        assertTrue(i3==heap.array.get(2));
    }
    
    @Test
    public void testGetParent_oneEl() throws Exception{
        heap.insert(i1);
        assertTrue(null==heap.getParent(i1));
    }
    

    @Test
    public void testGetParent_twoEl() throws Exception{
        heap.insert(i2);
        heap.insert(i1);
        assertTrue(i1==heap.getParent(i2));
    }
    
    
    @Test
    public void testGetLeftChild_oneEl() throws Exception{
        heap.insert(i1);
        assertTrue(heap.getLeftChild(i1)==null);
    }

    
    
    @Test
    public void testGetLeftChild_twoEl() throws Exception{
        heap.insert(i1);
        heap.insert(i2);
        assertTrue(heap.getLeftChild(i1).equals(i2));
    }
    
    
    @Test
    public void testGetLeftChild_threeEl() throws Exception{
        heap.insert(i2);
        heap.insert(i1);
        heap.insert(i3);
        assertTrue(heap.getLeftChild(i1).equals(i2));
    }
    
    
    @Test
    public void testGetLeftChild_leaf() throws Exception{
        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        assertTrue(null==heap.getLeftChild(i3));
    }
    
    
    @Test
    public void testGetRightChild_twoEl() throws Exception{
        heap.insert(i2);
        heap.insert(i1);
        assertTrue(heap.getRightChild(i1)==null);
    }
    
    @Test
    public void testGetRightChild_threeEl() throws Exception{
        heap.insert(i2);
        heap.insert(i1);
        heap.insert(i3);
        assertTrue(heap.getRightChild(i1).equals(i3));
    }
    

    @Test
    public void testGetRightChild_leaf() throws Exception{
        heap.insert(i1);
        assertTrue(null == heap.getRightChild(i1));
    }
    
    @Test
    public void testPopMinimum_oneEl() throws Exception{
        heap.insert(i1);
        assertTrue(i1==heap.popMinimum());
    }
    
    @Test
    public void testPopMinimum_twoEl() throws Exception{
        heap.insert(i1);
        heap.insert(i2);
        heap.popMinimum();
        assertTrue(i2==heap.popMinimum());
    }

    @Test
    public void decreaseElement() throws Exception{
        heap.insert(i1);
        heap.insert(i2);
        heap.decreaseElement(i2, 0);
        assertTrue(0 == heap.popMinimum());
    }

    public void testBuildHeap_threeEl() throws Exception {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(i3);
        arr.add(i2);
        arr.add(i1);
        assertTrue(i1 == heap.popMinimum());
    } 
}

