import java.util.Random;
import java.util.*;

public class Greedy {
    //creates a graph randomly
    //should import from Graph.java instead
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
    //helper method to count the amount of edges it shoud find
    public static int count_ones(int[][] graph, int vertex, int vertices)
    {
        int num_ones = 0;
        for(int i = 0; i < vertices; i++)
        {
            if(graph[i][vertex]==1)
            {
                num_ones++;
            }
        }
        return num_ones;
    }
    //returns list of vertexes that have an edge to the vertex passed to the function
    public static int[] find_edges(int[][] graph, int vertex, int vertices)
    {
        int size = 0;
        int current_index = 0;
        size = count_ones(graph, vertex, vertices);
        System.out.println("\nsize " + size);
        int[] result = new int [size];
        for (int i = 0; i< vertices; i++)
        {
            if((graph[vertex][i]== 1)||(graph[i][vertex]==1))
            {
                //System.out.println("found edge");
                //System.out.println(graph[vertex][i]);
                result[current_index] = i;
                current_index++;

            }

        }

        return result;
    }
    
    //count unvisited edges
    public static int count_unvisited(int[][] graph, int vertex, int vertices, int[] visited)
    {
        int num_unvisited = 0;
        for(int i = 0; i < vertices; i++)
        {
            if(graph[i][vertex]==1)
            {
                boolean edge_visited = visited[i] ==1;
                if(!edge_visited)
                {
                    num_unvisited++;
                }
            }
            
        }
        return num_unvisited;
    }
    //find unvisited edges
    public static int[] find_unvisited_edges(int[][] graph, int vertex, int vertices, int[] visited)
    {
        int current_index = 0;
        int size = count_unvisited(graph, vertex, vertices, visited);
        System.out.println("\nsize " + size);
        int[] result = new int [size];
        for (int i = 0; i< vertices; i++)
        {
            if((graph[vertex][i]== 1))
            {
                boolean edge_visited = visited[i] ==1;
                if(!edge_visited)
                {
                        result[current_index]= i;
                        current_index++;
                }
            
            }

        }

    

        return result;
    }
    //initializes visited array with 0
    public static int[] fill_array(int[] array)
    {
        for(int i = 0; i < array.length; i++ )
        {
            array[i] = 0;
        }
        return array;
    }
    
    //code greedy algorithm here 
    //for debugging store path

    public static void main( String args[] ) 
    {
        
        //testing with a graph of size 5
        int vertex = 0;
        int vertices = 5;
        int five_graph[][] = create_graph(vertices);
        for(int i=0; i < vertices; i++){
            for(int j = 0; j < vertices;  j++){
                System.out.print(five_graph[i][j]+" ");
            }
            System.out.println();
        }
        int five_edges[] = find_edges(five_graph, vertex, vertices);
        System.out.println("all edges");
        for(int i = 0; i < five_edges.length; i++){
            System.out.print(five_edges[i]);
            
        }
       
        //in the array below only thee second edge has been visited so far
        int visited[] = new int[] {0,1,0,1,0};
        //need to create a helper method to change a 0 to a 1 when passed an int and the visited array
        int[] unvisited_edges = find_unvisited_edges(five_graph, vertex, vertices,visited);
        System.out.println("unvisited edges");
        for(int i = 0; i < unvisited_edges.length; i++){
            System.out.print(unvisited_edges[i]);
        }



    }
}
