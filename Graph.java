import java.util.*;
import java.util.Random;

public class Graph{

    public static int[][] create_graph(int vertices) {
        int[][] graph = new int [vertices][vertices];
        Random rand = new Random();
        for(int i=0; i < vertices; i++){
            for(int j = 0; j < vertices;  j++){
                int random = rand.nextInt(2);
                if(i==j)
                {
                    graph[i][j]=0;
                }
                else{
                    graph[i][j]= random;
                    graph[j][i] = random;
                }
                
            }
        }
        return graph;
    };
    public static void main( String args[] ) {
        int vertices = 7;
        int new_graph[][] = create_graph(vertices);
        for(int i=0; i < vertices; i++){
            for(int j = 0; j < vertices;  j++){
                System.out.print(new_graph[i][j]+" ");
            }
            System.out.println();
        }

    }
}
