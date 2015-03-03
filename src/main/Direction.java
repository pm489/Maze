package main;

public enum Direction {
    NORTH(new Coords(0,-1)) {
        @Override
        public Direction turnLeft() {
            return WEST;
        }

        @Override
        public Direction turnRight() {
            return EAST;
        }
    },
    SOUTH(new Coords(0,1)) {
        @Override
        public Direction turnLeft() {
            return EAST;
        }

        @Override
        public Direction turnRight() {
            return WEST;
        }
    },
    EAST(new Coords(1,0)) {
        @Override
        public Direction turnLeft() {
            return NORTH;
        }

        @Override
        public Direction turnRight() {
            return SOUTH;
        }
    },
    WEST(new Coords(-1,0)) {
        @Override
        public Direction turnLeft() {
            return SOUTH;
        }

        @Override
        public Direction turnRight() {
            return NORTH;
        }
    };



    public final Coords howToMoveThere;

    Direction(Coords howToMoveThere) {
        this.howToMoveThere = howToMoveThere;
    }

    public abstract Direction turnLeft();

    public abstract Direction turnRight();
}
