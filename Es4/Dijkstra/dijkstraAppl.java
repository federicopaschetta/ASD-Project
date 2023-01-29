package dijkstra;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.lang.Double;


public class dijkstraAppl {
    
    /** 
     * Application of Dijkstra algorithm working for a csv given file of String type nodes 
     * and Double type distances
     */
    public static void main (String[]args) {        
        String line="",tmp;
        Graph<String> grafo =  new Graph<String>();
        String directoryName = System.getProperty("user.dir");
        directoryName = "italian_dist_graph.csv";
        System.out.println(directoryName);
        try{
            BufferedReader br = new BufferedReader(new FileReader(directoryName));
            while((line=br.readLine())!=null){
                String[] read = line.split(",");
                tmp=read[2];
                grafo.addNode(read[0]);
                grafo.addNode(read[1]);
                grafo.addEdge(read[0], read[1], Double.parseDouble(tmp));
                grafo.addEdge(read[1], read[0], Double.parseDouble(tmp));
            }
            
            br.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        DijkstraMinPath<String> dijkstra = new DijkstraMinPath<String>();
        StringComparator comp = new StringComparator();
        ArrayList<DijkstraNode<String>> ret = dijkstra.minPath(grafo, "torino", comp);
        for(int i = 0; i<ret.size(); i++) {
            ret.get(i).printNode();
        }
    }
}
