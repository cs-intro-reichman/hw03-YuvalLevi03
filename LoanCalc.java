public class LoanCalc {
	
	static double epsilon = 0.001;  // The computation tolerance (estimation error)
	static int iterationCounter;    // Monitors the efficiency of the calculation
	
	public static void main(String[] args) {
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		
                System.out.print("Periodical payment, using brute force: ");
		System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);

		System.out.print("Periodical payment, using bi-section search: ");
		System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
	}
       public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
    	iterationCounter = 0;
    	double x = loan / n;
    		while ( endBalance(loan,rate,n,x) > 0 ) {
                    x += epsilon;
    		    iterationCounter++;
    	        }
          return x;
        }

        public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
    	iterationCounter = 0;
    	double low = loan / n;
    	double high = loan;
        double x = (high + low) / 2;
        while ( Math.abs(endBalance(loan,rate,n,x)) >= epsilon ) {
    	    if ( endBalance(loan,rate,n,x) > 0 ) {
    	    	 low = x;
	    } else { 
    	    	 high = x;
	    }
    	 x = (high + low) / 2;
    	 iterationCounter++;
    	}
    	    return x;
        }
	
      		public static double endBalance(double loan, double rate, int n, double payment) {
		double paymentLeft = loan;
		double sum = 0;
		for (int i = 0 ; i < n ; i++ ) {
    	           sum = (paymentLeft - payment) * ( 1 + rate / 100) ;
                   paymentLeft = sum;
                }
    	        return paymentLeft; 
                }  
}

