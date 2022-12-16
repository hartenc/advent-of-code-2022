package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class Main13 {
    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static long puzzel1() {
        long correctSum = 0;
        int counter = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input13.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String regel2 = bufferedReader.readLine();
                bufferedReader.readLine();
                Regel parsedRegel1 = new Regel(regel);
                Regel parsedRegel2 = new Regel(regel2);
                counter++;

                try {
                    vergelijk(parsedRegel1.objects, parsedRegel2.objects);

                } catch (WrongOrderException e) {

                    // niks doen
                } catch (CorrectOrderException e) {
                    System.out.println(counter);
                    correctSum += counter;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return correctSum;
    }

    private static long puzzel2() {
        List<Regel> regels = new ArrayList<>();
        Regel divider1 = new Regel("[2]");
        Regel divider2 = new Regel("[6]");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input13.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String regel2 = bufferedReader.readLine();
                bufferedReader.readLine();
                Regel parsedRegel1 = new Regel(regel);
                Regel parsedRegel2 = new Regel(regel2);
                regels.add(parsedRegel1);
                regels.add(parsedRegel2);

            }
            regels.add(divider1);
            regels.add(divider2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        regels.sort((regel1, regel2) -> {
            try {
                vergelijk(regel1.objects, regel2.objects);
            } catch (WrongOrderException e) {
                return 1;
            } catch (CorrectOrderException e) {
                return -1;
            }
            return 0;
        });

        return (regels.indexOf(divider1) + 1) * (regels.indexOf(divider2) + 1);
    }

    private static class Regel {
        List<Object> objects = new ArrayList<>();

        public Regel(String input) {
            parseString(input.substring(1), objects);
        }

    }

    private static void parseString(String regel, List<Object> objects) {
        String getal = "";
        for (int x = 0; x < regel.length(); x++) {
            if (regel.charAt(x) == '[') {
                int positie = vindSluitendeHaak(regel.substring(x));
                List<Object> newArray = new ArrayList<>();
                String partString = regel.substring(x, positie + 1 + x);
                String insideArray = regel.substring(x + 1, positie + x);
                parseString(insideArray, newArray);
                objects.add(newArray);
                regel = regel.replaceFirst(partString.replace("[", "\\[").replace("]", "\\]"), "");
                x--;
                continue;
            }
            if (regel.charAt(x) > 47 && regel.charAt(x) < 58) getal = getal + regel.charAt(x);
            if ((regel.charAt(x) == ']' || regel.charAt(x) == ',') && !getal.equals("")) {
                objects.add(Integer.parseInt(getal));
                getal = "";
            }
            if (regel.charAt(x) == ']') return;
        }
        if (getal.length() > 0) objects.add(Integer.parseInt(getal));
    }

    private static int vindSluitendeHaak(String regel) {
        int interneHaken = 0;
        for (int x = 1; x < regel.length(); x++) {
            if (regel.charAt(x) == ']') {
                if (interneHaken == 0) return x;
                else interneHaken--;
            }
            if (regel.charAt(x) == '[') interneHaken++;
        }
        throw new IllegalStateException("Ongeldige regel: " + regel);
    }

    private static void vergelijk(List<Object> a, List<Object> b) {
        if(a.size()==0 && b.size()==0) return;
        for (int x = 0; x < a.size(); x++) {
            if (x >= b.size()) {
                throw new WrongOrderException("Elementen in b op: " + a.size() + " VS " + b.size());
            }
            Object aObj = a.get(x);
            Object bObj = b.get(x);
            if (aObj instanceof List aList) {
                if (bObj instanceof List bList) {
                    vergelijk(aList, bList);
                } else {
                    List<Object> bList = new ArrayList<>();
                    bList.add((Integer) bObj);
                    vergelijk(aList, bList);
                }
            }
            if (aObj instanceof Integer aInteger) {
                if (bObj instanceof List bList) {
                    List<Object> aList = new ArrayList<>();
                    aList.add(aInteger);
                    vergelijk(aList, bList);
                } else {
                    Integer bInteger = (Integer) bObj;
                    if (aInteger > bInteger) {
                        throw new WrongOrderException("" + aInteger + " is hoger dan " + bInteger);
                    }
                    if (aInteger < bInteger) {
                        throw new CorrectOrderException();
                    };
                }
            }
        }
        throw new CorrectOrderException();
    }

    private static class WrongOrderException extends RuntimeException {
        public WrongOrderException(String message) {super(message);}
    }
    private static class CorrectOrderException extends RuntimeException {}
}
