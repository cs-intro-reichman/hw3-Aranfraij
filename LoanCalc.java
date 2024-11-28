public class LoanCalc {

    static double epsilon = 0.001;  // The computation tolerance (estimation error)
    static int iterationCounter;    // Monitors the efficiency of the calculation

    public static void main(String[] args) {

        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);

        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Brute force search
        iterationCounter = 0;
        System.out.print("Periodical payment, using brute force: ");
        double bruteForcePayment = bruteForceSolver(loan, rate, n, epsilon);
        System.out.printf("%.2f", bruteForcePayment);
        System.out.println("\nNumber of iterations: " + iterationCounter);

        // Bisection search
        iterationCounter = 0;
        System.out.print("Periodical payment, using bisection search: ");
        double bisectionPayment = bisectionSolver(loan, rate, n, epsilon);
        System.out.printf("%.2f", bisectionPayment);
        System.out.println("\nNumber of iterations: " + iterationCounter);
    }

    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double periodPay = loan / n;  // Start with an initial guess
        double step = loan / 10;      // Reasonable initial step size

        while (true) {
            double balance = endBalance(loan, rate, n, periodPay);
            if (Math.abs(balance) <= epsilon) break;

            if (balance > 0) {
                periodPay += step;  // Payment is too small
            } else {
                periodPay -= step;  // Payment is too large
            }

            // Reduce step size as balance approaches zero
            step = Math.max(step / 2, 0.01);

            iterationCounter++;
        }

        return periodPay;
    }

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double lowVal = loan / n;
        double highVal = loan;
        double periodPay;

        while ((highVal - lowVal) > epsilon) {
            periodPay = (lowVal + highVal) / 2;
            double balance = endBalance(loan, rate, n, periodPay);

            if (Math.abs(balance) <= epsilon) {
                return periodPay;
            } else if (balance > 0) {
                lowVal = periodPay;  // Payment is too small
            } else {
                highVal = periodPay;  // Payment is too large
            }

            iterationCounter++;
        }

        return (lowVal + highVal) / 2;
    }

    private static double endBalance(double loan, double rate, int n, double payment) {
        double currentLoan = loan;
        for (int i = 1; i <= n; i++) {
            currentLoan = (currentLoan - payment) * (1 + rate / 100);
        }
        return currentLoan;
    }
}
