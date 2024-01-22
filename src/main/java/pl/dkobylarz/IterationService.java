package pl.dkobylarz;

public class IterationService {
    private static int currentIteration = 0;

    public static synchronized void iterate() {
        currentIteration = getCurrentIteration() + 1;
    }

    public static int getCurrentIteration() {
        return currentIteration;
    }
}