package fr.xen0xys.models.algorithms;

import fr.xen0xys.models.Computer;
import fr.xen0xys.models.Rod;

import java.awt.*;
import java.util.List;

public class BubbleSort implements Algorithm{

    private final Computer computer;
    private final List<Rod> rods;
    private int currentIndex = 0;

    public BubbleSort(Computer computer, List<Rod> rods) {
        this.computer = computer;
        this.rods = rods;
    }

    @Override
    public void step() {
        this.currentIndex++;
        this.rods.forEach(rod -> rod.setColor(Color.WHITE));
        if(this.currentIndex > this.rods.size() - 1){
            this.currentIndex = 0;
            this.computer.stop();
            return;
        }
        this.rods.get(this.currentIndex).setColor(Color.GREEN);
        if(this.currentIndex > 0){
            this.rods.get(this.currentIndex - 1).setColor(Color.RED);
            if(this.rods.get(this.currentIndex).getValue() < this.rods.get(this.currentIndex - 1).getValue()){
                this.swap(this.currentIndex, this.currentIndex - 1);
                this.currentIndex = 0;
            }
        }
    }

    private void swap(int index1, int index2){
        Rod rod1 = this.rods.get(index1);
        Rod rod2 = this.rods.get(index2);
        this.rods.set(index1, rod2);
        this.rods.set(index2, rod1);
        Color color1 = rod1.getColor();
        Color color2 = rod2.getColor();
        rod1.setColor(color2);
        rod2.setColor(color1);
    }
}
