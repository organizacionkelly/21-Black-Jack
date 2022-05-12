package com.cucumber;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)  
@CucumberOptions(
		plugin = {"pretty", "html:reports/Reporte_de_prueba_jugador.html"},
		features = {"classpath:features/jugador.feature"},
		glue = {"com.cucumber.stepDefinitionJugador"},
		monochrome = true,
		dryRun = false
)
public class TestRunnerJugador {
	
}
 