package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main14 {
    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static long puzzel1() {
        List<Obstakel> obstakels = new ArrayList<>();
        int hoogsteY = 0;
        int laagsteY = Integer.MAX_VALUE;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input14.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String[] parts = regel.split(" -> ");
                for(int x=0; x<parts.length - 1;x++) {
                    String[] start = parts[x].split(",");
                    String[] eind = parts[x+1].split(",");
                    int startX = Integer.parseInt(start[0]);
                    int startY = Integer.parseInt(start[1]);
                    int eindX = Integer.parseInt(eind[0]);
                    int eindY = Integer.parseInt(eind[1]);

                    if(startX > eindX) {
                        for(int i=eindX; i < startX +1; i++) {
                            obstakels.add(new Obstakel(i,startY));
                        }
                    }
                    if(startX < eindX) {
                        for(int i=startX; i < eindX +1; i++) {
                            obstakels.add(new Obstakel(i,startY));
                        }
                    }
                    if(startY > eindY) {
                        for(int i=eindY; i < startY +1; i++) {
                            obstakels.add(new Obstakel(startX,i));
                        }

                    }
                    if(startY < eindY) {
                        for(int i=startY; i < eindY +1; i++) {
                            obstakels.add(new Obstakel(startX,i));
                        }
                    }
                    if(startY > hoogsteY) hoogsteY = startY;
                    if(startY < laagsteY) laagsteY = startY;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        int zandkorrels = 0;
        while(true) {
            Obstakel zand = new Obstakel(500, 0);
            while(true) {
                if (zand.y > hoogsteY) break;
                if (!obstakels.contains(new Obstakel(zand.x, zand.y + 1))) {
                    // beneden vrij
                    zand.y += 1;
                    continue;
                }
                if (!obstakels.contains(new Obstakel(zand.x - 1, zand.y + 1))) {
                    // linksonder vrij
                    zand.x -= 1;
                    zand.y += 1;
                    continue;
                }
                if (!obstakels.contains(new Obstakel(zand.x + 1, zand.y + 1))) {
                    // rechtsonder vrij
                    zand.x += 1;
                    zand.y += 1;
                    continue;
                }
                break;
            }
            if (zand.y > hoogsteY) break;
            obstakels.add(zand);
            zandkorrels++;
        }

        return zandkorrels;
    }

    private static long puzzel2() {
        List<Obstakel> obstakels = new ArrayList<>();
        int hoogsteY = 0;
        int laagsteY = Integer.MAX_VALUE;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input14.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String[] parts = regel.split(" -> ");
                for(int x=0; x<parts.length - 1;x++) {
                    String[] start = parts[x].split(",");
                    String[] eind = parts[x+1].split(",");
                    int startX = Integer.parseInt(start[0]);
                    int startY = Integer.parseInt(start[1]);
                    int eindX = Integer.parseInt(eind[0]);
                    int eindY = Integer.parseInt(eind[1]);

                    if(startX > eindX) {
                        for(int i=eindX; i < startX +1; i++) {
                            obstakels.add(new Obstakel(i,startY));
                        }
                    }
                    if(startX < eindX) {
                        for(int i=startX; i < eindX +1; i++) {
                            obstakels.add(new Obstakel(i,startY));
                        }
                    }
                    if(startY > eindY) {
                        for(int i=eindY; i < startY +1; i++) {
                            obstakels.add(new Obstakel(startX,i));
                        }

                    }
                    if(startY < eindY) {
                        for(int i=startY; i < eindY +1; i++) {
                            obstakels.add(new Obstakel(startX,i));
                        }
                    }
                    if(startY > hoogsteY) hoogsteY = startY;
                    if(startY < laagsteY) laagsteY = startY;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        int zandkorrels = 0;
        while(true) {
            if(obstakels.contains(new Obstakel(500, 0))) break;
            Obstakel zand = new Obstakel(500, 0);
            while(true) {
                if (zand.y == hoogsteY + 1) break;
                if (!obstakels.contains(new Obstakel(zand.x, zand.y + 1))) {
                    // beneden vrij
                    zand.y += 1;
                    continue;
                }
                if (!obstakels.contains(new Obstakel(zand.x - 1, zand.y + 1))) {
                    // linksonder vrij
                    zand.x -= 1;
                    zand.y += 1;
                    continue;
                }
                if (!obstakels.contains(new Obstakel(zand.x + 1, zand.y + 1))) {
                    // rechtsonder vrij
                    zand.x += 1;
                    zand.y += 1;
                    continue;
                }
                break;
            }
            obstakels.add(zand);
            zandkorrels++;
        }

        return zandkorrels;
    }

    private static class Obstakel {
        public int x;
        public int y;

        public Obstakel(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Obstakel ander)
                return x == ander.x && y == ander.y;
            return false;
        }
    }


}
