package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Main7 {

    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static Long puzzel1() {
        Map parent = new Map(null, "/");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input7.txt"))) {

            String regel;
            MapReference currentMapRef = new MapReference(null);
            boolean listing = false;
            while ((regel = bufferedReader.readLine()) != null) {
                Map currentMap = currentMapRef.get();
                if (listing) {
                    if (regel.charAt(0) == '$') {
                        listing = false;
                    } else {
                        String[] parts = regel.split(" ");
                        if (parts[0].equals("dir")) {
                            currentMap.mappen.add(new Map(currentMap, parts[1]));
                        } else {
                            currentMap.grootte += Long.parseLong(parts[0]);
                        }
                    }
                }
                if (!listing) {
                    if (regel.startsWith("cd", 2)) {
                        String doelmap = regel.substring(5);
                        switch (doelmap) {
                            case "/" -> currentMapRef = new MapReference(parent);
                            case ".." -> currentMapRef = new MapReference(currentMap.parent);
                            default -> currentMapRef = new MapReference(currentMap.mappen.stream().filter(map -> map.naam.equals(doelmap)).findFirst().get());

                        }
                    }
                    if (regel.startsWith("ls", 2)) listing = true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getAllMappenKleinerDan(parent, 100000L, new AtomicLong()).get();
    }

    private static Long puzzel2() {
        Map parent = new Map(null, "/");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input7.txt"))) {

            String regel;
            MapReference currentMapRef = new MapReference(null);
            boolean listing = false;
            while ((regel = bufferedReader.readLine()) != null) {
                Map currentMap = currentMapRef.get();
                if (listing) {
                    if (regel.charAt(0) == '$') {
                        listing = false;
                    } else {
                        String[] parts = regel.split(" ");
                        if (parts[0].equals("dir")) {
                            currentMap.mappen.add(new Map(currentMap, parts[1]));
                        } else {
                            currentMap.grootte += Long.parseLong(parts[0]);
                        }
                    }
                }
                if (!listing) {
                    if (regel.startsWith("cd", 2)) {
                        String doelmap = regel.substring(5);
                        switch (doelmap) {
                            case "/" -> currentMapRef = new MapReference(parent);
                            case ".." -> currentMapRef = new MapReference(currentMap.parent);
                            default -> currentMapRef = new MapReference(currentMap.mappen.stream().filter(map -> map.naam.equals(doelmap)).findFirst().get());

                        }
                    }
                    if (regel.startsWith("ls", 2)) {
                        listing = true;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map> groottes = getAllMappenGroterDan(parent, 30_000_000L - (70_000_000L - parent.getGrootte()), new ArrayList<>());
        groottes.sort(Comparator.comparingLong(Map::getGrootte));
        return groottes.get(0).getGrootte();
    }

    private static AtomicLong getAllMappenKleinerDan(Map map, Long maxGrootte, AtomicLong sum) {
        if (map.getGrootte() <= maxGrootte) {
            sum.addAndGet(map.getGrootte());
        }
        map.mappen.forEach(submap -> getAllMappenKleinerDan(submap, maxGrootte, sum).get());
        return sum;
    }

    private static List<Map> getAllMappenGroterDan(Map map, Long minGrootte, List<Map> groottes) {
        if (map.getGrootte() >= minGrootte) {
            groottes.add(map);
        }
        map.mappen.forEach(submap -> getAllMappenGroterDan(submap, minGrootte, groottes));
        return groottes;
    }

    private static class Map {
        public Map parent;
        public String naam;
        public List<Map> mappen;
        public long grootte;

        public Map(Map parent, String naam) {
            this.parent = parent;
            this.naam = naam;
            mappen = new ArrayList<>();
        }

        public Long getGrootte() {
            return grootte + mappen.stream().map(Map::getGrootte).reduce(0L, Long::sum);
        }
    }

    private static class MapReference {
        private Map map;

        public MapReference(Map map) {
            this.map = map;
        }

        public Map get() {
            return map;
        }
    }
}
