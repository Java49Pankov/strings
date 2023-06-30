package telran.text;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;

public class Strings {
	static HashMap<String, BinaryOperator<Double>> mapOperations;
	static {
		mapOperations = new HashMap<>();
		mapOperations.put("-", (a, b) -> a - b);
		mapOperations.put("+", (a, b) -> a + b);
		mapOperations.put("*", (a, b) -> a * b);
		mapOperations.put("/", (a, b) -> a / b);
		mapOperations.put("%", (a, b) -> a % b);
	}

	public static String javaVariableName() {
		return "([a-zA-Z$][\\w$]*|_[\\w$]+)";
	}

	public static String arithmeticExpression() {
		String operandRE = operand();
		String operatorRE = operator();
		return String.format("%1$s(%2$s%1$s)*", operandRE, operatorRE);
	}

	public static String operator() {
		return "\\s*([-+*/])\\s*";
	}

	public static String operand() {
		return "(\\d+\\.?\\d*|(\\p{Alpha}))";

	}

	public static boolean isArithmeticExpression(String expression) {
		expression = expression.trim();
		return expression.matches(arithmeticExpression());
	}

	public static double computeExpression(String expression, HashMap<String, Double> mapVariables) {
		if (!isArithmeticExpression(expression)) {
			throw new IllegalArgumentException("Wrong arithmetic expression");
		}
		expression = expression.replaceAll("\\s+", "");
		String[] operands = expression.split(operator());
		String[] operators = expression.split(operand());
		double res = parseOperand(operands[0], mapVariables);
		for (int i = 1; i < operands.length; i++) {
			double operand = parseOperand(operands[i], mapVariables);
			res = mapOperations.get(operators[i]).apply(res, operand);
		}
		return Math.round(res * 100) / 100.0;
	}

	private static double parseOperand(String operand, HashMap<String, Double> mapVariables) {
		double res;
		if (operand.matches(javaVariableName())) {
			res = getValue(operand, mapVariables);
		} else {
			res = Double.parseDouble(operand);
		}
		return res;
	}

	private static double getValue(String operand, HashMap<String, Double> mapVariables) {
		double res = 0;
		if (mapVariables.containsKey(operand)) {
			res = mapVariables.get(operand);
		} else {
			throw new NoSuchElementException();
		}
		return res;
	}

}