package com.gokigendori.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] matrix) {
        Deque<Pair> queue = new ArrayDeque<>();
        int height = matrix.length;
        int width = matrix[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new Pair(j, i));
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[] nx = {-1, 1, 0, 0};
        int[] ny = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextx = p.x + nx[i];
                int nexty = p.y + ny[i];
                if (nextx < 0 || width <= nextx) {
                    continue;
                }
                if (nexty < 0 || height <= nexty) {
                    continue;
                }
                if (matrix[nexty][nextx] >= matrix[p.y][p.x] + 1) {
                    matrix[nexty][nextx] = matrix[p.y][p.x] + 1;
                    queue.add(new Pair(nextx, nexty));
                }
            }
        }
        return matrix;
    }

    public class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

