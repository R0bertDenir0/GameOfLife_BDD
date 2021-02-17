package game_of_life.multipleEvolutions;


import game_of_life.model.Grid;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class EvolutionSteps {
    private Grid grid;

    public EvolutionSteps() {}

    @Given("The actual population")
    public void TheActualPopulation(DataTable table) {
        this.grid = new Grid(table.asLists());
    }



    @When("Evolving to the next generation")
    public void evolvingToTheNextGeneration() {
        this.grid.evolve();
    }


    @Then("The new population should be")
    public void theNewPopulationShouldBe(DataTable table) {
        final var expectedGrid = new Grid(table.asLists());

        for (int i = 0; i < expectedGrid.getRows(); i++) {

            for (int j = 0; j < expectedGrid.getColumns(); j++) {
                Assertions.assertThat(expectedGrid.cells.get(i).get(j).isAlive == this.grid.cells.get(i).get(j).isAlive);
            }
        }

    }
}
