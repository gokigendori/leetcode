package com.gokigendori.leetcode.medium;

public class Medium {
    public int numTeams(int[] rating) {
        int result = 0;
        for (int i = 1; i < rating.length - 1; i++) {
            int[] less = new int[2];
            int[] more = new int[2];
            for (int j = 0; j < rating.length; j++) {
                if (i == j) {
                    continue;
                }
                if(rating[j]<rating[i]){
                    if (i < j) {
                        less[0]++;
                    }else{
                        less[1]++;
                    }
                }else{
                    if (i < j) {
                        more[0]++;
                    }else {
                        more[1]++;
                    }
                }
            }
            result += less[1] * more[0] // 増加;
             + more[1] * less[0]; // 減少
        }
        return result;
    }

}
