package graph;

import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * It specifies a test suite for the Heap library
 * 
 * @author paschetta 
 * @author parusso 
 * @author lombardi
 */

public class GraphTests {

    class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    }

    class StringComparator implements Comparator<String> {
        @Override
        public int compare(String i1, String i2) {
            return i1.compareTo(i2);
        }
    }

    private String a, b, c;
    private Integer i1, i2, i3;
    private Node<String> n1, n2, n3;
    private Edge<String, Integer> e1, e2, e3;
    private Graph<String, Integer> graph;

    @Before
    public void createGraph() throws GraphException {
        a = "a";
        b = "b";
        c = "c";
        i1 = 1;
        i2 = 2;
        i3 = 3;
        n1 = new Node<>(a, new StringComparator());
        n2 = new Node<>(b, new StringComparator());
        n3 = new Node<>(c, new StringComparator());

        e1 = new Edge<String, Integer>(n1, n2, i1, new IntegerComparator());
        e2 = new Edge<String, Integer>(n2, n3, i2, new IntegerComparator());
        e3 = new Edge<String, Integer>(n1, n3, i3, new IntegerComparator());
        graph = new Graph<String, Integer>(new StringComparator(), new IntegerComparator());
    }

    @Test
    public void testAddNode_oneEl() {
        graph.addNode(a);
        assertTrue(graph.getNodes().get(0).equals(a));
    }

    @Test
    public void testAddNode_twoEl() {
        graph.addNode(a);
        graph.addNode(b);
        assertTrue(graph.containsNode(b));
    }

    @Test
    public void testAddEdge_oneEl() {
        graph.addEdge(a, b, i1);
        assertTrue(graph.getEdges().get(0).equals(e1));
    }
    
    @Test
    public void testAddEdge_twoEl() {
        graph.addEdge(a, b, i1);
        graph.addEdge(b, c, i2);
        assertTrue(graph.getEdges().get(0).equals(e2));
    }

    @Test
    public void containsNode_oneEl() {
        graph.addNode(a);
        assertTrue(graph.containsNode(a));
    }

    
    @Test
    public void containsNode_twoEl() {
        graph.addNode(a);
        graph.addNode(b);
        assertTrue(graph.containsNode(b));
    }

    @Test
    public void containsEdge_oneEl() {
        graph.addEdge(a, b, i1);
        assertTrue(graph.containsEdge(a, b, i1));
    }

    @Test
    public void containsEdge_twoEl() {
        graph.addEdge(a, b, i1);
        graph.addEdge(b, c, i2);
        assertTrue(graph.containsEdge(b, c, i2));
    }

    
    @Test
    public void deleteNode_oneEl() {
        graph.addNode(a);
        graph.deleteNode(a);
        assertTrue(graph.nodeNumber() == 0);
    }

    @Test
    public void deleteNode_twoEl() {
        graph.addNode(a);
        graph.addNode(b);
        graph.deleteNode(b);
        assertFalse(graph.containsNode(b));
    }

    @Test
    public void deleteEdge_oneEl() {
        graph.addEdge(a, b, i1);
        graph.deleteEdge(a, b, i1);
        assertTrue(graph.edgeNumber() == 0);
    }

    @Test
    public void deleteEdge_twoEl() {
        graph.addEdge(a, b, i1);
        graph.addEdge(b, c, i2);
        graph.deleteEdge(b, c, i2);
        assertFalse(graph.containsEdge(b, c, i2));
    }

    
    @Test
    public void testnodeNumber_zeroEl() throws Exception {
        assertEquals(0, graph.nodeNumber());
    }

    @Test
    public void testnodeNumber_oneEl() throws Exception {
        graph.addNode(a);
        assertEquals(1, graph.nodeNumber());
    }

    @Test
    public void testnodeNumber_twoEl() throws Exception {
        graph.addNode(a);
        graph.addNode(b);
        assertEquals(2, graph.nodeNumber());
    }

    @Test
    public void testEdgeNumber_zeroEl() throws Exception {
        assertEquals(0, graph.edgeNumber());
    }

    @Test
    public void testEdgeNumber_oneEl() throws Exception {
        graph.addEdge(a, b, i1);
        assertEquals(1, graph.edgeNumber());
    }

    @Test
    public void testEdgeNumber_twoEl() throws Exception {
        graph.addEdge(a, b, i1);
        graph.addEdge(b, c, i2);
        assertEquals(2, graph.edgeNumber());
    }

    @Test
    public void testgetNodes_oneEl() throws Exception {
        graph.addNode(a);
        assertTrue(graph.getNodes().get(0).equals(a));
    }

    @Test
    public void testgetNodes_twoEl() throws Exception {
        graph.addNode(a);
        graph.addNode(b);
        assertTrue(graph.getNodes().get(1).equals(a));
    }

    @Test
    public void testgetEdges_oneEl() throws Exception {
        graph.addEdge(a, b, i1);
        assertTrue(graph.getEdges().get(0).equals(e1));
    }

    @Test
    public void testgetEdges_twoEl() throws Exception {
        graph.addEdge(a, b, i1);
        graph.addEdge(b, c, i2);
        assertTrue(graph.getEdges().get(0).equals(e2));
    }

    @Test
    public void testgetAdjNodes_oneEl() throws Exception {
        graph.addNode(a);
        graph.addNode(b);
        graph.addEdge(a, b, i1);
        assertTrue(graph.getAdjNodes(a).get(0).equals(b));
    }

    @Test
    public void testgetAdjNodes_twoEl() throws Exception {
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addEdge(a, b, i1);
        graph.addEdge(a, c, i3);
        assertTrue(graph.getAdjNodes(a).get(1).equals(c));
    }

    public void testLabel() throws Exception {
        graph.addEdge(a, b, i1);
        assertTrue(graph.label(e1.getSource().getValue(), e1.getDestination().getValue()).equals(1));
    }
    
}
