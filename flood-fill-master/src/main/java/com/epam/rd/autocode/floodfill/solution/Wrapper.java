package com.epam.rd.autocode.floodfill.solution;


public class Wrapper {
    private String[][] floodMap;
    private String[] lines;

    public Wrapper(String map) {
        this.lines = map.split("\n");
        floodMap = calcMapSize();
        initialFill();

    }

    private String[][] calcMapSize() {

        return this.floodMap = new String[lines.length][lines[0].length()];
    }

    private void initialFill() {

        for (int i = 0; i < floodMap.length; i++) {
            floodMap[i] = lines[i].split("");
        }

    }

    public String state() {

        StringBuilder sb = new StringBuilder();

        for (String[] strings : floodMap) {
            for (String string : strings) {
                sb.append(string);
            }
            sb.append("\n");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    public String[][] getFloodMap() {
        return floodMap;
    }

    public void setFloodMap(String[][] floodMap) {
        this.floodMap = floodMap;
    }

}
