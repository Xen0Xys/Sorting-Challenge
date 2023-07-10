package fr.xen0xys.models;

import fr.xen0xys.models.algorithms.*;
import fr.xen0xys.ui.utils.Runner;

import java.util.List;

public class Computer {
    private final Runner runner;

    public Computer(AlgorithmType algorithmType, List<Rod> rods) {
        Algorithm algorithm;
        switch (algorithmType){
            case BUBBLE_SORT -> algorithm = new BubbleSort(this, rods);
            case SELECTION_SORT -> algorithm = new SelectionSort(this, rods);
            case INSERTION_SORT -> algorithm = new InsertionSort(this, rods);
            default -> throw new IllegalStateException("Unexpected value: " + algorithmType);
        }
        this.runner = new Runner(algorithm::step, 20, true);
    }

    public void start(){
        this.runner.start();
    }

    public void stop(){
        this.runner.stopRunner();
    }

    public Runner getRunner() {
        return runner;
    }
}
