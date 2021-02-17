package game_of_life.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cell {

    public Boolean isAlive;
    public List<Optional<Cell>> neighbors;

    public Cell() {
        this.isAlive = false;
        this.neighbors = new ArrayList<>(8);
    }


    public Cell(Boolean isAlive) {
        this.isAlive = isAlive;
        this.neighbors = new ArrayList<>(8);
    }


    public Cell(Boolean isAlive, List<Optional<Cell>> neighbors) {
        this.isAlive = isAlive;
        this.neighbors = neighbors;
    }

    public Long getAliveNeighbors() {

        return this.neighbors.stream()
                .filter(Optional::isPresent)
                .filter(c -> c.get().isAlive)
                .count();
    }

    public Long getDeadNeighbors() {
        return this.neighbors.stream()
                .filter(Optional::isPresent)
                .filter(c -> !c.get().isAlive)
                .count();
    }
}
