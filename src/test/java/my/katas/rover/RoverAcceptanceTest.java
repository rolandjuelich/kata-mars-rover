package my.katas.rover;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, tags = "not @Ignore", plugin = { "pretty", "summary",
		"json:target/report/json/cucumber.json" })
public class RoverAcceptanceTest {

}
