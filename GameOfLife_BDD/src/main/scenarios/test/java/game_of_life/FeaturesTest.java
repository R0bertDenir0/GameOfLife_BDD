package game_of_life;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = {"src/test/java/game_of_life/multipleEvolutions"},
        glue = {"game_of_life.multipleEvolutions"},
        tags = "not @wip"
)
public class FeaturesTest {
}