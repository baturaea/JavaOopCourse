package ru.baturaea2022.arraylisthome;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Прочитать в список все строки из файла
        String inputFile = "ArrayListHome/src/ru/baturaea2022/arraylisthome/input.txt";

        try (BufferedReader in = new BufferedReader(new FileReader(inputFile))) {
            ArrayList<String> fileStringsList = new ArrayList<>();
            String line;
            while ((line = in.readLine()) != null) {
                fileStringsList.add(line);
            }

            System.out.println("Прочитанный из файла список:");
            System.out.println(fileStringsList);
        } catch (FileNotFoundException e) {
            System.out.println("File " + inputFile + " not found!");
        } catch (IOException e) {
            System.out.println("File processing error");
        }

        // Есть список из целых чисел. Удалить из него все четные числа.
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(2, 1, 7, 7, 3, 4, 3, 5, 6, 6, 7, 8, 7));
        System.out.println("Исходный список чисел:");
        System.out.println(numbersList);

        for (int i = 0; i < numbersList.size(); i++) {
            if (numbersList.get(i) % 2 == 0) {
                numbersList.remove(i);
                i--;
            }
        }

        System.out.println("Список без четных чисел:");
        System.out.println(numbersList);

        // Надо создать новый список, в котором будут элементы первого списка в таком же порядке, но без повторений
        ArrayList<Integer> uniqueNumbersList = new ArrayList<>(numbersList.size());

        for (Integer element : numbersList) {
            if (!uniqueNumbersList.contains(element)) {
                uniqueNumbersList.add(element);
            }
        }

        System.out.println("Список без дубликатов:");
        System.out.println(uniqueNumbersList);
    }
}