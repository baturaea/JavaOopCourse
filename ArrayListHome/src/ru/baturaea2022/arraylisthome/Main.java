package ru.baturaea2022.arraylisthome;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Прочитать в список все строки из файла
        ArrayList<String> stringsList = new ArrayList<>();
        String inputFile = "ArrayListHome/src/ru/baturaea2022/arraylisthome/input.txt";

        try (BufferedReader in =
                     new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = in.readLine()) != null) {
                stringsList.add(line);
            }

            System.out.println("Прочитанный из файла список:");
            System.out.println(stringsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Есть список из целых чисел. Удалить из него все четные числа.
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(2, 1, 7, 7, 3, 4, 3, 5, 6, 6, 7, 8, 7));
        System.out.println("Исходный список чисел:");
        System.out.println(integerList);

        for (int i = 0; i < integerList.size(); i++) {
            if (integerList.get(i) % 2 == 0) {
                integerList.remove(i);
                i--;
            }
        }

        System.out.println("Список без четных чисел:");
        System.out.println(integerList);

        // Надо создать новый список, в котором будут элементы первого списка в таком же порядке, но без повторений
        ArrayList<Integer> uniqueElementsList = new ArrayList<>(integerList.size());

        for (Integer element : integerList) {
            if (!uniqueElementsList.contains(element)) {
                uniqueElementsList.add(element);
            }
        }

        System.out.println("Список без дубликатов:");
        System.out.println(uniqueElementsList);
    }
}