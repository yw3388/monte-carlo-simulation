package Monte;

import java.util.ArrayList;

public class Pricing {
	double strikeprice, spotprice, sigma, interestrate;
	PayOut payout;
	Stop stop;
	RandomVectorGenerator rvg;
    Stats stats;
	StockPath path;
	int maturitydate;
	    static double p = 0.04;
        static double t = Math.sqrt(Math.log(1/(p*p)));
        static double c0 = 2.515517;
		static double c1 = 0.802853;
		static double c2 = 0.010328;
		static double d1 = 1.432788;
		static double d2 = 0.189269;
	    static double d3 = 0.001308;
	    double x = t - (c0 + c1 * t + c2 * t * t)/(1 + d1 * t+ d2 * t * t + d3 * t* t * t);

	
	public Pricing(double strikeprice, double sigma, double interestrate, double spotprice, int t){
		this.strikeprice = strikeprice;
		this.sigma = sigma;
		this.interestrate = interestrate;
		this.spotprice = spotprice;
		this.maturitydate = t;
	}
	
	   public void MonteCarlocall(int N){
		   stats = new Stats(N);
		   Stop stop = new Stop(x);
		   //Generate random variable
		   NormalRandomVectorGenerator g = new NormalRandomVectorGenerator(maturitydate);
		   //Get price
		   Antithetic anti = new Antithetic(g, maturitydate);
		   ArrayList<Double> vectors = anti.getVector();
		   GeometricPath path = new GeometricPath(152.35, 0.01, 0.0001, 252);
		    Callpayout payout = new Callpayout(165);
		    double a;
		    int j = 0;
		    double a1 = sigma;
			while ((Stop.stopp(a1, x, j) != true|| j < 100) ){  
				if( j > 100000 ){
					System.out.println("too much");
					break;
				}
				    	vectors = anti.getVector();
				    	 a = payout.getPayout(path, vectors);
				    	 stats.newstats(a);
		    	         a1 = stats.getSigma();
		    	         j++;
		    		}
			}
			
	   
		      
	   public void MonteCarloAsiancall(int N){
		    stats = new Stats(N);
		   //Generate random variable
		   NormalRandomVectorGenerator g2 = new NormalRandomVectorGenerator(maturitydate);
		   //Get price
		   Antithetic antit = new Antithetic(g2, maturitydate);
		   ArrayList<Double> vectors2 = antit.getVector();
		   GeometricPath path = new GeometricPath(152.35, 0.01, 0.0001, 252);
		    Asianpayout payout = new Asianpayout(164);
		    double b;
		    int j = 0;
		    double b1 = sigma;
		    while ((Stop.stopp(b1, x, j) != true|| j < 100) ){  
				if( j > 100000 ){
					System.out.println("too much");
					break;
				}
		    	vectors2 = antit.getVector();
		        b = payout.getPayout(path, vectors2);
		    	stats.newstats(b);
		    	 b1 = stats.getSigma();
    	         j++;
		    }
	   }  
	   
	   public static void main(String[] args){
		   int maturitydate = 252;
		   NormalRandomVectorGenerator g = new NormalRandomVectorGenerator(maturitydate);
		    	Pricing p1 = new Pricing(165, 0.01, 0.0001, 152.35 ,252);
		    	p1.MonteCarlocall(10000);
		    	p1.MonteCarloAsiancall(10000);
		    	


		   
	   }
 }

	
	

