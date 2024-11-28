public class LoanCalc {

    static double epsilon = 0.001;  // The computation tolerance (estimation error)
    static int iterationCounter;    // Monitors the efficiency of the calculation

    public static void main(String[] args) {
        // Gets the loan data
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);

        System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Computes the periodical payment using brute force search
        System.out.print("Periodical payment, using brute force: ");
        System.out.printf("%d", (int) Math.floor(bruteForceSolver(loan, rate, n, epsilon)));
        System.out.println();
        System.out.println("number of iterations: " + iterationCounter);

        // Computes the periodical payment using bisection search
        System.out.print("Periodical payment, using bi-section search: ");
        System.out.printf("%d", (int) Math.floor(bisectionSolver(loan, rate, n, epsilon)));
        System.out.println();
        System.out.println("number of iterations: " + iterationCounter);
    }

    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double currentLoan = loan;
        int periodPay = (int) (loan / n); // Start with an integer payment

        while (true) {
            currentLoan = endBalance(loan, rate, n, periodPay);

            // If the remaining balance is close enough to 0, stop searching
            if (Math.abs(currentLoan) <= epsilon) break;

            // Increment the payment by 1 if the balance is positive
            periodPay++;

            iterationCounter++;
        }

        return periodPay;
    }

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        int lowVal = (int) (loan / n);
        int highVal = (int) loan;
        int periodPay;

        while (highVal - lowVal > 1) {
            periodPay = (lowVal + highVal) / 2;
            double balance = endBalance(loan, rate, n, periodPay);

            if (Math.abs(balance) <= epsilon) {
                return periodPay;
            } else if (balance > 0) {
                lowVal = periodPay; // Payment is too small
            } else {
                highVal = periodPay; // Payment is too large
            }

            iterationCounter++;
        }

        // Return the floor of the best payment
        return lowVal;
    }

    private static double endBalance(double loan, double rate, int n, double payment) {
        double currentLoan = loan;

        for (int i = 1; i <= n; i++) {
            currentLoan = (currentLoan - payment) * (1 + rate / 100);
        }

        return currentLoan;
    }
}
