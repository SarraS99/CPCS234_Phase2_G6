package GraphFrameWork;

public class Vertex {

    public char label;
    private boolean isVisited;
    private int intLabel;

//================================================================
    public Vertex() {
    }

    public Vertex(char label, boolean isVisited) {
        this.label = label;
        this.isVisited = isVisited;
    }

    public Vertex(int intLabel, boolean isVisited) {
        this.intLabel = intLabel;
        this.isVisited = isVisited;

    }

    public Vertex(char label) {
        this.label = label;
        this.isVisited = true;
    }
//================================================================

    public void setIntLabel(int intLabel) {
        this.intLabel = intLabel;
    }

    public int getIntLabel() {
        return intLabel;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }
    
    public boolean isIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public int getVertPos(Vertex v) {
        return intLabel;
    }
    public void displayInfo() {
        
    }
}
