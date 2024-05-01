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
    
    //code greedy algorithm here 
    //for debugging store path
    public static boolean hamiltonian_path(int[][] graph, int vertices)
    {
        //********************************************************************************* */
        //note this currently doesn't work. I think the issue is with recognizing what point
        //to go to next or with one of the helper functions
        //I tested each helper function individually but they aren't combining together properly
        //************************************************************************************ */
        //to store the path for debugging
        int [] path = new int [vertices];
        path = fill_array(path);
        int path_index = 0;
        //boolean has_path = false;
        //lowest edges to store number of edges while loweest vertex stores which vertex
        int lowest_edges = -1;
        int lowest_vertex = -1;
        int num_one_edges = 0;
        int num_visited = 0;
        int[] visited = new int [vertices];
        //initialize visited array
        visited = fill_array(visited);
        // determine if no path is possible and if one is go ahead and find the start point
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
                //System.out.println("one edge vertex "+ i);
                num_one_edges++;
            }
            if(lowest_edges == -1 || edges < lowest_edges)
            {
                lowest_edges = edges;
                lowest_vertex = i;
            }
        }
        System.out.println("total one edge vertexes " + num_one_edges);
        if(num_one_edges>2)
        {
            return false;
        }
        //start the path
        visited[lowest_vertex] =1;
        num_visited++;
        int current_vertex =lowest_vertex;
        path[path_index] = current_vertex;
        path_index++;
        //unvisited vertexes from the current vertex
        int num_unvisited = count_unvisited(graph,current_vertex,vertices,visited);
        
        //begin loop
        while(num_visited < vertices && num_unvisited !=0 )
        {
            System.out.println("\ncurrent "+ current_vertex);
            System.out.println("visited " + num_visited+ " "+ "linked to " +num_unvisited+"\n");
            for(int x = 0; x<visited.length;x++)
            {
                System.out.print(visited[x]);
            }
            //System.out.println("Current vertex "+current_vertex);
            lowest_edges = -1;
            lowest_vertex = -1;
            int[] connected_unvisited = find_unvisited_edges(graph, current_vertex, vertices, visited);

            for(int j = 0; j < connected_unvisited.length; j++)
            {
                int edges = count_unvisited(graph, j, vertices, visited);
                //System.out.println(j+ " has "+edges + " edges");
                if(lowest_edges == -1 || edges < lowest_edges)
                {
                    lowest_edges = edges;
                    lowest_vertex = j;
                }
            }
            current_vertex = lowest_vertex;
            visited[lowest_vertex]= 1;
            num_visited++;
            path[path_index] = current_vertex;
            path_index++;
            num_unvisited = count_unvisited(graph,current_vertex,vertices,visited);

        }
        System.out.println("num_visited = "+ num_visited+"\n");
        for(int x = 0; x<visited.length;x++)
        {
            System.out.print(visited[x]);
        }
    
        if(num_visited == vertices)
        {
            return true;
        }

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
