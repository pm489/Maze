package main;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CoordsTest {

    @Test
    public void canAddCoordsTogetherForMovement() {
        Coords coord1 = new Coords(1, 0);
        Coords coord2 = new Coords(0, 1);

        assertThat(coord1.add(coord2).toString(), equalTo(new Coords(1, 1).toString()));
    }
}
