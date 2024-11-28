/**
 * Computes the periodical payment necessary to re-pay a given loan.
 */
public class LoanCalc{

    static double epsilon = 0.001;  // The computation tolerance (estimation error)
    static int iterationCounter;    // Monitors the efficiency of the calculation

    /**
     * Gets the loan data and computes the periodical payment.
     * Expects to get three command-line arguments: sum of the loan (double),
     * interest rate (double, as a percentage), and number of payments (int).
     */
    public static void main(String[] args) {
        // Gets the loan data
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);

        System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Computes the periodical payment using brute force search
        System.out.print("Periodical payment, using brute force: ");
        System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
        System.out.println();
        System.out.println("number of iterations: " + iterationCounter);

      //  iterationCounter = 0; // resetting

        // Computes the periodical payment using bisection search
        System.out.print("Periodical payment, using bi-section search: ");
        System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
        System.out.println();
        System.out.println("number of iterations: " + iterationCounter);
    }

    /**
     * Uses a sequential search method  ("brute force") to compute an approximation
     * of the periodical payment that will bring the ending balance of a loan close to 0.
     * Given: the sum of the loan, the periodical interest rate (as a percentage),
     * the number of periods (n), and epsilon, a tolerance level.
     */
    // Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {

        iterationCounter = 0;
        double currentLoan = loan; // temp for calculations
        double periodPay = loan / n;


        while(currentLoan >= epsilon){

            currentLoan = endBalance(loan,rate,n,periodPay);
//
          if(currentLoan <= epsilon)
               break;

            else {
                periodPay = periodPay + epsilon;
                currentLoan = loan;
           }

            iterationCounter++;
        }

        return periodPay;
    }

    /**
     * Uses bisection search to compute an approximation of the periodical payment
     * that will bring the ending balance of a loan close to 0.
     * Given: the sum of the loan, the periodical interest rate (as a percentage),
     * the number of periods (n), and epsilon, a tolerance level.
     */
    // Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {

        iterationCounter = 0;
        double lowVal = 0;
        double highVal = loan;
        double periodPay = (lowVal + highVal) / 2;

        while ( (highVal - lowVal) > epsilon) {
            //resetting the low and high value for the next iteration
            if(endBalance(loan, rate, n, periodPay) * endBalance(loan, rate, n, lowVal) > 0)
                lowVal = periodPay;

            else
                highVal = periodPay;

            periodPay = (lowVal + highVal) / 2;

            iterationCounter++;
        }


        return periodPay;
    }

    /**
     * Computes the ending balance of a loan, given the sum of the loan, the periodical
     * interest rate (as a percentage), the number of periods (n), and the periodical payment.
     */
    private static double endBalance(double loan, double rate, int n, double payment) {

        double currentLoan = loan; // temp for calculations

        // solve for periodPay: n * (currentLoan - periodPay)*(1 + rate/100))
        for(int i = 1; i <= n; i++) {
            currentLoan = (currentLoan - payment) * (1 + rate / 100);
        }

        return currentLoan;
    }
}   
