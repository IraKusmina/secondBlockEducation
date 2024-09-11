package controller;

import java.util.List;

import model.Animal;
import service.NurseryService;

public class NurseryController {

    private NurseryService nurseryService = new NurseryService();

    public void addAnimal(Animal animal) {
        nurseryService.addAnimal(animal);
    }

    public Animal getAnimal(String name){
        return nurseryService.getAnimal(name);
    }

    public void getCount(){
        System.out.println(nurseryService.getCount());
    }

    public void teachNewCommand(Animal animal, String command) {
        nurseryService.teachNewCommand(animal, command);
    }

    public void getAnimalByDate(String date){
        nurseryService.getAnimalByDate(date);
    }

    public void readFile(){
        nurseryService.readFile();
    }

    public void writeToFile(){
        nurseryService.writeToFile();
    }
    
}
