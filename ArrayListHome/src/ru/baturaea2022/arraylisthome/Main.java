package ru.baturaea2022.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Прочитать в список все строки из файла
        ArrayList<String> listStrings = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/ru/baturaea2022/arraylisthome/input.txt"))) {
            while (scanner.hasNextLine()) {
                listStrings.add(scanner.nextLine());
            }

            System.out.println("Прочитанный из файла список: ");
            System.out.println(listStrings);
        } catch (FileNotFoundException e) {
            System.out.println("File 'input.txt' not found.");
        }

        //Есть список из целых чисел. Удалить из него все четные числа.
        ArrayList<Integer> listIntegers = new ArrayList<>(Arrays.asList(2, 1, 7, 7, 3, 4, 3, 5, 6, 6, 7, 8, 7));
        System.out.println("Исходный список чисел: ");
        System.out.println(listIntegers);

        for (int i = 0; i < listIntegers.size(); i++) {
            if (listIntegers.get(i) % 2 == 0) {
                listIntegers.remove(i);
                i--;
            }
        }

        System.out.println("Список без четных чисел: ");
        System.out.println(listIntegers);

        //Надо создать новый список, в котором будут элементы первого списка в таком же порядке, но без повторений
        ArrayList<Integer> listUniquesElements = new ArrayList<>(listIntegers.size());

        for (Integer element : listIntegers) {
            if (!listUniquesElements.contains(element)) {
                listUniquesElements.add(element);
            }
        }

        System.out.println("Список без дубликатов: ");
        System.out.println(listUniquesElements);
    }
}