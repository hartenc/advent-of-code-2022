package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main10 {
    private static int cycle = 0;
    private static int register = 1;
    private static long signal = 0L;

    public static void main(String[] args) {
//        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static long puzzel1() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input10.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String[] delen = regel.split(" ");
                switch(delen[0]) {
                    case "noop" -> noop();
                    case "addx" -> addx(Integer.parseInt(delen[1]));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return signal;
    }

    private static long puzzel2() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input10.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String[] delen = regel.split(" ");
                switch(delen[0]) {
                    case "noop" -> noop();
                    case "addx" -> addx(Integer.parseInt(delen[1]));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return signal;
    }

    private static void noop() {
        cycle();
    }

    private static void addx(Integer waarde) {
        cycle();
        cycle();
        register += waarde;
    }

//    private static void cycle() {
//        cycle++;
//        if((cycle - 20) % 40 == 0) {
//            signal += (register * cycle);
//        }
//    }

    private static void cycle() {
        int spriteDistance = Math.abs(register - (cycle%40));
        if (spriteDistance < 2) System.out.print("#"); else System.out.print(".");
        cycle++;
        if(cycle % 40 == 0) {
            System.out.println("");
        }
    }


}
