package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Maze {
/*
stream maze


 */

    private final List<String> mazeRows;
    private final int lengthOfMapRow;
    final static Set<String> VALID_CHARS = new HashSet(Arrays.asList(new String[]{"X", "F", "S", " "}));

    public Maze(List<String> mazeRows, int rowLength) {
        this.mazeRows = mazeRows;
        this.lengthOfMapRow = rowLength;
    }

    public static Maze giveMeAMaze(List<String> rows) {
        return getMaze(rows);
    }

    public static Maze giveMeAMaze(Path mazeFile) throws IOException {
        List<String> listOfRows = Files.lines(mazeFile).collect(Collectors.toList());
        return getMaze(listOfRows);
    }

    private static Maze getMaze(List<String> mazeRows) {
        List<String> flattenedMaze = new ArrayList();
        int rowLength = 0;

        for (String mazeRow : mazeRows) {
            String[] split = mazeRow.split("(?!^)");
            rowLength = split.length;
            for (String s : split) {
                if (!VALID_CHARS.contains(s)) {
                    throw new InputMismatchException("maze contained :" + s + " thats invalid");
                }
                flattenedMaze.add(s);
            }
        }

        return new Maze(flattenedMaze, rowLength);
    }

    public int howManyWalls() {
        return howManyOf("X");
    }

    public int howManySpaces() {
        return howManyOf(" ");
    }

    public String whatsAt(int xCoord, int yCoord) {
        return mazeRows.get(((lengthOfMapRow)*yCoord)+xCoord);
    }



    private int howManyOf(String s) {
        return new Integer(String.valueOf(mazeRows.stream().filter((t) -> s.equals(t)).count()));
    }

    public Coords startCoords() {
        int indexOfStart = mazeRows.indexOf("S");
        int x = indexOfStart % lengthOfMapRow;
        int y = indexOfStart / lengthOfMapRow;
        return new Coords(x,y);
    }
}
