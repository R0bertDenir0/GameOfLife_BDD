package game_of_life.model;

public class GameOfLife {

    private final Grid grid;

    public GameOfLife(final Grid grid) {
        this.grid = grid;
    }

    public void play() {
        this.grid.evolve();
    }
}
