package main;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MazeTest {

    Maze validMaze;
    private final String path = "E:\\dev\\projects\\maze\\src\\resources\\Maze1.txt";


    @Test
    public void canGenerateMazeFromValidInput() throws IOException {
        validMaze = Maze.giveMeAMaze(Paths.get(path));
    }

    @Test(expected = InputMismatchException.class)
    public void blowsUpOnInvalidMaze() throws IOException {
        validMaze = Maze.giveMeAMaze(Paths.get("E:\\dev\\projects\\maze\\src\\resources\\MazeInvalid.txt"));
    }

    @Test
    public void howManyWalls() throws Exception {
        List<String> wall = new ArrayList();
        wall.add("XXXXXXXXXX");
        validMaze = Maze.giveMeAMaze(wall);
        assertThat(validMaze.howManyWalls(), equalTo(10));

        wall.add("XXXXX     ");
        Maze validMazeMoreWall = Maze.giveMeAMaze(wall);
        assertThat(validMazeMoreWall.howManyWalls(), equalTo(15));
    }

    @Test
    public void howManySpaces() {
        List<String> space = new ArrayList();
        space.add("          ");
        validMaze = Maze.giveMeAMaze(space);
        assertThat(validMaze.howManySpaces(), equalTo(10));

        space.add("XXXXX     ");
        Maze validMazeMoreWall = Maze.giveMeAMaze(space);
        assertThat(validMazeMoreWall.howManySpaces(), equalTo(15));
    }

    @Test
    public void whatsAtCoordsList() {
        List<String> maze = new ArrayList();
        maze.add("  S       ");
        maze.add("XXFXX     ");
        validMaze = Maze.giveMeAMaze(maze);

        assertThat(validMaze.whatsAt(0, 0), equalTo(" "));
        assertThat(validMaze.whatsAt(2, 1), equalTo("F"));
        assertThat(validMaze.whatsAt(2, 0), equalTo("S"));
    }

    @Test
    public void whatsAtCoords() throws IOException {
        validMaze = Maze.giveMeAMaze(Paths.get("E:\\dev\\projects\\maze\\src\\resources\\Maze1.txt"));

        assertThat(validMaze.whatsAt(0,0),equalTo("X"));
        assertThat(validMaze.whatsAt(1,1),equalTo(" "));
        assertThat(validMaze.whatsAt(3,3),equalTo("S"));
        assertThat(validMaze.whatsAt(3,10),equalTo(" "));
        assertThat(validMaze.whatsAt(14,14),equalTo("X"));
        assertThat(validMaze.whatsAt(1,14),equalTo("F"));
    }
}
