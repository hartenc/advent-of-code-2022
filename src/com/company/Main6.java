package com.company;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main6 {

    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static int puzzel1() {
        List<Character> tekens = new ArrayList<>();
        int positie = 0;
        try (FileReader reader = new FileReader("input6.txt")) {
            while (tekens.size() != 4) {
                Character teken = Character.valueOf((char) reader.read());
                if (tekens.contains(teken)) {
                    tekens.removeAll(tekens.subList(0, tekens.indexOf(teken) + 1));
                }
                tekens.add(teken);
                positie++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return positie;
    }

    private static int puzzel2() {
        List<Character> tekens = new ArrayList<>();
        int positie = 0;
        try (FileReader reader = new FileReader("input6.txt")) {
            while (tekens.size() != 14) {
                Character teken = Character.valueOf((char) reader.read());
                if (tekens.contains(teken)) {
                    tekens.removeAll(tekens.subList(0, tekens.indexOf(teken) + 1));
                }
                tekens.add(teken);
                positie++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return positie;
    }


}
