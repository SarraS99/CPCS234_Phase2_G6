
package GraphFrameWork;
import LSRoutingApp.Path;
import LSRoutingApp.Router;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Graph {

    private int verticesNo;
    private int edgeNo;
    private int edgeCounter;
    private boolean isDigraph;
    private Router[] vertices = null;
    static Path[][] adjMatrix = null;
//====================================================================

    public Graph(int verticesNo, int edgeNo) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        this.isDigraph = true;
        this.edgeCounter = 0;
        adjMatrix = new Path[verticesNo][verticesNo];
        makeGraph(verticesNo, edgeNo);
    }

    public Graph() {

    }

 //============================================================================= 
    public void readGraphFromFile(File file) throws FileNotFoundException {
        Scanner read = new Scanner(file);

        // get the graph type if it is directed or not
        int digraph = Integer.parseInt(read.nextLine().substring(8));
        if (digraph == 0) {
            this.setIsDigraph(false);
        }
        else {
            this.setIsDigraph(true);
        }
        
        //1-read number of verties 
        int verNo = read.nextInt();
        this.setVerticesNo(verNo);
        this.setAdjMatrixSize(verNo); 
        //2-cerate the array of vertices 
        vertices = new Router[verticesNo];
        //3- read number of edges 
        int edgeNum = read.nextInt();
        this.setEdgeNo(edgeNum);
        
       
        
        //read the graph 
        while (read.hasNext()) {
            // read source target weight 
            char source = read.next().charAt(0);
            char target = read.next().charAt(0);
            int w = Integer.parseInt(read.next());
            
            // create objects of router
            Router src = new Router(source);
            Router targ = new Router(target);

            //save and set the intlable of the vertecies in the array
            
            src.setIntLabel(source - 'A');
            
            targ.setIntLabel(target - 'A'); 
            
            //call add edge function to add edges
            //send 1 as a req no which is for req 1
             addEdge(src, targ, w, 1);

        }//end while 

       read.close(); 
    }//End method 


    public void makeGraph(int verticesNo, int edgeNo) {

        //cerate array of vertices 
        vertices = new Router[verticesNo];

        //a loop to create vertices
        for (int i = 0; i < vertices.length; i++) {

            Router v = new Router(i, false);

            vertices[i] = v;
        }  
        
        //creating edges 
        // *it has to connects the created vertices randomly 
        // *first we will connect the all vertices with each other 
        // * then well conntect the first and last vertex
        for (int i = 0; i < vertices.length - 1; i++) {
            Router s = vertices[i];
            Router t = vertices[i + 1];
            //the weight is randomly generated
            int wt = (int) (Math.random() * 50);
            Path e = new Path(s, t, wt);
            adjMatrix[i][i + 1] = e;
            edgeCounter++;
        // if its not directed then for every edge(s,t) there is edge (t,s) 
            if (!isDigraph) {

                Path e2 = new Path(t,s , wt);
                adjMatrix[i][i + 1] = e2;
                edgeCounter++;

            }
        }//end for
        
        //connecting the last vertex with the first one
        Router s = vertices[vertices.length - 1];
        Router t = vertices[0];
        int wt = (int) (Math.random() * 50);
        Path e = new Path(s, t, wt);
        adjMatrix[vertices.length - 1][0] = e;
        edgeCounter++; 
        //if its not directed add e2
        if (!isDigraph) {

                Path e2 = new Path(t,s , wt);
            adjMatrix[vertices.length - 1][0] = e2;
                edgeCounter++; }

        // now we have made sure the graph is connected we have to make sure the edges are 
        //the spesifed number and random  
                for (; edgeNo < edgeCounter; ) {
            //randomlly 
            int source = (int) (Math.random() * verticesNo);
            int target = (int) (Math.random() * verticesNo);

              //the weight is randomly generated
             int w = (int) (Math.random() * 500);
             //when the graph is digraph then one edge, and when not 2
             if (isDigraph) {
                addEdge(vertices[source], vertices[target], w,2);
            }
             if (!isDigraph) { 

                 addEdge(vertices[source], vertices[target], w,2);
                 addEdge(vertices[target], vertices[source], w,2);

            }
             
        }

    } 

    //----------------------------------------------------------------------------------------------
    public void addEdge(Router Source, Router Target, int w, int reqNO) {
        //create routers 
        Router s = null;
        Router t = null;
        Path e = null;
        
        // if the reqNO= 2 then this means that the calling is from make graph method
        if (reqNO == 2) {
            //use chackEdge method to check if the edge does exsist or not 
            boolean check = checkEdge(Source.getIntLabel(), Target.getIntLabel(), adjMatrix);
            
            if (check == false) {//then there is no edge so we make one 
                s = vertices[Source.getIntLabel()];
                t = vertices[Target.getIntLabel()];
                e = new Path(s, t, w); // new edge

            }

            // if the graph is directed
            if (e != null && isDigraph) {
                //adding the edge to the source vertex only 
                adjMatrix[Source.getIntLabel()][Target.getIntLabel()] = e;
                edgeCounter++;
            }
            // if the graph is not directed 
            else if (isDigraph == false && e != null) {
                Path edge2 = new Path(e.getTarget(), e.getSource(), w); //new path (t,s)
                // add the edge to source and target 
                adjMatrix[Source.getIntLabel()][Target.getIntLabel()] = e;
                adjMatrix[Target.getIntLabel()][Source.getIntLabel()] = edge2;
                edgeCounter = edgeCounter + 2;
            }

        } 

        if (reqNO == 1) {
            // if the graph is directed
            if (isDigraph) {
                //if the source vertex= null make one
                if (vertices[Source.getIntLabel()] == null) {
                    
                    vertices[Source.getIntLabel()] = Source;
                }
                //if the target vertex= null make one
                if (vertices[Target.getIntLabel()] == null) {
                    vertices[Target.getIntLabel()] = Target;
                  
                }
                // make a new path
                e = new Path(Source,Target, w);
                // add the path to the adjMatrix 
                adjMatrix[Source.getIntLabel()][Target.getIntLabel()] = e;
                edgeCounter++;
            }
            // if undirected
            if (isDigraph == false) {
             
                if (vertices[Source.getIntLabel()] == null) {
                 
                    vertices[Source.getIntLabel()] = Source;
                }
                if (vertices[Target.getIntLabel()] == null) {
                    
                    vertices[Target.getIntLabel()] = Target;
                }
                //make two paths 
                e = new Path(Source,Target, w);
                Path edge2 = new Path(Target,Source, w);
                // add path to the adjmatrix for source and target 
                adjMatrix[Source.getIntLabel()][Target.getIntLabel()] = e;
                adjMatrix[Target.getIntLabel()][Source.getIntLabel()] = edge2;
                edgeCounter = edgeCounter + 2;

            }

        }

    }// End Method
    // returns false when the edge does not exists
    public boolean checkEdge(int Source, int Target, Path [][] adjMatrix) {
        boolean check = false;
        for (int i = 0; i < edgeCounter; i++) {
            if (Source == Target) {
                return check = true;
            }//end if
            if (adjMatrix[Source][Target] == null) {
                return check = false;
            }//end if
            else {
                return check = true;
            }//end else 
        }
        return check;
    }
