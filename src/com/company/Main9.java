package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Main9 {

    public static void main(String[] args) {
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static int puzzel1() {
        List<Positie> velden = new ArrayList<>();
        Positie kop = new Positie(0,0);
        Positie staart = new Positie(0, 0);
        velden.add(new Positie(0,0));
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input9.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String[] delen = regel.split(" ");
                int stappen = Integer.parseInt(delen[1]);
                for(int i=0; i<stappen; i++) {
                    switch(delen[0])  {
                        case "U" -> kop.y--;
                        case "D" -> kop.y++;
                        case "L" -> kop.x--;
                        case "R" -> kop.x++;
                    }
                    if(!kop.grenstAan(staart)) {
                        int xDiff = kop.x - staart.x;
                        int yDiff = kop.y - staart.y;
                        if(xDiff != 0 && yDiff != 0) {
                            // diagonaal
                            if(xDiff == 1) {
                                if(yDiff == 2) {
                                    staart.x++;
                                    staart.y++;
                                }
                                if(yDiff == -2) {
                                    staart.x++;
                                    staart.y--;
                                }
                            }
                            if(xDiff == 2) {
                                if(yDiff == 1) {
                                    staart.x++;
                                    staart.y++;
                                }
                                if(yDiff == -1) {
                                    staart.x++;
                                    staart.y--;
                                }
                            }
                            if(xDiff == -1) {
                                if(yDiff == 2) {
                                    staart.x--;
                                    staart.y++;
                                }
                                if(yDiff == -2) {
                                    staart.x--;
                                    staart.y--;
                                }
                            }
                            if(xDiff == -2) {
                                if(yDiff == 1) {
                                    staart.x--;
                                    staart.y++;
                                }
                                if(yDiff == -1) {
                                    staart.x--;
                                    staart.y--;
                                }
                            }


                        }  else {
                            if (xDiff > 0) staart.x++;
                            if (xDiff < 0) staart.x--;
                            if (yDiff > 0) staart.y++;
                            if (yDiff < 0) staart.y--;
                        }
                        if(!velden.contains(staart)) velden.add(new Positie(staart.x, staart.y));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return velden.size();
    }

    private static int puzzel2() {
        List<Positie> velden = new ArrayList<>();
        List<Positie> touw = new ArrayList<>();
        for (int i=0;i<10;i++) {
            touw.add(new Positie(0, 0));
        }
        velden.add(new Positie(0,0));
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input9.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                String[] delen = regel.split(" ");
                int stappen = Integer.parseInt(delen[1]);
                for(int i=0; i<stappen; i++) {
                    switch(delen[0])  {
                        case "U" -> touw.get(0).y--;
                        case "D" -> touw.get(0).y++;
                        case "L" -> touw.get(0).x--;
                        case "R" -> touw.get(0).x++;
                    }
                    for (int deel=1; deel < 10; deel++)
                    {
                        Positie kop = touw.get(deel - 1);
                        Positie staart = touw.get(deel);
                        if (!kop.grenstAan(staart)) {
                            int xDiff = kop.x - staart.x;
                            int yDiff = kop.y - staart.y;
                            if (xDiff != 0 && yDiff != 0) {
                                // diagonaal
                                if (xDiff == 1) {
                                    if (yDiff == 2) {
                                        staart.x++;
                                        staart.y++;
                                    }
                                    if (yDiff == -2) {
                                        staart.x++;
                                        staart.y--;
                                    }
                                }
                                if (xDiff == 2) {
                                    if (yDiff > 0) {
                                        staart.x++;
                                        staart.y++;
                                    }
                                    if (yDiff < 0) {
                                        staart.x++;
                                        staart.y--;
                                    }

                                }
                                if (xDiff == -1) {
                                    if (yDiff == 2) {
                                        staart.x--;
                                        staart.y++;
                                    }
                                    if (yDiff == -2) {
                                        staart.x--;
                                        staart.y--;
                                    }
                                }
                                if (xDiff == -2) {
                                    if (yDiff > 0) {
                                        staart.x--;
                                        staart.y++;
                                    }
                                    if (yDiff < 0) {
                                        staart.x--;
                                        staart.y--;
                                    }
                                }


                            } else {
                                if (xDiff > 0) staart.x++;
                                if (xDiff < 0) staart.x--;
                                if (yDiff > 0) staart.y++;
                                if (yDiff < 0) staart.y--;
                            }

                        }

                    }
                    if (!velden.contains(touw.get(9))) velden.add(new Positie(touw.get(9).x, touw.get(9).y));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return velden.size();
    }

    private static class Positie {
        public int x;
        public int y;

        public Positie(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean grenstAan(Positie ander) {
            return Math.abs(x - ander.x) <= 1 && Math.abs(y - ander.y) <= 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Positie positie = (Positie) o;
            return x == positie.x && y == positie.y;
        }
    }


}
