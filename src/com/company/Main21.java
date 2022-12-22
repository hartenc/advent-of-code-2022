package com.company;

import java.awt.desktop.SystemSleepEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Main21 {
    private static Map<String, Monkey> monkeys = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static long puzzel1() {


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input21.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String naam = regel.substring(0, 4);
                try {
                    long waarde = Long.parseLong(regel.substring(6));
                    monkeys.put(naam, new Monkey(waarde));
                } catch (NumberFormatException e) {
                    String target1 = regel.substring(6, 10);
                    char operation = regel.charAt(11);
                    String target2 = regel.substring(13);
                    monkeys.put(naam, new Monkey(target1, target2, operation));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        monkeys.forEach((naam, monkey) -> {
            if (monkey.value == 0) {
                monkey.value = evaluate(monkeys.get(monkey.target1), monkeys.get(monkey.target2), monkey.operation);
            }
        });

        return monkeys.get("root").value;
    }

    private static long evaluate(Monkey monkey1, Monkey monkey2, char operation) {
        if (monkey1.value == 0) {
            monkey1.value = evaluate(monkeys.get(monkey1.target1), monkeys.get(monkey1.target2), monkey1.operation);
        }
        if (monkey2.value == 0) {
            monkey2.value = evaluate(monkeys.get(monkey2.target1), monkeys.get(monkey2.target2), monkey2.operation);
        }
        switch (operation) {
            case '+':
                return monkey1.value + monkey2.value;
            case '-':
                return monkey1.value - monkey2.value;
            case '*':
                return monkey1.value * monkey2.value;
            case '/':
                return monkey1.value / monkey2.value;
        }
        throw new IllegalStateException("op " + operation);
    }

    private static long puzzel2() {


        Monkey rootMonkey = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input21.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String naam = regel.substring(0, 4);
                try {
                    long waarde = Long.parseLong(regel.substring(6));
                    if (naam.equals("humn")) waarde = 3305080592857L; //arbitrair hoog nummer
                    monkeys.put(naam, new Monkey(waarde));
                } catch (NumberFormatException e) {
                    String target1 = regel.substring(6, 10);
                    char operation = regel.charAt(11);
                    String target2 = regel.substring(13);
                    if (naam.equals("root")) rootMonkey = new Monkey(target1, target2, '='); else
                    monkeys.put(naam, new Monkey(target1, target2, operation));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {

            monkeys.forEach((naam, monkey) -> {
                if (monkey.value == 0L) {
                    monkey.value = evaluate(monkeys.get(monkey.target1), monkeys.get(monkey.target2), monkey.operation);
                }
            });
            if (monkeys.get(rootMonkey.target1).value == monkeys.get(rootMonkey.target2).value)
                return monkeys.get("humn").value;
            else {
                monkeys.get("humn").value += ((monkeys.get(rootMonkey.target1).value - monkeys.get(rootMonkey.target2).value) / 10);
            }
            monkeys.forEach((naam, monkey) -> {
                if (monkey.target1 != null) monkey.value = 0L;
            });
        }

    }


    private static class Monkey {
        public String target1;
        public String target2;
        public char operation;
        public long value;

        public Monkey(String target1, String target2, char operation) {
            this.target1 = target1;
            this.target2 = target2;
            this.operation = operation;
        }

        public Monkey(long value) {
            this.value = value;
        }

    }

}
