package ru.baturaea2022.arraylist_main;

import ru.baturaea2022.arraylist.ArrayList;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(1);
        list1.add(10);
        list1.add(5);

        System.out.println("Исходный список:");
        System.out.println(list1);

        list1.add(5, 8);
        System.out.println("list1.add(5, 8)");
        System.out.println(list1);

        Integer o = 8;
        System.out.println("list1.contains(o)");
        System.out.println(list1.contains(o));

        System.out.println("list1.get(2)");
        System.out.println(list1.get(2));

        System.out.println("list1.set(2, 9)");
        System.out.println(list1.set(2, 9));

        List<Integer> collection = Arrays.asList(2, 4, 5, 6);

        System.out.println(list1.addAll(collection));
        System.out.println("list1.addAll(2, collection): 2,4,5,6");
        System.out.println(list1);

        System.out.println("list1.containsAll(collection): 2,4,5,6");
        System.out.println(list1.containsAll(collection));

        System.out.println("list1.removeAll(collection): 2,4,5,6");
        System.out.println(list1.removeAll(collection));
        System.out.println(list1);

        System.out.println("list1.retainAll(collection): 2,4,5,6");
        System.out.println(list1.retainAll(collection));
        System.out.println(list1);
    }
}