
package LSRoutingApp;
import GraphFrameWork.Vertex;


public class Router extends Vertex {
    
    private String routerName;
    
    
//================================================================
    public Router(char label) {
        super(label);
    }


    public Router() {
    }
    
    
    public Router(char label, boolean isVisited) {
        super(label, isVisited);
    }

    public Router(int position, boolean isVisited) {
        super(position, isVisited);
    }
 //================================================================   
    @Override
    public void displayInfo() {

    }
}
