package pl.dkobylarz;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class IterationService {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int currentIteration = 0;

    public void iterate() {
        rwLock.writeLock().lock();
        try {
            setCurrentIteration(getCurrentIteration() + 1);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public int getCurrentIteration() {
        rwLock.readLock().lock();
        try {
            return currentIteration;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void setCurrentIteration(int currentIteration) {
        rwLock.writeLock().lock();
        try {
            this.currentIteration = currentIteration;
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
