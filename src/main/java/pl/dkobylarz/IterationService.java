package pl.dkobylarz;

public class IterationService {
    private int currentIteration = 0;

    public synchronized void iterate() {
        setCurrentIteration(getCurrentIteration() + 1);
    }

    public int getCurrentIteration() {
        return currentIteration;
    }

    public void setCurrentIteration(int currentIteration) {
        this.currentIteration = currentIteration;
    }
}