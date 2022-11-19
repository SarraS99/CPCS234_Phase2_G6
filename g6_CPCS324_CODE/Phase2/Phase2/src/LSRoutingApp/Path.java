
package LSRoutingApp;
import GraphFrameWork.Edge;

public class Path extends Edge{
    
    public Path(Router Source, Router Target, int weight) {
        super(Source, Target, weight);
    }
    
    @Override
    public void displayInfo() {

    }
}
