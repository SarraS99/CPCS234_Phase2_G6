
package LSRoutingApp;

import GraphFrameWork.DijkstraAlg;
import GraphFrameWork.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LSRoutingApp {
    static Graph g;
    
    public static void main(String[] args) throws FileNotFoundException {
        //create an object of Dijkstra algorithm class 
        DijkstraAlg p = null;
         
         // variables to calculate running time*
        double startTime, finishTime, time;
        
        
        
        //menu scanner
        Scanner input = new Scanner(System.in);
        
    // variable for the menu choice from 1-3 
        int choice = 0;
        while (choice != 3) {
            System.out.println("");
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     CPCS324 Project Phase 2  ");
            System.out.println("------------------------------------------------------------------");
            System.out.println("Choose one of the following options: ");
            System.out.println(" 1- Read the graph vertecies and edges from the file. ");
            System.out.println(" 2- Choose a specific number of vertecies and edges. ");
            System.out.println(" 3- End the program. ");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice == 1) {
                //create a graph
                g = new Graph();
                File myFile = new File("file.txt");
                //call readGraphFromFile to read from file
                g.readGraphFromFile(myFile);
                // send the g to the DijkstraAlg function
                 p = new DijkstraAlg(g);
                //to inform the method that this is a graph of a read method 
                p.setReadFile(true);
                // start calculating time 
                startTime = System.currentTimeMillis();
                //compute the the shortest path using the computeDijkstraAlg
                p.computeDijkstraAlg(g);
                // end calculating the time 
                finishTime = System.currentTimeMillis();
                // compute the total time 
                time = finishTime - startTime;
                // print the total time 
                System.out.println("---------------------------------------");
                System.out.println("The Runtime (in MilliSecond) of n= " + g.getVerticesNo() + " and m= " + g.getEdgeNo() + " :" + time);

            } else if (choice == 2) {

                System.out.println("Choose the number of vertecies and edges from the following list: ");
                System.out.println(" (n) is number of vertecies, (m) is number of edges ");
                System.out.println(" 1- n=2000 , m=10000");
                System.out.println(" 2- n=3000 , m=15000");
                System.out.println(" 3- n=4000 , m=20000");
                System.out.println(" 4- n=5000 , m=25000");
                System.out.println(" 5- n=6000 , m=30000");
                System.out.println("");
                System.out.print("Enter your choice: ");
                // 
                int option = input.nextInt();
                
                // a switch for every option 
                // for each option give the n,m and creat a graph and call the Dijkstra  
                switch (option) {
                    
                    case 1:
                        
                        g = new Graph(2000, 10000);
                        p = new DijkstraAlg(g);
                        p.setReadFile(false); 
                        
                        // start calculating time 
                        startTime = System.currentTimeMillis();
                        //compute the the shortest path using the computeDijkstraAlg
                        p.computeDijkstraAlg(g);
                       // end calculating the time 
                        finishTime = System.currentTimeMillis();
                        // compute the total time 
                        time = finishTime - startTime;
                        System.out.println("---------------------------------------");
                        System.out.println("The Runtime (MilliSecond)of n=2000, m=10000: " + time);
                        break;
                    case 2:
                        
                        g = new Graph(3000, 15000);
                        p = new DijkstraAlg(g);
                        p.setReadFile(false);
                        startTime = System.currentTimeMillis();
                        
                        p.computeDijkstraAlg(g);
                        finishTime = System.currentTimeMillis();
                        time = finishTime - startTime;
                        System.out.println("---------------------------------------");
                        System.out.println("The Runtime (MilliSecond)of n=3000, m=15000: " + time);
                        break;
                    case 3:
                        
                        g = new Graph(4000, 20000);
                        p = new DijkstraAlg(g);
                        p.setReadFile(false);
                        startTime = System.currentTimeMillis();
                        
                        p.computeDijkstraAlg(g);
                        finishTime = System.currentTimeMillis();
                        time = finishTime - startTime;
                        System.out.println("---------------------------------------");
                        System.out.println("The Runtime (MilliSecond)of n=4000, m=20000: " + time);
                        break;
                    case 4:
                        
                        g = new Graph(5000, 25000);
                        p = new DijkstraAlg(g);
                        p.setReadFile(false);
                        startTime = System.currentTimeMillis();
                        
                        p.computeDijkstraAlg(g);
                        finishTime = System.currentTimeMillis();
                        time = finishTime - startTime;
                        System.out.println("---------------------------------------");
                        System.out.println("The Runtime (MilliSecond)of n=5000, m=25000: " + time);
                        break;
                    case 5:
                        
                        g = new Graph(6000, 30000);
                        p = new DijkstraAlg(g);
                        p.setReadFile(false);
                        startTime = System.currentTimeMillis();
                        
                        p.computeDijkstraAlg(g);
                        finishTime = System.currentTimeMillis();
                        time = finishTime - startTime;
                        System.out.println("---------------------------------------");
                        System.out.println("The Runtime (MilliSecond) of n=6000, m=30000: " + time);
                        break;
                    default:
                        System.out.println("You entered a wrong choice .. try again ");

                }// end switch

                
            }// end if (choice ==2 )
        }// end of while loop

        System.out.println("");
        System.out.println("------------ END OF PROGRAM ------------");
        System.out.println("     Thanks for using our program..");

    }
    // this function is to print the shortest path of every v in the graph only for req1 when the read is ture
    public void printMatrixAfterDijkstraAlgorithm(int[] distance, boolean readFile, String[] Result) {
        //print all the distances with the pathes
        System.out.println("\n***************** Dijkstra Algorithm *****************");
        System.out.println("The source router is A");
        if (readFile) {
            for (int i = 0; i < g.getAdjMatrixLen(); i++) {
                System.out.println(Result[i] + " route lenght: " + distance[i]);
            }
            System.out.println();
        }
    }
    
}
