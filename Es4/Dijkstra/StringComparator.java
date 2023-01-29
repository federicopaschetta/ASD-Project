package dijkstra;
import java.util.Comparator;


public class StringComparator implements Comparator<String> {
    
    /** 
     * @param s1 string given as first parameter
     * @param s2 string given as second parameter
     * @return int -1 if s1 is minor than s2 (character by character), 1 if s1 is greater, 0 if they are equal
     */
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}
