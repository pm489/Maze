package main;

public class Coords {

    public final int x;
    public final int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x+","+y;
    }

    public Coords add(Coords coord2) {
        return new Coords(this.x + coord2.x, this.y + coord2.y);
    }
}
