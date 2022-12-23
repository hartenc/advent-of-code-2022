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
        List<Cube> cubes = new ArrayList<>();
        long exposedSides = 0L;
        int maxX=0;
        int maxY=0;
        int maxZ=0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input18.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String parts[] = regel.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                int z = Integer.parseInt(parts[2]);
                cubes.add(new Cube(x, y, z));
                if(x > maxX) maxX = x;
                if(y > maxY) maxY = y;
                if(z > maxZ) maxZ = z;
            }
            int[][][] field = new int[maxX+1][maxY+1][maxZ+1];
            cubes.forEach(cube -> field[cube.x][cube.y][cube.z] = 2);
            field[0][0][0] = 1;
            for(int cycle=0;cycle<10000;cycle++) {
                for (int x = 0; x < maxX + 1; x++) {
                    for (int y = 0; y < maxY + 1; y++) {
                        for (int z = 0; z < maxZ + 1; z++) {
                            if(field[x][y][z] == 1) {
                                if(x > 0 && field[x-1][y][z] == 0) field[x-1][y][z] = 1;
                                if(x < (maxX) && field[x+1][y][z] == 0) field[x+1][y][z] = 1;
                                if(y > 0 && field[x][y-1][z] == 0) field[x][y-1][z] = 1;
                                if(y < (maxY) && field[x][y+1][z] == 0) field[x][y+1][z] = 1;
                                if(z > 0 && field[x][y][z-1] == 0) field[x][y][z-1] = 1;
                                if(z < (maxZ) && field[x][y][z+1] == 0) field[x][y][z+1] = 1;
                            }
                        }
                    }
                }
            }

            for (int x = 0; x < maxX; x++) {
                for (int y = 0; y < maxY; y++) {
                    for (int z = 0; z < maxZ; z++) {
                        if(field[x][y][z] == 0) {
                            cubes.add(new Cube(x,y,z));
                        }
                    }
                }
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
