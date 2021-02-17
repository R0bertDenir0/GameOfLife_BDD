package game_of_life.model;

import game_of_life.model.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import java.util.ArrayList;

public class Grid {




    private Integer rows;
    private Integer columns;
    public final List<List<Cell>> cells;

    public Grid(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new ArrayList<>();

        for (int i = 0; i < columns; i++) {
            cells.add(new ArrayList<>());
        }
    }

    public Grid(Grid grid) {
        this.rows = grid.rows;
        this.columns = grid.columns;
        this.cells = grid.cells;
    }

    public Grid(List<List<String>> cells) {
        this.rows = cells.size();
        this.columns = cells.get(0).size();
        this.cells = convertDataTableToCells(cells);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.cells.get(i).get(j).neighbors = this.findCellNeighbors(i, j);
            }
        }
    }

    public List<Optional<Cell>> findCellNeighbors(int posX, int posY) {
        var cell = this.cells.get(posX).get(posY);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (i == posX - 1 && j == posY - 1) {

                    cell.neighbors.add(Optional.of(this.cells.get(i).get(j)));
                } else if (i == posX - 1 && j == posY) {

                    cell.neighbors.add(Optional.of(this.cells.get(i).get(j)));
                } else if (i == posX - 1 && j == posY + 1) {

                    cell.neighbors.add(Optional.of(this.cells.get(i).get(j)));
                } else if (i == posX && j == posY - 1) {

                    cell.neighbors.add(Optional.of(this.cells.get(i).get(j)));
                }  else if (i == posX && j == posY + 1) {

                    cell.neighbors.add(Optional.of(this.cells.get(i).get(j)));
                }  else if (i == posX + 1 && j == posY - 1) {

                    cell.neighbors.add(Optional.of(this.cells.get(i).get(j)));
                } else if (i == posX + 1 && j == posY) {

                    cell.neighbors.add(Optional.of(this.cells.get(i).get(j)));
                } else if (i == posX + 1 && j == posY + 1) {

                    cell.neighbors.add(Optional.of(this.cells.get(i).get(j)));
                }
            }
        }

        return cell.neighbors;
    }

    private boolean isOutOfBoundaries(int posX, int posY) {
        return posX - 1 < 0 || posX + 1 > this.rows || posY - 1 < 0 || posY + 1 > this.columns;
    }

    public void evolve() {
        for (var row : this.cells) {
            for (var cell : row) {

                if (cell.isAlive && cell.getAliveNeighbors() < 2) {

                    cell.isAlive = false;
                } else if (cell.isAlive && cell.getAliveNeighbors() > 3) {

                    cell.isAlive = false;
                } else if (cell.isAlive && (cell.getAliveNeighbors() == 2
                        || cell.getAliveNeighbors() == 3)) {

                    cell.isAlive = true;
                } else if (!cell.isAlive && cell.getAliveNeighbors() == 3) {

                    cell.isAlive = true;
                }
            }
        }

        for (int i = 0; i < this.rows; i++) {

            for (int j = 0; j < this.columns; j++) {

                this.cells.get(i).get(j).neighbors = this.findCellNeighbors(i, j);
            }
        }
    }

    private List<List<Cell>> convertDataTableToCells(List<List<String>> dataTable) {

        List<List<Cell>> cells = new ArrayList<>();

        for (int i = 0; i < dataTable.size(); i++) {
            cells.add(new ArrayList<>());

            for (int j = 0; j < dataTable.get(i).size(); j++) {

                Cell cell = dataTable.get(i).get(j).contains("*") ? new Cell(true): new Cell(false);
                cells.get(i).add(cell);
            }
        }

        return cells;
    }



    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }


    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Grid grid = (Grid) obj;

        return Objects.equals(rows, grid.rows) && Objects.equals(columns, grid.columns) && Objects.equals(cells, grid.cells);
    }



    @Override
    public int hashCode() {
        return Objects.hash(rows, columns, cells);
    }
}


