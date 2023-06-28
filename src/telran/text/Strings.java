package telran.text;

public class Strings {
	public static String javaVariableName() {
		return "[a-zA-Z$][\\w$]*|_[\\w$]+";
	}

	public static String zero_300() {
		return "[1-9]\\d?|[1-2]\\d\\d|300|0";
	}

	public static String ipV4Octet() {
		return "[01]?\\d\\d?|2([0-4]\\d|5[0-5])";
	}

	public static String ipV4() {
		String octetRegex = ipV4Octet();
		return String.format("((%s)[.]){3}(%s)", octetRegex, octetRegex);
	}

	/*
	 * @return regex for mobile Israel phone Israel code optional +972 (if the code
	 * specified, operator code should be with no 0) operator code: 050-059 ,
	 * 072-077 7 digits that may or may not be separated by dash
	 */
	public static String mobileIsraelPhone() {
		return "(\\+972\\s*-?|0)(5\\d|7[2-7])(-?\\d){7}";
	}

	/**
	 * 
	 * @return regex for arithmetic expressions without brackets operators - +, -,
	 *         *, / operands integer positive number with possible leading zero's
	 *         Positive examples: 1. " 20" 2. "20 +10 * 2/100 +4" 3. " 20 +10 *
	 *         2/100 +4 " Negative examples: 1. " 20+" 2. "10 20" 3. " +20"
	 */
	public static String arithmeticExpression() {
		return "\\s*\\d+(\\s*[+*/-]\\s*\\d+)*\\s*";
	}

	public static String arithmeticExpression_1() {
		String delimiter = "\\s*";
		String operand = "\\d+";
		String operator = "[+*/-]";
		return delimiter + operand + "(" + delimiter + operator + delimiter + operand + ")" + "*" + delimiter;
	}
}
