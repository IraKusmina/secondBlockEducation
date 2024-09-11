package model;

import java.util.ArrayList;

public class Nursery {
    ArrayList<Animal> nurseryAnimal = new ArrayList<>();
    int count = 0;

    public ArrayList<Animal> getNurseryAnimal() {
        return nurseryAnimal;
    }

    public void addAnimal(Animal animal){
        nurseryAnimal.add(animal);
        count = count + 1;
    }

    public int getCount(){
        return count;
    }
}
