public class LoanCalc {

    static double epsilon = 0.001;  // The computation tolerance (estimation error)
    static int iterationCounter;   // Monitors the efficiency of the calculation

    public static void main(String[] args) {

        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);

        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Computes the periodical payment using brute force search
        iterationCounter = 0; // Reset counter
        System.out.print("Periodical payment, using brute force: ");
        System.out.printf("%.0f", Math.floor(bruteForceSolver(loan, rate, n, epsilon)));
        System.out.println();
        System.out.println("number of iterations: " + iterationCounter);

        // Computes the periodical payment using bisection search
        iterationCounter = 0; // Reset counter
        System.out.print("Periodical payment, using bi-section search: ");
        System.out.printf("%.0f", Math.floor(bisectionSolver(loan, rate, n, epsilon)));
        System.out.println();
        System.out.println("number of iterations: " + iterationCounter);
    }

    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {

        iterationCounter = 0; // Ensure the counter starts at 0
        double currentLoan = loan;
        double periodPay = loan / n;

        while (currentLoan >= epsilon) {

            currentLoan = endBalance(loan, rate, n, periodPay);

            if (currentLoan <= epsilon) {
                break;
            } else {
                periodPay += epsilon / 10; // Smaller step size to reduce overshooting
                currentLoan = loan; // Reset the loan balance for recalculation
            }

            iterationCounter++; // Increment counter correctly
        }

        return periodPay;
    }

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {

        iterationCounter = 0; // Ensure the counter starts at 0
        double lowVal = 0;
        double highVal = loan;
        double periodPay = (lowVal + highVal) / 2;

        while ((highVal - lowVal) > epsilon) {
            if (endBalance(loan, rate, n, periodPay) * endBalance(loan, rate, n, lowVal) > 0) {
                lowVal = periodPay;
            } else {
                highVal = periodPay;
            }

            periodPay = (lowVal + highVal) / 2;

            iterationCounter++; // Increment counter correctly
        }

        return periodPay;
    }

    private static double endBalance(double loan, double rate, int n, double payment) {

        double currentLoan = loan;

        // Calculate the remaining loan balance after n periods
        for (int i = 1; i <= n; i++) {
            currentLoan = (currentLoan - payment) * (1 + rate / 100);
        }

        return currentLoan;
    }
}
