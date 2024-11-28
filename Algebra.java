// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
		// Tests some of the operations
		System.out.println(plus(-5, -3)); // 2 + 3
		// System.out.println(minus(7, 2)); // 7 - 2
		System.out.println(minus(-5, -3)); // 2 - 7
		// System.out.println(times(-3, -4)); // 3 * 4
		// System.out.println(plus(2, times(4, 2))); // 2 + 4 * 2
		// System.out.println(pow(5, 3)); // 5^3
		// System.out.println(pow(3, 5)); // 3^5
		// System.out.println(div(-13, 2)); // 12 / 3
		// System.out.println(div(4, 5)); // 5 / 5
		// System.out.println(div(120, 6)); // 25 / 7
		// System.out.println(mod(25, 7)); // 25 % 7
		// System.out.println(mod(120, 6)); // 120 % 6
		// System.out.println(sqrt(36));
		// System.out.println(sqrt(263169));
		// System.out.println(sqrt(76123));
	}

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		int isneg = 0;
		if (x1 < 0 && x2 < 0) {
			x1 = reverseNeg(x1);
			x2 = reverseNeg(x2);
			isneg = 1;
		}
		for (int i = 0; i < x2; i++) {
			++x1;
		}
		if (isneg == 1) {
			return reversePos(x1);
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		int isneg = 0;
		if (x1 < 0 && x2 < 0) {
			x1 = reverseNeg(x1);
			x2 = reverseNeg(x2);
			isneg = 1;
		}
		for (int i = 0; i < x2; i++) {
			--x1;
		}
		if (isneg == 1) {
			return reversePos(x1);
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		if (x1 == 0 || x2 == 0) {
			return 0;
		}
		if (x1 < 0 && x2 < 0) {
			x1 = reverseNeg(x1);
			x2 = reverseNeg(x2);
		}
		int isNeg = isNeg(x1, x2);
		if (isNeg == 1) {
			if (x1 < 0) {
				x1 = reverseNeg(x1);
			} else {
				x2 = reverseNeg(x2);
			}
		}
		int localx1 = x1;
		for (int i = 1; i < x2; i++) {
			x1 = plus(x1, localx1);
		}
		if (isNeg == 1) {
			return reversePos(x1);
		}
		return x1;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		if (n == 0) {
			return 1;
		}
		int localx = x;
		for (int i = 1; i < n; i++) {
			x = times(x, localx);
		}
		return x;
	}

	// Returns the integer part of x1 / x2
	public static int div(int x1, int x2) {
		if (x1 == 0) {
			return 0;
		}
		if (x1 > 0 && x1 < x2) {
			return 0;
		}
		if (x1 < 0 && x2 < 0) {
			x1 = reverseNeg(x1);
			x2 = reverseNeg(x2);
		}
		int isNeg = isNeg(x1, x2);
		if (isNeg == 1) {
			if (x1 < 0) {
				x1 = reverseNeg(x1);
			} else {
				x2 = reverseNeg(x2);
			}
		}
		int timesMultiply = 1;
		int multiplyTester = x2;
		while (x1 > multiplyTester + x2) {
			timesMultiply = ++timesMultiply;
			multiplyTester = times(x2, timesMultiply);
			if (x1 > multiplyTester + x2) {
			} else {
				break;
			}
		}
		if (isNeg == 1) {
			return reversePos(timesMultiply);
		}
		if (x1 == (multiplyTester + x2)) {
			return ++timesMultiply;
		}
		return timesMultiply;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int reducedDiv = div(x1, x2);
		int numMoltiplayed = times(x2, reducedDiv);
		return minus(x1, numMoltiplayed);
	}

	// Returns the integer part of sqrt(x)
	public static int sqrt(int x) {
		if (x == 0) {
			return 0;
		}
		int num = 1;
		int squerd = 1;
		while (x != squerd) {
			num = ++num;
			squerd = pow(num, 2);
		}
		return num;
	}

	public static int isNeg(int x1, int x2) {
		if (x1 < 0 && x2 < 0) {
			return 0;
		}
		if (x1 > 0 && x2 > 0) {
			return 0;
		}
		return 1;
	}

	public static int reverseNeg(int x) {
		int plusX = 0;
		while (x < 0) {
			plusX = ++plusX;
			x = ++x;
		}
		return plusX;
	}

	public static int reversePos(int x) {
		int plusX = 0;
		while (x > 0) {
			plusX = --plusX;
			x = --x;
		}
		return plusX;
	}

}