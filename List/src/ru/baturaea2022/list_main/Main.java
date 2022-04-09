package ru.baturaea2022.list_main;

import ru.baturaea2022.list.List;

public class Main {
    public static void main(String[] args) {
        List<String> stringsList = new List<>("qwe");

        stringsList.insertInHead("rew");
        stringsList.insertInHead("rew1");
        stringsList.insertInHead("rew4");

        System.out.println(stringsList);
        System.out.println(stringsList.getLength());

        System.out.println("================insert(1, 543)");
        stringsList.insert(1, "543");
        System.out.println(stringsList);
        System.out.println(stringsList.getLength());

        System.out.println("================copyList()");
        List<String> copyStringsList = stringsList.copyList();
        System.out.println(copyStringsList);
        System.out.println(copyStringsList.getLength());

        System.out.println("================reversalList");
        stringsList.reversalList();
        System.out.println(stringsList);
        System.out.println(stringsList.getLength());

        System.out.println("================deleteByValue(rew1)");
        stringsList.deleteByValue("rew1");
        System.out.println(stringsList);
        System.out.println(stringsList.getLength());

        System.out.println("================deleteByIndex(1)");
        stringsList.deleteByIndex(1);
        System.out.println(stringsList);

        System.out.println("================deleteFromHead");
        stringsList.deleteFromHead();
        System.out.println(stringsList);
        System.out.println(stringsList.getLength());

        System.out.println("================stringsList.setValue(1, stringsList.getValue(0));");
        stringsList.setValue(1, stringsList.getValue(0));
        System.out.println(stringsList);
        System.out.println(stringsList.getLength());
    }
}