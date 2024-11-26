// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {

	static double epsilon = 0.001; // Approximation accuracy
	static int iterationCounter; // Number of iterations

	// Gets the loan data and computes the periodical payment.
	// Expects to get three command-line arguments: loan amount (double),
	// interest rate (double, as a percentage), and number of payments (int).
	public static void main(String[] args) {
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = (Double.parseDouble(args[1]) / 100);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the
	// periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {
		for (int i = 0; i <= n; i++) {
			loan = loan - payment;
			loan = loan * (1 + rate);
		}
		return loan;
	}

	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		double periodicalPayment = epsilon;
		// double periodicalPayment = 11465.5;
		double payedLoan = epsilon * 1000;
		int iterationCount = 0;
		while (Math.abs(payedLoan) >= epsilon) {
			periodicalPayment = (periodicalPayment + (epsilon / 100));
			payedLoan = endBalance(loan, rate, n, periodicalPayment);
			++iterationCount;
		}
		return ++iterationCount;
	}

	// Uses bisection search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
	public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		double L = 1.0, H = loan;
		double mid = (L + H) / 2.0;
		int stepCounter = 0;
		double payedLoan = epsilon * 100;
		while (Math.abs(payedLoan) >= epsilon) {
			payedLoan = endBalance(loan, rate, n, mid);
			if (payedLoan > 0) {
				L = mid;
			} else {
				H = mid;
			}
			mid = (L + H) / 2;
			stepCounter++;
		}
		return stepCounter;
	}
}