package fr.xen0xys.models.algorithms;

import fr.xen0xys.models.Computer;
import fr.xen0xys.models.Rod;

import java.awt.*;
import java.util.List;

public class InsertionSort implements Algorithm{
    private final Computer computer;
    private final java.util.List<Rod> rods;
    private int currentIndex = 0;
    private int sortedIndex = 0;

    private int insertionIndex = 0;

    public InsertionSort(Computer computer, List<Rod> rods) {
        this.computer = computer;
        this.rods = rods;
    }

    @Override
    public void step() {
        this.rods.forEach(rod -> rod.setColor(Color.WHITE));
        if(this.currentIndex > this.rods.size() - 1){
            this.computer.stop();
            return;
        }
        this.rods.get(this.currentIndex).setColor(Color.GREEN);
        this.rods.get(this.insertionIndex).setColor(Color.RED);
        if(this.rods.get(this.currentIndex).getValue() < this.rods.get(this.insertionIndex).getValue()){
            this.insert(this.insertionIndex);
            this.insertionIndex = 0;
            this.currentIndex++;
            return;
        }
        if(this.insertionIndex == this.sortedIndex){
            this.insert(this.insertionIndex);
            this.insertionIndex = 0;
            this.currentIndex++;
            return;
        }
        this.insertionIndex++;
        if(sortedIndex == this.rods.size()){
            this.computer.stop();
        }
    }

    private void insert(int index){
        this.sortedIndex++;
        Rod rod = this.rods.get(this.currentIndex);
        this.rods.remove(this.currentIndex);
        this.rods.add(index, rod);
        Color color = rod.getColor();
        rod.setColor(Color.RED);
        this.rods.get(index).setColor(color);
    }
}