//==============================================================================
    
    public void setAdjMatrixSize(int size) {
        adjMatrix = new Path[size][size];
    }
   
    public int getVerticesNo() {
        return verticesNo;
    }

    public void setVerticesNo(int verticesNo) {
        this.verticesNo = verticesNo;
    }

    public int getEdgeNo() {
        return edgeNo;
    }

    public void setEdgeNo(int edgeNo) {
        this.edgeNo = edgeNo;
    }
    
    //check if the graph is directed or not 
     
    public boolean isIsDigraph() {
        return isDigraph;
    }

    public void setIsDigraph(boolean isDigraph) {
        this.isDigraph = isDigraph;
    }

    
    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Router[] vertices) {
        this.vertices = vertices;
    }

    public Path[][] getAdjMatrix() {
        return adjMatrix;
    }

    public void setAdjMatrix(Path[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }
    //returns the edge weight 
    public int getEdgeWeight(int i, int j) {
        if (i >=0 && j >=0) {
            return adjMatrix[i][j].getWeight();
        }
       return 0;
    }

    public int getAdjMatrixLen() {
        return adjMatrix.length;
    }

    public void setVertexIsNotVisited(int i) {
       vertices[i].setIsVisited(false);
    }
    
    public void setVertexState(int i) {
        if (i >= 0) {
            vertices[i].setIsVisited(true);
        } 
    }

    public boolean getVertexIsVisited(int i) {
        return vertices[i].isIsVisited();
    }

}
