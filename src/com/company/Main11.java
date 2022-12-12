package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class Main11 {

    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static long puzzel1() {
        List<Monkey> monkeys = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input11.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                if(regel.startsWith("Monkey")) {
                    Monkey monkey = new Monkey();
                    String[] items = bufferedReader.readLine().split("items: ")[1].split(", ");
                    Stream.of(items).forEach(item -> monkey.items.add(Long.parseLong(item)));

                    String operation = bufferedReader.readLine().split("= old ")[1];
                    switch(operation.charAt(0)) {
                        case '+' -> monkey.operationAction = 0;
                        case '*' -> monkey.operationAction = 1;
                    }
                    if (operation.startsWith("old", 2)) {
                        monkey.operationValue = -1;
                    } else {
                        monkey.operationValue = Integer.parseInt(operation.substring(2));
                    }

                    String divisible = bufferedReader.readLine().split("divisible by ")[1];
                    monkey.divisble = Integer.parseInt(divisible);

                    String conditionTrue = bufferedReader.readLine().split("monkey ")[1];
                    monkey.targetTrue = Integer.parseInt(conditionTrue);

                    String conditionFalse = bufferedReader.readLine().split("monkey ")[1];
                    monkey.targetFalse = Integer.parseInt(conditionFalse);

                    monkeys.add(monkey);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i=0; i<20; i++) {
            monkeys.forEach(monkey -> {
                while(monkey.items.size() > 0) {
                    monkey.inspects++;
                    Long item = monkey.items.get(0);
                    Long originalVal = monkey.items.get(0);
                    if(monkey.operationAction == 0) {
                        if (monkey.operationValue == -1) {
                            item = item + item;
                        } else {
                            item = item + monkey.operationValue;
                        }
                    } else {
                        if (monkey.operationValue == -1) {
                            item = item * item;
                        } else {
                            item = item * monkey.operationValue;
                        }
                    }
                    item = item / 3;

                    monkey.items.remove(originalVal);
                    if (item % monkey.divisble == 0) {
                        monkeys.get(monkey.targetTrue).items.add(item);
                    } else {
                        monkeys.get(monkey.targetFalse).items.add(item);
                    }
                }
            });
        }
        monkeys.sort(Comparator.comparingLong(a -> -a.inspects));

        return monkeys.get(0).inspects * monkeys.get(1).inspects;
    }

    private static long puzzel2() {
        List<Monkey> monkeys = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input11.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                if(regel.startsWith("Monkey")) {
                    Monkey monkey = new Monkey();
                    String[] items = bufferedReader.readLine().split("items: ")[1].split(", ");
                    Stream.of(items).forEach(item -> monkey.items.add(Long.parseLong(item)));

                    String operation = bufferedReader.readLine().split("= old ")[1];
                    switch(operation.charAt(0)) {
                        case '+' -> monkey.operationAction = 0;
                        case '*' -> monkey.operationAction = 1;
                    }
                    if (operation.startsWith("old", 2)) {
                        monkey.operationValue = -1;
                    } else {
                        monkey.operationValue = Integer.parseInt(operation.substring(2));
                    }

                    String divisible = bufferedReader.readLine().split("divisible by ")[1];
                    monkey.divisble = Integer.parseInt(divisible);

                    String conditionTrue = bufferedReader.readLine().split("monkey ")[1];
                    monkey.targetTrue = Integer.parseInt(conditionTrue);

                    String conditionFalse = bufferedReader.readLine().split("monkey ")[1];
                    monkey.targetFalse = Integer.parseInt(conditionFalse);

                    monkeys.add(monkey);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer mod = monkeys.stream().map(monkey -> monkey.divisble).reduce(1, (a,b) -> a*b);

        for(int i=0; i<10000; i++) {
            monkeys.forEach(monkey -> {
                while(monkey.items.size() > 0) {
                    monkey.inspects++;
                    Long item = monkey.items.get(0);
                    item = item % mod;
                    Long originalVal = monkey.items.get(0);
                    if(monkey.operationAction == 0) {
                        if (monkey.operationValue == -1) {
                            item = item + item;
                        } else {
                            item = item + monkey.operationValue;
                        }
                    } else {
                        if (monkey.operationValue == -1) {
                            item = item * item;
                        } else {
                            item = item * monkey.operationValue;
                        }
                    }

                    monkey.items.remove(originalVal);
                    if (item % monkey.divisble == 0) {
                        monkeys.get(monkey.targetTrue).items.add(item);
                    } else {
                        monkeys.get(monkey.targetFalse).items.add(item);
                    }
                }
            });
        }
        monkeys.sort(Comparator.comparingLong(a -> -a.inspects));

        return monkeys.get(0).inspects * monkeys.get(1).inspects;
    }

    private static class Monkey {
        public List<Long> items;
        public int operationValue;
        public int operationAction; //0 = +, 1 = *
        public int divisble;
        public int targetTrue;
        public int targetFalse;
        public long inspects;

        public Monkey() {
            items = new ArrayList<>();
        }
    }


}
