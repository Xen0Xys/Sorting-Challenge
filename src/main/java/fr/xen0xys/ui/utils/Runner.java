package fr.xen0xys.ui.utils;

import com.google.common.collect.EvictingQueue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unused")
public class Runner extends Thread{

    private final Runnable action;
    private final double aps;
    private final boolean stability;

    private boolean running = false;
    private boolean paused = false;
    private int passedActions = 0;
    private final Queue<Long> apsQueue;
    private final ReentrantLock queueLock = new ReentrantLock();

    /**
     * Create a new Runner
     * @param action The action to run
     */
    public Runner(@NotNull final Runnable action){
        this(action, 60, true);
    }

    /**
     * Create a new Runner
     * @param action The action to run
     * @param aps The number of actions per second
     * @param stability If the runner should be stable
     */
    public Runner(@NotNull final Runnable action, @Range(from = 0, to=10000) final int aps, final boolean stability){
        this.action = action;
        this.aps = 1D/aps;
        this.stability = stability;
        this.apsQueue = EvictingQueue.create(aps);
    }

    @Override
    public void run() {
        this.running = true;
        long nextAction = (long) (System.nanoTime() + this.aps * 1_000_000_000L);
        long start = System.nanoTime();
        while (running){
            if(!paused){
                if(System.nanoTime() >= nextAction){
                    this.action.run();
                    this.passedActions++;
                    if(this.stability)
                        nextAction = (long) (nextAction + this.aps * 1_000_000_000L);
                    else
                        nextAction = (long) (System.nanoTime() + this.aps * 1_000_000_000L);
                    long end = System.nanoTime();
                    this.queueLock.lock();
                    try {
                        this.apsQueue.add(end - start);
                    } finally {
                        this.queueLock.unlock();
                    }
                    start = System.nanoTime();
                }
            }
            Thread.yield();
        }
        this.apsQueue.clear();
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    public void stopRunner(){
        this.running = false;
    }

    public boolean isRunning() {
        return running;
    }
    public int getPassedActions() {
        return passedActions;
    }
    public int getCurrentAps() {
        return (int) (1D / (computeAverage() / 1_000_000_000D));
    }

    private double computeAverage() {
        long sum = 0;
        int count = this.apsQueue.size();
        this.queueLock.lock();
        try{
            for (long element : this.apsQueue)
                sum += element;
        }finally {
            this.queueLock.unlock();
        }
        if (count > 0)
            return (double) sum / count;
        else
            return -1_000_000_000;
    }
}
