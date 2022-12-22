package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main18 {
    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static long puzzel1() {
        List<Cube> cubes = new ArrayList<>();
        long exposedSides = 0L;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input18.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String parts[] = regel.split(",");
                cubes.add(new Cube(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2])));

            }

            for(int x=0;x<cubes.size();x++) {
                int zijdes = 6;
                for(int y=0;y<cubes.size();y++) {
                    if (x==y) continue;
                    int afstandX = cubes.get(x).x - cubes.get(y).x;
                    int afstandY = cubes.get(x).y - cubes.get(y).y;
                    int afstandZ = cubes.get(x).z - cubes.get(y).z;
                    if(Math.abs(afstandX) + Math.abs(afstandY) + Math.abs(afstandZ) > 1) continue;
                    zijdes--;
                }
                exposedSides += zijdes;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return exposedSides;
    }

    private static long puzzel2() {
        return 0L;
    }

    private static class Cube {
        public Cube(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int x;
        public int y;
        public int z;
    }


}
