package Monte;

public class Stop {
	double x;
	
	public Stop(double x){
	   	this.x = x;
	}
	
	public static boolean stopp (double sigma, double x, int N){
	
		if ( x * sigma / Math.sqrt(N) <= 0.1){
			return true;
		}
		return false;
	}
	
	
}
