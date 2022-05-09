package ru.baturaea2022.hashtable_main;

import ru.baturaea2022.hashtable.HashTable;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table = new HashTable<>();

        table.add(1);
        table.add(11);
        table.add(6);
        table.add(12);
        table.add(15);
        table.add(2);
        table.add(26);
        table.add(4);
        table.add(3);
        table.add(5);

        System.out.println(table);
        System.out.println("=================");

        List<Integer> collection = Arrays.asList(2, 4, 5, 6);

        System.out.println("table.addAll(collection): 2,4,5,6");
        System.out.println(table.addAll(collection));
        System.out.println(table);

        System.out.println("table.containsAll(collection):");
        System.out.println(table.containsAll(collection));

        System.out.println("table.removeAll(collection):");
        System.out.println(table.removeAll(collection));
        System.out.println(table);

        System.out.println("table.retainAll(collection):");
        System.out.println(table.retainAll(collection));
        System.out.println(table);

        System.out.println("table.size():");
        System.out.println(table.size());
    }
}