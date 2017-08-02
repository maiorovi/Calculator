package app.boot.controller.transferobjects;

public class CalculationResult {
	private String initialExpression;
	private String evaluationResult;

	public CalculationResult(String initialExpression, String evaluationResult) {
		this.initialExpression = initialExpression;
		this.evaluationResult = evaluationResult;
	}

	public CalculationResult(String initialExpression, Double evaluationResult) {
		this.initialExpression = initialExpression;
		this.evaluationResult = evaluationResult.toString();
	}

	public String getInitialExpression() {
		return initialExpression;
	}

	public String getEvaluationResult() {
		return evaluationResult;
	}
}
