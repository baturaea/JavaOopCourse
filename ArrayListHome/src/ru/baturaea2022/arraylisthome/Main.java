package ru.baturaea2022.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> list1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/ru/baturaea2022/arraylisthome/input.txt"))) {
            while (scanner.hasNextLine()) {
                list1.add(scanner.nextInt());
            }
        }

        System.out.println(list1);

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) % 2 == 0) {
                list1.remove(i);
                i--;
            }
        }

        System.out.println(list1);
        ArrayList<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            Integer element = list1.get(i);
            boolean isRepeat = false;

            for (int j = 0; j < i; j++) {
                if (element.equals(list1.get(j))) {
                    isRepeat = true;
                    break;
                }
            }

            if (!isRepeat) {
                list2.add(element);
            }
        }

        System.out.println(list2);
    }
}

