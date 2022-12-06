package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main5 {

    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static String puzzel1() {
        List<LinkedList<Character>> stapels = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input5.txt"))) {

            String regel;
            boolean stapelsLezen = true;
            int aantalStapels = 0;
            while ((regel = bufferedReader.readLine()) != null) {
                if(stapelsLezen) {
                    // inlezen van de stapels
                    if(regel.charAt(1) == '1') {
                        stapelsLezen = false;
                        bufferedReader.readLine();
                    } else {
                        if (stapels.size() == 0) {
                            aantalStapels = (regel.length() + 1) / 4;
                            for(int i=0;i<aantalStapels;i++) stapels.add(new LinkedList<>());
                        }
                        for (int i=0;i<aantalStapels;i++) {
                            Character box = regel.charAt((i*4) + 1);
                            if(!box.equals(' ')) stapels.get(i).addLast(box);
                        }
                    }
                } else {
                    // inlezen van de instructies
                    Integer aantal = Integer.parseInt(regel.split("move ")[1].split(" ")[0]);
                    Integer van = Integer.parseInt(regel.split("from ")[1].split(" ")[0]) - 1;
                    Integer naar = Integer.parseInt(regel.split("to ")[1]) - 1;
                    for(int i=0;i<aantal;i++) {
                        Character box = stapels.get(van).removeFirst();
                        stapels.get(naar).addFirst(box);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stapels.stream().map(stapel -> stapel.getFirst().toString()).reduce("", (str, item) -> str + item);
    }
    private static String puzzel2() {
        List<StringBuilder> stapels = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input5.txt"))) {

            String regel;
            boolean stapelsLezen = true;
            int aantalStapels = 0;
            while ((regel = bufferedReader.readLine()) != null) {
                if(stapelsLezen) {
                    // inlezen van de stapels
                    if(regel.charAt(1) == '1') {
                        stapelsLezen = false;
                        bufferedReader.readLine();
                    } else {
                        if (stapels.size() == 0) {
                            aantalStapels = (regel.length() + 1) / 4;
                            for(int i=0;i<aantalStapels;i++) stapels.add(new StringBuilder());
                        }
                        for (int i=0;i<aantalStapels;i++) {
                            Character box = regel.charAt((i*4) + 1);
                            if(!box.equals(' ')) stapels.get(i).append(box);
                        }
                    }
                } else {
                    // inlezen van de instructies
                    Integer aantal = Integer.parseInt(regel.split("move ")[1].split(" ")[0]);
                    Integer van = Integer.parseInt(regel.split("from ")[1].split(" ")[0]) - 1;
                    Integer naar = Integer.parseInt(regel.split("to ")[1]) - 1;
                    String part = stapels.get(van).substring(0, aantal);
                    stapels.get(van).delete(0, aantal);
                    stapels.get(naar).insert(0, part);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stapels.stream().map(stapel -> stapel.substring(0,1)).reduce("", (str, item) -> str + item);
    }

}
