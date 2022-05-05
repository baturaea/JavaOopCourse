package ru.baturaea2022.hashtable_main;

import ru.baturaea2022.hashtable.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table = new HashTable<>();

        table.add(1);
        table.add(11);
        table.add(12);
        table.add(15);
        table.add(2);
        table.add(26);
        table.add(4);
        table.add(3);

        for (Integer i : table) {
            System.out.println(i);
        }


    }
}