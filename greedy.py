def hasEdges(graph, i, j):
    return True

def hasHamiltonianPath(graph):
    y = len(graph)
    x = len(graph[0])
    hasPath = True
    
    for i in range(0, y):
        for j in range(0, x):
            if hasEdges(graph, i, j):
                hasPath = True
            else:
                hasPath = False
    
    return hasPath
    

def main():
    graph = [[0]*5]*5
    graph =  [
        [0,1,0,1,0],
        [1,0,1,1,1],
        [0,1,0,0,1],
        [0,1,0,0,1],
        [0,1,1,1,0]
    ]
    print(graph)
    
    if hasHamiltonianPath(graph):
        print("Graph has a Hamiltonian path")
    else:
        print("Graph does not have a Hamiltonian path")

main()