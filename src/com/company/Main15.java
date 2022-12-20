package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main15 {
    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static long puzzel1() {
        List<Sensor> sensors = new ArrayList<>();
        int hoogsteX = 0;
        int laagsteX = Integer.MAX_VALUE;
        int geenBeacon = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input15.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String[] parts = regel.split("x=");
                int xPos = Integer.parseInt(parts[1].split(", ")[0]);
                int yPos = Integer.parseInt(parts[1].split(", y=")[1].split(":")[0]);
                int bxPos = Integer.parseInt(parts[2].split(", ")[0]);
                int byPos = Integer.parseInt(parts[2].split(", y=")[1].split(":")[0]);
                Sensor sensor = new Sensor(xPos,yPos, bxPos, byPos);
                sensors.add(sensor);
                if(sensor.x - sensor.bereik < laagsteX) laagsteX = sensor.x - sensor.bereik;
                if(sensor.x + sensor.bereik > hoogsteX) hoogsteX = sensor.x + sensor.bereik;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = laagsteX; i < hoogsteX; i++) {
            for(int i2=0;i2<sensors.size();i2++) {
                if(sensors.get(i2).kanZien(i, 2000000)) { geenBeacon++; break;}
            }
        }

        return geenBeacon;
    }

    private static long puzzel2() {
        List<Sensor> sensors = new ArrayList<>();
        int hoogsteX = 0;
        int laagsteX = Integer.MAX_VALUE;
        boolean beaconMogelijk = true;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input15.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String[] parts = regel.split("x=");
                int xPos = Integer.parseInt(parts[1].split(", ")[0]);
                int yPos = Integer.parseInt(parts[1].split(", y=")[1].split(":")[0]);
                int bxPos = Integer.parseInt(parts[2].split(", ")[0]);
                int byPos = Integer.parseInt(parts[2].split(", y=")[1].split(":")[0]);
                Sensor sensor = new Sensor(xPos,yPos, bxPos, byPos);
                sensors.add(sensor);
                if(sensor.x - sensor.bereik < laagsteX) laagsteX = sensor.x - sensor.bereik;
                if(sensor.x + sensor.bereik > hoogsteX) hoogsteX = sensor.x + sensor.bereik;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 4_000_000; i++) {
            for(int j = 0; j < 4000000; j++) {
                beaconMogelijk = true;
                for (int i2 = 0; i2 < sensors.size(); i2++) {
                    if (sensors.get(i2).kanZien(i, j)) {
                        beaconMogelijk = false;
                        break;
                    }
                }
                if(beaconMogelijk) {
                    System.out.println("" + i + "-" + j);
                    return 0L;
                }
            }
            if(i % 100 == 0) System.out.println("Rijen gehad: " + i);
        }

        return 0L;
    }

    private static class Sensor {
        public int x;
        public int y;
        public int beaconX;
        public int beaconY;
        public int bereik;

        public Sensor(int x, int y, int beaconX, int beaconY) {
            this.x = x;
            this.y = y;
            this.beaconX = beaconX;
            this.beaconY = beaconY;
            bereik = Math.abs(x-beaconX) + Math.abs(y-beaconY);
        }

        public boolean kanZien(int x, int y) {
            return !(x==beaconX && y==beaconY) && Math.abs(this.x - x) + Math.abs(this.y - y) <= bereik;
        }

    }


}
