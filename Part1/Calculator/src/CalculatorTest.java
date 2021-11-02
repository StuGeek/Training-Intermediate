import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {
    @Test
	public void test() {
        // 测试识别合法输入格式的函数是否正常
		assertEquals(true, Calculator.isNumeric("12"));
        assertEquals(true, Calculator.isNumeric("12.01"));
        assertEquals(true, Calculator.isNumeric("0.5"));
        assertEquals(true, Calculator.isNumeric("-12"));
        assertEquals(true, Calculator.isNumeric("-12.01"));
        assertEquals(true, Calculator.isNumeric("-0.5"));
        assertEquals(false, Calculator.isNumeric("abc"));
        assertEquals(false, Calculator.isNumeric(""));
	}
}
