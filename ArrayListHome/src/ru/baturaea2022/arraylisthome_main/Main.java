package ru.baturaea2022.arraylisthome_main;

import ru.baturaea2022.arraylisthome.ArrayListHome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayListHome<Integer> list1 = new ArrayListHome<>();

        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/ru/baturaea2022/arraylisthome_main/input.txt"))) {
            while (scanner.hasNextLine()) {
                list1.add(scanner.nextInt());
            }
        }

        System.out.println(list1);

        for (int i = 0; i < list1.size(); i++) {
            if (((Integer) list1.get(i)) % 2 == 0) {
                list1.remove(i);
                i--;
            }
        }

        System.out.println(list1);
        ArrayListHome<Integer> list2 = new ArrayListHome<>();

        for (int i = 0; i < list1.size(); i++) {
            Object element = list1.get(i);
            boolean isRepeat = false;

            for (int j = 0; j < i; j++) {
                if (element.equals(list1.get(j))) {
                    isRepeat = true;
                    break;
                }
            }

            if (!isRepeat) {
                list2.add((Integer) element);
            }
        }

        System.out.println(list2);
    }
}

