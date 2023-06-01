package com.epam.rd.autocode.floodfill.solution;

public class Flooder {

    private boolean[][] isVisitedMap;

    public void fill(String[][] map) {

        this.isVisitedMap = new boolean[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals("░") && !isVisited(i, j)) {

                    if (i - 1 >= 0) {

                        if (!map[i - 1][j].equals("░")) {
                            map[i - 1][j] = "░";
                            isVisitedMap[i - 1][j] = true;
                        }

                    }
                    if (i + 1 != map.length) {

                        if (!map[i + 1][j].equals("░")) {
                            map[i + 1][j] = "░";
                            isVisitedMap[i + 1][j] = true;
                        }

                    }
                    if (j + 1 != map[i].length) {

                        if (!map[i][j + 1].equals("░")) {
                            map[i][j + 1] = "░";
                            isVisitedMap[i][j + 1] = true;
                        }

                    }
                    if (j - 1 >= 0) {

                        if (!map[i][j - 1].equals("░")) {
                            map[i][j - 1] = "░";
                            isVisitedMap[i][j - 1] = true;
                        }

                    }


                }
            }
        }

    }

    public boolean isMapFlooded(String state) {
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == '█') {
                return false;
            }
        }
        return true;
    }

    private boolean isVisited(int row, int col) {
        return isVisitedMap[row][col];
    }

}

