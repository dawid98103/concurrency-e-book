package pl.dkobylarz;

import java.util.concurrent.locks.ReentrantLock;

class IterationService {
    private final ReentrantLock lock = new ReentrantLock();
    private int currentIteration = 0;

    public void iterate() {
        lock.lock();
        try {
            setCurrentIteration(getCurrentIteration() + 1);
        } finally {
            lock.unlock();
        }
    }

    public int getCurrentIteration() {
        return currentIteration;
    }

    public void setCurrentIteration(int currentIteration) {
        this.currentIteration = currentIteration;
    }
}