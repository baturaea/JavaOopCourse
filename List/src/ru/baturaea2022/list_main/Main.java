package ru.baturaea2022.list_main;

import ru.baturaea2022.list.List;

public class Main {
    public static void main(String[] args) {
        List<String> stringsList = new List<>("qwe");


        stringsList.insertFirst("rew");
        stringsList.insertFirst(null);
        stringsList.insertFirst("rew4");

        System.out.println(stringsList);
        System.out.println(stringsList.getSize());

        System.out.println("================insert(4, 111)");
        stringsList.insert(4, "111");
        System.out.println(stringsList);
        System.out.println(stringsList.getSize());

        System.out.println("================insert(1, 543)");
        stringsList.insert(1, "543");
        System.out.println(stringsList);
        System.out.println(stringsList.getSize());

        System.out.println("================copyList()");
        List<String> copyStringsList = stringsList.copy();
        System.out.println(copyStringsList);
        System.out.println(copyStringsList.getSize());

        System.out.println("================reversalList");
        stringsList.reverse();
        System.out.println(stringsList);
        System.out.println(stringsList.getSize());

        System.out.println("================deleteByValue(rew4)");
        System.out.println(stringsList.deleteByData("rew4"));
        System.out.println(stringsList);
        System.out.println(stringsList.getSize());

        System.out.println("================deleteByIndex(1)");
        System.out.println(stringsList.deleteByIndex(1));
        System.out.println(stringsList);
        System.out.println(stringsList.getSize());

        System.out.println("================deleteFromHead");
        stringsList.deleteFirst();
        System.out.println(stringsList);
        System.out.println(stringsList.getSize());

        System.out.println("================stringsList.setValue(1, stringsList.getValue(0));");
        stringsList.setData(1, stringsList.getData(0));
        System.out.println(stringsList);
        System.out.println(stringsList.getSize());
    }
}