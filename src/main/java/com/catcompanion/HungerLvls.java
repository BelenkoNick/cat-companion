package com.catcompanion;

public class HungerLvls {
    public static String[] Lvls = new String[]{"(-----)", "(+----)", "(++---)", "(+++--)", "(++++-)", "(+++++)"};

    public static int findIndex(String[] Lvls, String element) {
        for (int i = 0; i < Lvls.length; i++) {
            if (element.equals(Lvls[i]))
                return i;
        }
        return 999;
    }
}
