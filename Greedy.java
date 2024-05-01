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
    public static boolean hamiltonian_path(int[][] graph, int vertices)
    {
        boolean has_path = false;
        int start = -1;
        int num_one_edges = 0;
        for(int i = 0; i < vertices; i++)
        {
            int edges = count_ones(graph, i, vertices);
            if(edges == 0)
            {
                System.out.println("isolated vertex "+ i);
                return false;
            }
            else if(edges == 1)
            {
                System.out.println("one edge vertex "+ i);
                start = i;
                num_one_edges++;
            }
        }
        System.out.println("total one edge vertexes " + num_one_edges);
        if(num_one_edges>2)
        {
            return false;
        }
        //check if any have degree 0
            //return false if that is the case
        //if any have degree 1
            //start there
        //find lowest degree to start
        //continue while all_visited = False or current is connected to no unvisited nodes
            //find edges
            //find lowest amount of unvisited nodes edges
                //if some have the same try all 
            //visit that node
            //make it be 1 in visited

        return true;
    }

    public static void main( String args[] ) 
    {
        int vertices = 5;
        int[][] new_graph = create_graph(vertices);
        boolean has_path = hamiltonian_path(new_graph, vertices);
        System.out.println("has path "+has_path);
        //print graph
        for(int i=0; i < vertices; i++){
            for(int j = 0; j < vertices;  j++){
                System.out.print(new_graph[i][j]+" ");
            }
            System.out.println();
        }


        
    }
}
