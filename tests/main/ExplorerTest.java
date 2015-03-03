package main;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ExplorerTest {

    Maze maze;

    public ExplorerTest() throws IOException {
        maze = Maze.giveMeAMaze(Paths.get("E:\\dev\\projects\\maze\\src\\resources\\Maze1.txt"));

    }


    @Test
    public void explorerStartsOnCorrectCoordsAndFacesNorth() {
        Explorer columbus = new Explorer(maze);

        assertThat(columbus.myLocation().toString(), equalTo("3,3"));
        assertThat(columbus.faces(), equalTo(Direction.NORTH));

    }

    @Test
    public void explorerCanMoveForward(){
        List<String> canMoveNorth = new ArrayList<>();
        canMoveNorth.add("XX X");
        canMoveNorth.add("XXSX");
        Explorer columbus = new Explorer(Maze.giveMeAMaze(canMoveNorth));
        assertThat(columbus.myLocation().toString(), equalTo("2,1"));
        columbus.moveForward();
        assertThat(columbus.myLocation().toString(), equalTo("2,0"));
    }

    @Test(expected = Exception.class)
    public void explorerCanWalkIntoAWall(){
        List<String> brokeMaze = new ArrayList<>();
        brokeMaze.add("XXXX");
        brokeMaze.add("XXSX");

        Explorer columbus = new Explorer(Maze.giveMeAMaze(brokeMaze));
        assertThat(columbus.myLocation().toString(), equalTo("2,1"));
        columbus.moveForward();
    }

    @Test
    public void canTurnLeft(){
        Explorer columbus = new Explorer(maze);
        assertThat(columbus.faces(), equalTo(Direction.NORTH));
        columbus.turnLeft();
        assertThat(columbus.faces(), equalTo(Direction.WEST));
        columbus.turnLeft();
        assertThat(columbus.faces(), equalTo(Direction.SOUTH));
        columbus.turnLeft();
        assertThat(columbus.faces(), equalTo(Direction.EAST));
        columbus.turnLeft();
        assertThat(columbus.faces(), equalTo(Direction.NORTH));
    }

    @Test
    public void canTurnRight(){
        Explorer columbus = new Explorer(maze);
        assertThat(columbus.faces(), equalTo(Direction.NORTH));
        columbus.turnRight();
        assertThat(columbus.faces(), equalTo(Direction.EAST));
        columbus.turnRight();
        assertThat(columbus.faces(), equalTo(Direction.SOUTH));
        columbus.turnRight();
        assertThat(columbus.faces(), equalTo(Direction.WEST));
        columbus.turnRight();
        assertThat(columbus.faces(), equalTo(Direction.NORTH));
    }

    @Test
    public void explorerMustBeAbleToDeclareWhatsInFrontOfHim(){
        Explorer columbus = new Explorer(maze);
        assertThat(columbus.whatDoISee(), equalTo("X"));
        columbus.turnRight();
        assertThat(columbus.whatDoISee(), equalTo(" "));

    }

    @Test
    public void explorerCanDeclareWhatNextMovesAre(){
        Explorer columbus = new Explorer(maze);
        assertThat(columbus.nextMoves(),equalTo("turn left, turn right"));
        columbus.turnRight();
        assertThat(columbus.nextMoves(), equalTo("turn left, turn right, move forward"));
    }

    @Test
    public void canReportLogOfMoves(){
        Explorer columbus = new Explorer(maze);
        columbus.turnRight();
        columbus.moveForward();

        assertThat(columbus.myMoves(),equalTo("turn right, move forward"));
    }
}
