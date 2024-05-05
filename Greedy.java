import java.util.Random;
//import java.util.*;

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
        //System.out.println("\nsize " + size);
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
        //System.out.println("\nsize " + size);
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
    
  
    public static boolean hamiltonian_path(int[][] graph, int vertices) {
        int[] path = new int[vertices];
        path = fill_array(path);
        int path_index = 0;
        int lowest_vertex = -1;
        int num_visited = 0;
        int num_one_edges = 0;
        int[] visited = new int[vertices];
        visited = fill_array(visited);
    
        //find  starting vertex with the lowest degree
        int lowest_edges = Integer.MAX_VALUE;
        for (int i = 0; i < vertices; i++) {
            int edges = count_ones(graph, i, vertices);
            //no path if a vertex has no edge
            if(edges == 0)
            {
                System.out.println("isolated vertex "+ i);
                return false;
            }
            else if(edges == 1)
            {
                //System.out.println("one edge vertex "+ i);
                num_one_edges++;
            }
            if (edges < lowest_edges) {
                lowest_edges = edges;
                lowest_vertex = i;
            }
        }
        //no path if more than 2 vertexes are degree 1
        if(num_one_edges>2)
        {
            System.out.println("Too many vertices with degree 1: "+ num_one_edges +" vertices");
            return false;
        }
    
        // Start the path
        visited[lowest_vertex] = 1;
        num_visited++;
        int current_vertex = lowest_vertex;
        path[path_index] = current_vertex;
        path_index++;
    
        // Begin loop
        while (num_visited < vertices) {
            int[] connected_unvisited = find_unvisited_edges(graph, current_vertex, vertices, visited);
            lowest_vertex = -1;
            lowest_edges = Integer.MAX_VALUE;
    
            // Find the next vertex with the lowest degree
            for (int j : connected_unvisited) {
                int edges = count_unvisited(graph, j, vertices, visited);
                if (edges < lowest_edges) {
                    lowest_edges = edges;
                    lowest_vertex = j;
                }
            }
    
            // Check if there's no next vertex to visit
            if (lowest_vertex == -1)
            {
                //print path and length of path then return false
                System.out.print("Path: ");
                for (int i = 0; i < path_index; i++) 
                {
                    System.out.print(path[i] + " ");
                }
                System.out.println();
                //If not a full Hamiltonian Path, necessary for determining how well the greedy algorithm did
                System.out.println("No path found");
                System.out.println("Path length: " + path_index);
                return false;
            }
                
    
            current_vertex = lowest_vertex;
            visited[lowest_vertex] = 1;
            num_visited++;
            path[path_index] = current_vertex;
            path_index++;
        }
        
        // Check if all vertices are visited
        if (num_visited == vertices) 
        {
            //print path
            System.out.print("Hamiltonian Path: ");
            for (int i = 0; i < vertices; i++) 
            {
                System.out.print(path[i] + " ");
            }
            System.out.println();
            return true;
        }
        System.out.print("Path: ");
        for (int i = 0; i < path_index; i++) 
        {
            System.out.print(path[i] + " ");
        }
        System.out.println();
        //If not a full Hamiltonian Path, necessary for determining how well the greedy algorithm did
        System.out.println("No path found");
        System.out.println("Path length: " + path_index);
        return false;
    }
    
    public static void main( String args[] ) 
    {
        int vertices = 5;
        int[][] new_graph = create_graph(vertices);
        boolean has_path = hamiltonian_path(new_graph, vertices);
        System.out.println("\nhas path "+has_path);
        //print graph
        for(int i=0; i < vertices; i++){
            for(int j = 0; j < vertices;  j++){
                System.out.print(new_graph[i][j]+" ");
            }
            System.out.println();
        }


        
    }
}
