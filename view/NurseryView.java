package view;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.NurseryController;
import model.*;

public class NurseryView {
    private NurseryController nurseryController = new NurseryController();
    Animal animal;

    public void workConsole() {
        readFile();
        System.out.println();
        System.out.println("Для работы необходимо ввести цифру.");
        System.out.println(
                "1. Добавить животное.\n" +
                        "2. Вывести список команд животного." +
                        "\n3. Обучить животное новой комманде." +
                        "\n4. Вывести список животных по дате. " +
                        "\n5. Показать общее количество животных." +
                        "\n6. Выход");
        System.out.print("Ваш выбор: ");
        Boolean flag = false;
        int num = 0;
        while (flag == false && (num < 1 || num > 5)) {
            try {
                num = Integer.parseInt(useScanner());
                flag = true;
            } catch (Exception e) {
                flag = false;
                System.out.println("Вы невено ввели число. Попробуйте снова!");
            }
        }
        checkChoice(num);

    }

    public void checkChoice(int choice) {
        switch (choice) {
            case 1:
                addAnimalConsole();
                workConsole();
                break;
            case 2:
                String name = inputName();
                try{
                    Animal animal = nurseryController.getAnimal(name);
                    System.out.println("Животное " + name + " умеет делать команды " + animal.getCommands());
                }
                catch(Exception e){
                    System.out.println("Такого животного нет в питомнике");
                }
                workConsole();
                break;
            case 3:
                Boolean flag = false;
                while (flag == false){
                    name = inputName();
                    String answer = "";
                    try{animal = nurseryController.getAnimal(name);
                        if (animal.equals(null)){
                            System.out.println("Такого животного нет в питомнике");
                            answer = "no";
                        } else{
                            System.out.println(animal);
                            System.out.println("Это ваше животное? Введите yes/no");
                            answer = useScanner();
                        }
                    } catch (Exception e){
                        System.out.println("Такого животного нет в питомнике");
                    }
                    if (answer.equals("yes")){
                        System.out.println("Введите команду, которую хотите добавить");
                        String command = useScanner();
                        flag = true;
                        nurseryController.teachNewCommand(animal, command);
                    }
                    else {
                        System.out.println("Попробуйте ввести имя заново. Возможно, вы допустили ошибку");
                    }
                }
                workConsole();
                break;
            case 4:
                System.out.println("Введите дату, по которой хотите найти животных");
                String date = inputDate();
                nurseryController.getAnimalByDate(date);
                workConsole();
                break;
            case 5:
                nurseryController.getCount();
                workConsole();
                break;
            case 6:
                writeToFile();
                System.out.println("До свидания!");
        }
    }

    public void addAnimalConsole() {
        System.out.println(
                "Выберите и введите тип животного c маленькой буквы: \n dog cat, hamster, horse, donkey, camel");
        String type = inputType().toLowerCase();
        String name = inputName().toLowerCase();
        System.out.println("Введите дату рождения животного в формате: ДД.ММ.ГГГГ. Если не знаете точную дату, введите 01");
        String date = inputDate();
        List<String> commands = inputCommands(type);
        for (String string : commands) {
            System.out.println(string);
        }
        Animal animal = findTypeAnimal(name, type, date, commands);
        nurseryController.addAnimal(animal);
    }

    public String inputType() {
        Boolean flag = false;
        while (flag == false) {
            String type = useScanner();
            if (type.equals("dog") || type.equals("cat")|| type.equals("hamster") || type.equals("camel")|| type.equals("horse")
                    || type.equals("donkey")) {
                return type;
            } else {
                System.out.println("Неверно ввели животное. Попробуйте снова.");
            }
        }
        return null;
    }

    public String inputName() {
        String name = "";
        while (name.isEmpty()){
            System.out.println("Введите имя живтоного");
            name = useScanner().toLowerCase();
        }
        return name;
    }

    public String inputDate() {
        boolean flag = false;
        String date = null;
        while (!flag) {
            date = useScanner();
            flag = checkDate(date);
        }
        return date;
    }

    public List<String> inputCommands(String type) {
        System.out.println("Введите через пробел команды, которые умеет животное");
        String commands = useScanner();
        List<String> listOfCommands = new ArrayList<>();
        String[] com = commands.split(" ");
        for (String string : com) {
            listOfCommands.add(string);
        }
        return listOfCommands;
    }

    public boolean checkDate(String info) {
        try {
            String[] newDate = info.split("\\.");
            int day = Integer.parseInt(newDate[0]);
            int month = Integer.parseInt(newDate[1]);
            int year = Integer.parseInt(newDate[2]);
            if (day > 0 && day < 32) {
                if (month > 0 && month <= 12) {
                    if (year > 1900 && year < Year.now().getValue()) {
                        return true;
                    } else {
                        System.out.println("Неверный ввод даты. Попробуйте снова.");
                        return false;
                    }
                } else {
                    System.out.println("Неверный ввод даты. Попробуйте снова.");
                    return false;
                }
            } else {
                System.out.println("Неверный ввод даты. Попробуйте снова.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Вы неверно ввели число попробуйте снова");
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Вы неверно ввели число попробуйте снова");
            return false;
        }
    }

    public String useScanner() {
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        return info;
    }

    public Animal findTypeAnimal(String name, String type, String date, List<String> commands){
        if (type.equalsIgnoreCase("dog")){
            return new Dog(name, type, date, commands);
        } else if (type.equalsIgnoreCase("cat")){
            return new Cat(name, type, date, commands);
        } else if (type.equalsIgnoreCase("hamster")){
            return new Hamster(name, type, date, commands);
        } else if (type.equalsIgnoreCase("camel")){
            return new Camel (name, type, date, commands);
        } else if (type.equalsIgnoreCase("horse")){
            return new Horse(name, type, date, commands);
        } else if (type.equalsIgnoreCase("donkey")){
            return new Donkey (name, type, date, commands);
        }
        return null;
    }

    public void readFile(){
        nurseryController.readFile();
    }

    public void writeToFile(){
        nurseryController.writeToFile();
    }
}
