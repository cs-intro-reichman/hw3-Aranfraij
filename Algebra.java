// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
    public static void main(String args[]) {
        // Tests
        System.out.println(plus(2, 3));      // 2 + 3
        System.out.println(minus(7, 2));     // 7 - 2
        System.out.println(minus(2, 7));     // 2 - 7
        System.out.println(times(3, -4));    // 3 * -4
        System.out.println(plus(2, times(4, 2))); // 2 + 4 * 2
        System.out.println(pow(-5, 3));      // (-5)^3
        System.out.println(pow(3, 5));       // 3^5
        System.out.println(div(-12, 3));     // -12 / 3    
        System.out.println(div(5, -5));      // 5 / -5  
        System.out.println(div(25, 7));      // 25 / 7
        System.out.println(mod(25, 7));      // 25 % 7
        System.out.println(mod(120, 6));     // 120 % 6    
        System.out.println(sqrt(36));        // sqrt(36)
        System.out.println(sqrt(263169));    // sqrt(263169)
        System.out.println(sqrt(0));         // sqrt(0)
    }

    // Returns x1 + x2
    public static int plus(int x1, int x2) {
        if (x2 == 0) {
            return x1;
        }
        while (x2 != 0) {
            x1++;
            x2--;
        }
        return x1;
    }

    // Returns x1 - x2
    public static int minus(int x1, int x2) {
        if (x2 == 0) {
            return x1;
        }
        while (x2 != 0) {
            x1--;
            x2--;
        }
        return x1;
    }

    // Returns x1 * x2
    public static int times(int x1, int x2) {
        boolean isNegative = (x1 < 0 && x2 >= 0) || (x1 >= 0 && x2 < 0);
        x1 = x1 < 0 ? minus(0, x1) : x1; // Convert to positive
        x2 = x2 < 0 ? minus(0, x2) : x2; // Convert to positive
        int sum = 0;
        for (int i = 0; i < x2; i++) {
            sum = plus(sum, x1);
        }
        return isNegative ? minus(0, sum) : sum;
    }

    // Returns x^n (for n >= 0)
    public static int pow(int x, int n) {
        if (n == 0) {
            return 1;
        }
        boolean isNegative = x < 0 && n % 2 != 0; // Result is negative if base is negative and power is odd
        x = x < 0 ? minus(0, x) : x; // Convert to positive
        int result = 1;
        for (int i = 0; i < n; i++) {
            result = times(result, x);
        }
        return isNegative ? minus(0, result) : result;
    }

    // Returns the integer part of x1 / x2 
    public static int div(int x1, int x2) {
        boolean isNegative = (x1 < 0 && x2 >= 0) || (x1 >= 0 && x2 < 0);
        x1 = x1 < 0 ? minus(0, x1) : x1;
        x2 = x2 < 0 ? minus(0, x2) : x2;
        int count = 0;
        while (x1 >= x2) {
            x1 = minus(x1, x2);
            count = plus(count, 1);
        }
        return isNegative ? minus(0, count) : count;
    }

    // Returns x1 % x2
    public static int mod(int x1, int x2) {
        if (x1 < x2) {
            return x1;
        }
        while (x1 >= x2) {
            x1 = minus(x1, x2);
        }
        return x1;
    }    

    // Returns the integer part of sqrt(x) 
    public static int sqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int base = 1;
        while (pow(base, 2) <= x) {
            if (pow(plus(base, 1), 2) > x) {
                return base;
            }
            base = plus(base, 1);
        }
        return base;
    }     
}
