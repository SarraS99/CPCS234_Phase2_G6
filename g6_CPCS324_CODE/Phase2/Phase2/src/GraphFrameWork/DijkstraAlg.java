
package GraphFrameWork;
import LSRoutingApp.LSRoutingApp;
import LSRoutingApp.Path;
import LSRoutingApp.Router;



public class DijkstraAlg{
    
    
    private boolean readFile = false; // set the readFile to when req2
    LSRoutingApp ap = new LSRoutingApp(); // object of class LSRoutingApp to use the print function
    
 //============================================================================   
    
    public DijkstraAlg(Graph g) {
        
    }
//==============================================================================
    public void computeDijkstraAlg(Graph graph) {
 
        // the Matrix Size = the AdjMatrix of the graph 
        int MatrixSize = graph.getAdjMatrixLen();

        //fill the adjMatrix 
        for (int i = 0; i < MatrixSize; i++) {
            for (int j = 0; j < MatrixSize; j++) {
                //if its null create s and t 
                if (graph.adjMatrix[i][j] == null) {
                    Router s = null;
                    Router t = null;
                    if (i != j) {
                        //and create an edge with max if it not the vertex 
                        graph.adjMatrix[i][j] = new Path(s, t, Integer.MAX_VALUE);

                    } else {
                        //same vertex = 0 
                        graph.adjMatrix[i][j] = new Path(s, t, Integer.MIN_VALUE);
                    }
                }
            }
        }
            
        
        // distance between the source and every other vertex
        int distance[] = new int[MatrixSize];
        
       // array of string for the final result to be printed
        String[] Result = new String[MatrixSize];

        // 1-this loop is to set the vertex as unvisited 
        for (int i = 0; i < MatrixSize; i++) {
            graph.setVertexIsNotVisited(i);
            // distance=infinity
            distance[i] = Integer.MAX_VALUE;
        }

        // 2- first vertex=source 
        distance[0] = 0;
        
       // if req 1:
        if (readFile) {
            Result[0] = "Rt.A";
        }
        
        // if req 2:
        else {
            Result[0] = "0 ";
        }

        
        // Find shortest path for all vertices
        for (int i = 0; i < MatrixSize; i++) {
            
            // find the minimum distance vertex using the MinDistance function
            int index = MinDistance(distance, graph);

            // set the vertex that has the minDistance as visited 
            graph.setVertexState(index);

            //update distances values
            for (int j = 0; j < MatrixSize; j++) {
           
           //when the vertex is 
           //1- unvisted and 
           //2- its weight: != 999999 or 0  
           //3- the new minDis vertex (edgeweight+distance) is less than the distance of current 
           //then update               
                if (graph.getVertexIsVisited(j) != true && graph.getEdgeWeight(index, j) != Integer.MAX_VALUE && graph.getEdgeWeight(index, j) != 0 && (distance[index] + graph.getEdgeWeight(index, j) < distance[j])) {
                    //update 
                    distance[j] = distance[index] + graph.getEdgeWeight(index, j);
                    // update the Result to = current vertex Result + parent Result
                    if (readFile) {
                        Result[j] = Result[index] + "-Rt." + (char) (j + 65);
                    } else {
                        Result[j] = Result[index] + (j);
                    }//end else 

                }//end if 
            }//end for 

        }//end for 

        // calling the print method to print the result 
        ap.printMatrixAfterDijkstraAlgorithm(distance, readFile, Result);
    }

    public void setReadFile(boolean readFile) {
        this.readFile = readFile;
    }
   
    // the minimum distance function 
    private static int MinDistance(int[] distance, Graph graph) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertexIndex = -1;
        
        //when the vertex is not visited yet and it less than the minDistance make it the new minDISTANCE
        // and store the index 
        for (int i = 0; i < distance.length; i++) {
            if (graph.getVertexIsVisited(i) != true && distance[i] < minDistance) {
                minDistance = distance[i];
                // assign the minimum distance vertex index 
                minDistanceVertexIndex = i;
            }
        }
        return minDistanceVertexIndex;
    }
    
    
}

