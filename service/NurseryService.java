package service;

import model.*;

import java.io.*;
import java.util.ArrayList;

public class NurseryService {
    Nursery nursery = new Nursery();
    int count;

    public void addAnimal(Animal animal){
        nursery.addAnimal(animal);
        System.out.println("Животное " + animal.getName() + ", вид: " + animal.getType() + " добавлен в питомник");
    }

    public int getCount() {
        return nursery.getCount();
    }

    public Animal getAnimal(String name){
        for (Animal animal: nursery.getNurseryAnimal()){
            if (animal.getName().equals(name)){
                return animal;
            }
        }
        return null;
    }

    public void teachNewCommand(Animal animal, String command){
        animal.getCommands().add(command);
    }

    public void getAnimalByDate(String date){
        if (nursery.getNurseryAnimal().isEmpty()){
            System.out.println("По выбранной дате нет животных");
        } else {
            for (Animal animal: nursery.getNurseryAnimal()){
                if (animal.getDate().equals(date)){
                    System.out.println(animal);
                }
            }
        }
    }

    public void readFile() {
        String path = "nursery.txt";
        String [] arr;
        File file = new File(path);
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    arr = line.split(" ");
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (int i = 3; i < arr.length; i++) {
                        arrayList.add(arr[i]);
                    }
                    nursery.addAnimal(new Animal(arr[0], arr[1], arr[2], arrayList));
                    line = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(){
        String path = "nursery.txt";
        String string;
        File f = new File(path);
        try(FileWriter fileWriter = new FileWriter(path)){
                    for (Animal animal: nursery.getNurseryAnimal()){
                        string = animal.getName() + " " + animal.getType() + " " + animal.getDate() + " " +
                                animal.getCommands().toString().replaceAll("^\\[|\\]$", "") + "\n";
                        fileWriter.write(string);
                    }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
