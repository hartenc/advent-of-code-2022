package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main12 {

    public static void main(String[] args) {
//        System.out.println(Character.valueOf((char)123));
        System.out.println(puzzel1());
        System.out.println(puzzel2());
    }

    private static long puzzel1() {
        List<String> veld = new ArrayList<>();
        int[][] afstand;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input12.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                veld.add(regel);

            }

            afstand = new int[veld.get(0).length()][veld.size()];
            afstand[0][0] = 1;

            while(true) {

                for(int i=0;i<veld.get(0).length();i++) {
                    for(int i2=0;i2<veld.size();i2++) {
                        Character letter = veld.get(i2).charAt(i);
                        if(afstand[i][i2]==0) continue;
                        if(letter.equals('{') && afstand[i][i2] != 0) {
                            return afstand[i][i2];
                        }
                        if(i != 0) {
                            if ((afstand[i-1][i2] > afstand[i][i2] || afstand[i-1][i2] == 0) && veld.get(i2).charAt(i-1) - letter < 2) {
                                afstand[i-1][i2] = afstand[i][i2] + 1;
                            }
                        }
                        if(i < veld.get(0).length() - 1) {
                            if ((afstand[i+1][i2] > afstand[i][i2] || afstand[i+1][i2] == 0) && veld.get(i2).charAt(i+1) - letter < 2) {
                                afstand[i+1][i2] = afstand[i][i2] + 1;
                            }
                        }
                        if(i2 != 0) {
                            if ((afstand[i][i2-1] > afstand[i][i2] || afstand[i][i2-1] == 0) && veld.get(i2-1).charAt(i) - letter < 2) {
                                afstand[i][i2-1] = afstand[i][i2] + 1;
                            }
                        }
                        if(i2 < veld.size() - 1) {
                            if ((afstand[i][i2+1] > afstand[i][i2] || afstand[i][i2+1] == 0) && veld.get(i2+1).charAt(i) - letter < 2) {
                                afstand[i][i2+1] = afstand[i][i2] + 1;
                            }
                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return 0;
    }

    private static long puzzel2() {
        List<String> veld = new ArrayList<>();
        int[][] afstand;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input12.txt"))) {
            String regel;
            while ((regel = bufferedReader.readLine()) != null) {
                veld.add(regel);

            }

            afstand = new int[veld.get(0).length()][veld.size()];
            for(int x=0;x<veld.get(0).length(); x++) {
                for (int y = 0; y < veld.size(); y++) {
                    if(veld.get(y).charAt(x) == 'a') afstand[x][y] = 1;
                }
            }
            int it = 0;
            while(true) {
                it++;
                for(int i=0;i<veld.get(0).length();i++) {
                    for(int i2=0;i2<veld.size();i2++) {
                        Character letter = veld.get(i2).charAt(i);
                        if(afstand[i][i2]==0) continue;
                        if(letter.equals('{') && afstand[i][i2] != 0  && it > 100_000) {
                            return afstand[i][i2];
                        }
                        if(i != 0) {
                            if ((afstand[i-1][i2] > afstand[i][i2] || afstand[i-1][i2] == 0) && veld.get(i2).charAt(i-1) - letter < 2) {
                                afstand[i-1][i2] = afstand[i][i2] + 1;
                            }
                        }
                        if(i < veld.get(0).length() - 1) {
                            if ((afstand[i+1][i2] > afstand[i][i2] || afstand[i+1][i2] == 0) && veld.get(i2).charAt(i+1) - letter < 2) {
                                afstand[i+1][i2] = afstand[i][i2] + 1;
                            }
                        }
                        if(i2 != 0) {
                            if ((afstand[i][i2-1] > afstand[i][i2] || afstand[i][i2-1] == 0) && veld.get(i2-1).charAt(i) - letter < 2) {
                                afstand[i][i2-1] = afstand[i][i2] + 1;
                            }
                        }
                        if(i2 < veld.size() - 1) {
                            if ((afstand[i][i2+1] > afstand[i][i2] || afstand[i][i2+1] == 0) && veld.get(i2+1).charAt(i) - letter < 2) {
                                afstand[i][i2+1] = afstand[i][i2] + 1;
                            }
                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return 0;
    }


}
