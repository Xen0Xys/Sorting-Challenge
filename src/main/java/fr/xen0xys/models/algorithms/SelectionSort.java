package fr.xen0xys.models.algorithms;

import fr.xen0xys.models.Computer;
import fr.xen0xys.models.Rod;

import java.awt.*;
import java.util.List;

public class SelectionSort implements Algorithm{

    private final Computer computer;
    private final java.util.List<Rod> rods;
    private int currentIndex = 0;
    private int sortedIndex = -1;

    private int minIndex = 0;

    public SelectionSort(Computer computer, List<Rod> rods) {
        this.computer = computer;
        this.rods = rods;
    }

    @Override
    public void step() {
        this.currentIndex++;
        this.rods.forEach(rod -> rod.setColor(Color.WHITE));
        if(this.sortedIndex > this.rods.size() - 2){
            this.computer.stop();
            return;
        }
        this.rods.get(this.minIndex).setColor(Color.RED);
        if(this.currentIndex > this.rods.size() - 1){
            this.swap(this.minIndex);
            this.minIndex = this.sortedIndex + 1;
            this.currentIndex = this.sortedIndex + 1;
            return;
        }
        // Min search
        this.rods.get(this.currentIndex).setColor(Color.GREEN);
        if(this.rods.get(this.currentIndex).getValue() < this.rods.get(this.minIndex).getValue()){
            this.minIndex = this.currentIndex;
            this.rods.get(this.minIndex).setColor(Color.RED);
        }
    }

    private void swap(int minIndex){
        this.sortedIndex++;
        Rod rod1 = this.rods.get(minIndex);
        Rod rod2 = this.rods.get(sortedIndex);
        this.rods.set(minIndex, rod2);
        this.rods.set(sortedIndex, rod1);
        Color color1 = rod1.getColor();
        Color color2 = rod2.getColor();
        rod1.setColor(color2);
        rod2.setColor(color1);
    }
}
