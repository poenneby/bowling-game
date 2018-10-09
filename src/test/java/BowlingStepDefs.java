import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingStepDefs {

    private BowlingGame bowlingGame;

    @Given("^a new bowling game$")
    public void a_new_bowling_game() throws Throwable {
        bowlingGame = new BowlingGame();
    }

    @Then("^the score should be (\\d+)$")
    public void the_score_should_be(int arg1) throws Throwable {
        assertThat(bowlingGame.getScore()).isEqualTo(arg1);
    }

    @When("^I roll and (.*) fall$")
    public void i_roll_and_fall(List<Integer> rounds) throws Throwable {
        rounds.stream().forEach(numberOfPinsHit -> bowlingGame.roll(numberOfPinsHit));
    }

}
