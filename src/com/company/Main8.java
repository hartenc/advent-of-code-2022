package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Main8 {

    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static int puzzel1() {
        List<List<Boom>> bos = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input8.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                List<Boom> lijn = new ArrayList<>();
                for(int i=0;i<regel.length();i++) lijn.add(new Boom(Byte.parseByte(regel.substring(i, i+1))));
                bos.add(lijn);
            }
            // Kijk vanuit west
            for (int i=0; i< 99; i++) {
                byte hoogste = -1;
                for (int i2=0; i2 < 99; i2++) {
                    Boom boom = bos.get(i).get(i2);
                    if (boom.hoogte > hoogste) {
                        boom.zichtbaar = true;
                        hoogste = boom.hoogte;
                    }
                }
            }
            // Kijk vanuit noord
            for (int i=0; i< 99; i++) {
                byte hoogste = -1;
                for (int i2=0; i2 < 99; i2++) {
                    Boom boom = bos.get(i2).get(i);
                    if (boom.hoogte > hoogste) {
                        boom.zichtbaar = true;
                        hoogste = boom.hoogte;
                    }
                }
            }
            // Kijk vanuit oost
            for (int i=0; i< 99; i++) {
                byte hoogste = -1;
                for (int i2=0; i2 < 99; i2++) {
                    Boom boom = bos.get(i).get(98 - i2);
                    if (boom.hoogte > hoogste) {
                        boom.zichtbaar = true;
                        hoogste = boom.hoogte;
                    }
                }
            }
            // Kijk vanuit zuid
            for (int i=0; i< 99; i++) {
                byte hoogste = -1;
                for (int i2=0; i2 < 99; i2++) {
                    Boom boom = bos.get(98 - i2).get(98 - i);
                    if (boom.hoogte > hoogste) {
                        boom.zichtbaar = true;
                        hoogste = boom.hoogte;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int zichtbaar = 0;
        for (int i=0; i<99;i++) {
            for (int i2=0; i2<99;i2++) {
                if(bos.get(i).get(i2).zichtbaar) zichtbaar++;
            }
        }

        return zichtbaar;
    }

    private static int puzzel2() {
        List<List<Boom>> bos = new ArrayList<>();
        int hoogsteScore = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input8.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                List<Boom> lijn = new ArrayList<>();
                for(int i=0;i<regel.length();i++) lijn.add(new Boom(Byte.parseByte(regel.substring(i, i+1))));
                bos.add(lijn);
            }

            for(int i=0;i<99;i++) {
                for(int i2=0;i2<99;i2++) {
                    Boom boom = bos.get(i).get(i2);
                    int scoreOost = 0;
                    int scoreZuid = 0;
                    int scoreWest = 0;
                    int scoreNoord = 0;

                    int teller = i2;
                    // kijk oost
                    while(teller < 98) {
                        scoreOost++;
                        teller++;
                        if(bos.get(i).get(teller).hoogte >= boom.hoogte) break;
                    }
                    teller = i;
                    // kijk zuid
                    while(teller < 98) {
                        scoreZuid++;
                        teller++;
                        if(bos.get(teller).get(i2).hoogte >= boom.hoogte) break;
                    }
                    teller = i2;
                    // kijk west
                    while(teller > 0) {
                        scoreWest++;
                        teller--;
                        if(bos.get(i).get(teller).hoogte >= boom.hoogte) break;
                    }
                    teller = i;
                    // kijk noord
                    while(teller > 0) {
                        scoreNoord++;
                        teller--;
                        if(bos.get(teller).get(i2).hoogte >= boom.hoogte) break;
                    }
                    int totaal = scoreOost * scoreNoord * scoreWest * scoreZuid;
                    if(totaal > hoogsteScore) {
                        hoogsteScore = totaal;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoogsteScore;
    }

    private static class Boom {
        public Byte hoogte = 0;
        public boolean zichtbaar = false;

        public Boom(Byte hoogte) {
            this.hoogte = hoogte;
        }
    }


}
