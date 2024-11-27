// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
	 if (x2==0) {
		return x1;
	 }while (x2!= 0) {
		x1++;
		x2--;
	 }
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		if (x2 == 0) {
			return x1;
		}while (x2!=0) {
			x1--;
			x2--;
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		if (x1==0 || x2==0) {
			return 0;
		}
		int sum = x1;
		for(int i=0; i<x2-1;i++){
			sum = plus(sum, x1);
		}
		return sum;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		if (x==1 || n==0) {
			return 1;
		}
		int sum = x;
		for(int i = 0; i<n-1; i++){
			sum = times(sum, x);
		}
		return sum;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		if (x2 == 1) {
			return x1;
		}
		int count=0;
		int sum =x2;
		while (sum<=x1) {
			sum= plus(sum, x2);
			count++;
		}
		return count;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		if (x1<x2) {
			return x1;
		}
		int sum = x1;
		while (sum>=x2) {
			sum = minus(sum, x2);
		}
		return sum;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int base=1;
		while (pow(base, 2)<x) {
			if (pow(plus(base, 1), 2)>x) {
				return base;
			}
			base++;
		}
		return base;
	}	  	  
}
