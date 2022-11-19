
package GraphFrameWork;

import LSRoutingApp.Router;

public class Edge {

    private int weight;
    
    
    private Router Source;
    
    
    private Router Target;
//================================================================
    
    public Edge(Router Source, Router Target, int weight) {
        this.weight = weight;
        this.Source = Source;
        this.Target = Target;
    }

    public Edge(Router v) {
        this.weight = 0;
        this.Source = v;
        this.Target = v;
    }
//================================================================
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Router getSource() {
        return Source;
    }

    public void setSource(Router Source) {
        this.Source = Source;
    }

    public Router getTarget() {
        return Target;
    }

    public void setTarget(Router Target) {
        this.Target = Target;
    }
    public void displayInfo() {

    }
}
