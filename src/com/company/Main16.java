package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main16 {
    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static long puzzel1() {
        Map<String, Valve> valves = new HashMap<>();
        List<Scenario> scenarios = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input16.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String valveNaam = regel.substring(6, 8);
                int flowRate = Integer.parseInt(regel.split("rate=")[1].split(";")[0]);
                String tunnels = regel.split("valves ")[1];
                valves.put(valveNaam, new Valve(tunnels, flowRate, valveNaam));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        Scenario start = new Scenario();
        start.current = valves.get("AA");
        scenarios.add(start);

        for (int x = 0; x < 30; x++) {
            scenarios.forEach(scenario -> {
                scenario.open.forEach(valve -> scenario.score += valves.get(valve).flow);
            });
            List<Scenario> newScenarios = new ArrayList<>();
            scenarios.forEach(scenario -> {
                if (!scenario.open.contains(scenario.current.naam) && scenario.current.flow > 0) {
                    scenario.open.add(scenario.current.naam);
                    newScenarios.add(scenario);
                } else {
                    scenario.current.tunnels.forEach(tunnel -> {
                        Scenario newScenario = new Scenario();
                        newScenario.current = valves.get(tunnel);
                        newScenario.open = new ArrayList<>(scenario.open);
                        newScenario.score = scenario.score;
                        newScenarios.add(newScenario);
                    });
                }
            });

            newScenarios.sort((a, b) -> b.score - a.score);
            if (newScenarios.size() > 10000) scenarios = newScenarios.subList(0, 10000);
            else scenarios = newScenarios;
            //scenarios = newScenarios;
        }

        return scenarios.get(0).score;
    }

    private static long puzzel2() {
        Map<String, Valve> valves = new HashMap<>();
        List<Scenario2> scenarios = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input16.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String valveNaam = regel.substring(6, 8);
                int flowRate = Integer.parseInt(regel.split("rate=")[1].split(";")[0]);
                String tunnels = regel.split("valves ")[1];
                valves.put(valveNaam, new Valve(tunnels, flowRate, valveNaam));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        Scenario2 start = new Scenario2();
        start.current = valves.get("AA");
        start.olifant = valves.get("AA");
        scenarios.add(start);

        for (int x = 0; x < 26; x++) {
            scenarios.forEach(scenario -> {
                scenario.open.forEach(valve -> scenario.score += valves.get(valve).flow);
            });
            List<Scenario2> newScenarios = new ArrayList<>();
            scenarios.forEach(scenario -> {
                if (!scenario.open.contains(scenario.current.naam) && scenario.current.flow > 0) {
                    scenario.open.add(scenario.current.naam);
                    newScenarios.add(scenario);
                } else {
                    scenario.current.tunnels.forEach(tunnel -> {
                        Scenario2 newScenario = new Scenario2();
                        newScenario.current = valves.get(tunnel);
                        newScenario.olifant = scenario.olifant;
                        newScenario.open = new ArrayList<>(scenario.open);
                        newScenario.score = scenario.score;
                        newScenarios.add(newScenario);
                    });
                }

            });
            List<Scenario2> newScenarios2 = new ArrayList<>();
            newScenarios.forEach(scenario -> {
                if (!scenario.open.contains(scenario.olifant.naam) && scenario.olifant.flow > 0) {
                    scenario.open.add(scenario.olifant.naam);
                    newScenarios2.add(scenario);
                } else {
                    scenario.olifant.tunnels.forEach(tunnel -> {
                        Scenario2 newScenario = new Scenario2();
                        newScenario.current = scenario.current;
                        newScenario.olifant = valves.get(tunnel);
                        newScenario.open = new ArrayList<>(scenario.open);
                        newScenario.score = scenario.score;
                        newScenarios2.add(newScenario);
                    });
                }

            });

            newScenarios2.sort((a, b) -> b.score - a.score);
            if (newScenarios2.size() > 100000) scenarios = newScenarios2.subList(0, 100000);
            else scenarios = newScenarios2;
            //scenarios = newScenarios;
        }

        return scenarios.get(0).score;
    }

    private static class Valve {
        public List<String> tunnels;
        public int flow;
        public String naam;

        public Valve(String tunnels, int flow, String naam) {
            this.tunnels = Stream.of(tunnels.split(", ")).collect(Collectors.toList());
            this.flow = flow;
            this.naam = naam;
        }

    }

    private static class Scenario {
        public int score;
        public Valve current;
        public List<String> open = new ArrayList<>();
    }

    private static class Scenario2 {
        public int score;
        public Valve current;
        public Valve olifant;
        public List<String> open = new ArrayList<>();
    }


}
