package telran.text.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.text.Strings;

class StringsTest {

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
	void computeExpressionDoubleTest() {
		HashMap<String, Double> map = new HashMap<String, Double>();
		map.put("a", 2.1);
		map.put("c", 1000.2);
		assertEquals(2, Strings.computeExpression(" 12/ 6", map));
		assertEquals(1008.67, Strings.computeExpression(" 12.1* a/ 3+c ", map));
		assertEquals(27.1, Strings.computeExpression(" 50/ 2+ a ", map));
		assertEquals(502.2, Strings.computeExpression(" c / 2+a ", map));
		assertEquals(1008, Strings.computeExpression(" 12*  2 / 3 + 1000 ", map));
		assertThrowsExactly(NoSuchElementException.class, () -> Strings.computeExpression(" 50/ 2+d+ 2 ", map));
		assertThrowsExactly(IllegalArgumentException.class, () -> Strings.computeExpression(" 12/ 18 + 100 10", map));
		assertThrowsExactly(IllegalArgumentException.class, () -> Strings.computeExpression(" 12/ 18 + 100 10", map));
	}
}
