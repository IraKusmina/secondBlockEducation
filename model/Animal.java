package model;

import java.util.List;

public class Animal {
    private String type;
    private String name;
    private String date;
    private List<String> commands;

    public Animal (String name, String type, String date, List<String> commands){
        this.name = name;
        this.type = type;
        this.date = date;
        this.commands = commands;

    }

    public Animal(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Животное: " + name + " Тип животного: " + type + " Дата рождения: " + date + " Команды: " + commands;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}