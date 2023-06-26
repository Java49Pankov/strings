package telran.text;

public class Strings {
	public static String javaVariableName() {
		return "[a-zA-Z$][\\w$]*|_[\\w$]+";
	}

	public static String zero_300() {
		return "[1-9]\\d?|[1-2]\\d\\d|300|0";
	}

	public static String ipV4Octet() {
		return "[0-9]\\d?|[0-1]\\d\\d|2[0-4]\\d|25[0-5]";
	}

	public static String ipV4() {
		String octetRegex = "(" + ipV4Octet() + ")";
		return octetRegex +"\\." + octetRegex + "\\." + octetRegex + "\\." + octetRegex;
	}
}
