package app.boot.controller;

import app.boot.controller.transferobjects.CalculationResult;
import app.core.CalculationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class CalculationEngineController {

	private CalculationEngine calculationEngine;

	@Autowired
	public CalculationEngineController(CalculationEngine calculationEngine) {
		this.calculationEngine = calculationEngine;
	}

	@CrossOrigin(origins = "http://localhost:4200", methods= {RequestMethod.GET})
	@GetMapping(path = "/expression/{expression}", produces= APPLICATION_JSON_VALUE)
	public ResponseEntity<CalculationResult> response(@PathVariable(name = "expression") String expr) {
		try {
			// would be nice to wrap calculation engine with cache it can be more use full
			return ResponseEntity.ok().body(new CalculationResult(expr, calculationEngine.calculate(expr)));
			//TODO: here must be exception handling
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
