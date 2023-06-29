package telran.text.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.text.Strings;

class StringsTest {

	@Test
	void test() {
//		String regex = "gray | grey | griy ";
		String regex = "gr(a|e|i)y";
		assertTrue("gray".matches(regex));
		assertTrue("grey".matches(regex));
		assertTrue("griy".matches(regex));
		assertFalse("groy".matches(regex));

		String regex_1 = "a?1234";
		assertTrue("1234".matches(regex_1));
		assertTrue("a1234".matches(regex_1));
		assertFalse("b1234".matches(regex_1));
		assertFalse("aa1234".matches(regex_1));

		String regex_2 = "a*1234";
		assertTrue("1234".matches(regex_2));
		assertTrue("a1234".matches(regex_2));
		assertFalse("b1234".matches(regex_2));
		assertTrue("aa1234".matches(regex_2));

		String regex_3 = "a+1234";
		assertFalse("1234".matches(regex_3));
		assertTrue("a1234".matches(regex_3));
		assertFalse("b1234".matches(regex_3));
		assertTrue("aa1234".matches(regex_3));

		String regex_4 = "(a|b)+1234";
		assertFalse("1234".matches(regex_4));
		assertTrue("a1234".matches(regex_4));
		assertTrue("b1234".matches(regex_4));
		assertTrue("aa1234".matches(regex_4));
	}

	@Test
	void javaVariableNameTest() {
		String regex = Strings.javaVariableName();
		assertTrue("a".matches(regex));
		assertTrue("$".matches(regex));
		assertTrue("$2".matches(regex));
		assertTrue("$_".matches(regex));
		assertTrue("__".matches(regex));
		assertTrue("_2".matches(regex));
		assertTrue("a_b".matches(regex));
		assertTrue("abc123456789".matches(regex));
		assertFalse("1a".matches(regex));
		assertFalse("_".matches(regex));
		assertFalse("a#".matches(regex));
		assertFalse("a b".matches(regex));
		assertFalse("a-b".matches(regex));
		assertFalse(" abc123456789".matches(regex));
	}

	@Test
	void zero_300Test() {
		String regex = Strings.zero_300();
		assertTrue("0".matches(regex));
		assertTrue("9".matches(regex));
		assertTrue("300".matches(regex));
		assertTrue("99".matches(regex));
		assertFalse("01".matches(regex));
		assertFalse("00".matches(regex));
		assertFalse("1111".matches(regex));
		assertFalse("301".matches(regex));
		assertFalse("3000".matches(regex));
		assertFalse("310".matches(regex));
		assertFalse("-30".matches(regex));
	}

	@Test
	void ipV4OctetTest() {
		String regex = Strings.ipV4Octet();
		assertTrue("000".matches(regex));
		assertTrue("00".matches(regex));
		assertTrue("0".matches(regex));
		assertTrue("99".matches(regex));
		assertTrue("1".matches(regex));
		assertTrue("10".matches(regex));
		assertTrue("199".matches(regex));
		assertTrue("200".matches(regex));
		assertTrue("249".matches(regex));
		assertTrue("250".matches(regex));
		assertTrue("255".matches(regex));
		assertFalse("0000".matches(regex));
		assertFalse(" 1".matches(regex));
		assertFalse(".0".matches(regex));
		assertFalse("-1".matches(regex));
		assertFalse("256".matches(regex));
		assertFalse("1000".matches(regex));
	}

	@Test
	void ipV4Test() {
		String regex = Strings.ipV4_v1();
		assertTrue("0.0.0.0".matches(regex));
		assertTrue("1.1.1.1".matches(regex));
		assertTrue("99.99.12.09".matches(regex));
		assertTrue("100.199.200.255".matches(regex));
		assertFalse(".1.2.3.4".matches(regex));
		assertFalse("1.2.3.4.".matches(regex));
		assertFalse(".1.&2.3.4".matches(regex));
		assertFalse("1.2.3".matches(regex));
		assertFalse("1.2.3.4.5".matches(regex));
		assertFalse("123 123 123 123".matches(regex));
	}

	@Test
	void mobileIsraelTest() {
		String regex = Strings.mobileIsraelPhone();
		assertTrue("+972-541234567".matches(regex));
		assertTrue("0541234567".matches(regex));
		assertTrue("074-1-2345-67".matches(regex));
		assertTrue("+972541234567".matches(regex));
		assertTrue("+972  541234567".matches(regex));
		assertFalse("+972-0541234567".matches(regex));
		assertFalse("+9720541234567".matches(regex));
		assertFalse("972-541234567".matches(regex));
		assertFalse("0641234567".matches(regex));
		assertFalse("+972-54123v567".matches(regex));
	}

	@Test
	void arithmeticExpressionTest() {
		assertTrue(Strings.isArithmeticExpression(" 12 "));
		assertTrue(Strings.isArithmeticExpression(" 12/ 6 "));
		assertTrue(Strings.isArithmeticExpression("12/2"));
		assertTrue(Strings.isArithmeticExpression(" 12* 2 / 3 + 1000 "));
		assertTrue(Strings.isArithmeticExpression(" 120  /  50 + 100 - 2 * 3 / 500"));
		assertFalse(Strings.isArithmeticExpression(" 12 18"));
		assertFalse(Strings.isArithmeticExpression(" 12/3&4 "));
		assertFalse(Strings.isArithmeticExpression(" 12+18- "));
		assertFalse(Strings.isArithmeticExpression(" 12/ 18 +100 10"));
	}

	@Test
	void computeExpressionTest() {
		assertEquals(12, Strings.computeExpression(" 12 "));
		assertEquals(2, Strings.computeExpression(" 12/ 6"));
		assertEquals(6, Strings.computeExpression("12/2"));
		assertEquals(1008, Strings.computeExpression(" 12*  2 / 3 + 1000 "));
		assertEquals(0, Strings.computeExpression(" 120 / 50 + 100 - 2 * 3 / 500 "));
		assertThrowsExactly(IllegalArgumentException.class,
				() -> Strings.computeExpression(" 12/ 18 + 100 10"));
	}

}
