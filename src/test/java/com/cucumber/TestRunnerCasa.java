package com.cucumber;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)  
@CucumberOptions(
		plugin = {"pretty", "html:reports/Reporte_de_prueba_casa.html"},
		features = {"classpath:features/casa.feature"},
		glue = {"com.cucumber.stepDefinitionCasa"},
		monochrome = true,
		dryRun = false
)
public class TestRunnerCasa {
	
}
 