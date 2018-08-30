package Monte;

import java.util.ArrayList;

public class GeometricPath implements StockPath {
   
	double spotprice;
	double sigma;
	double r;
   
   GeometricPath(double spotprice, double sigma, double r, int t){
	   this.spotprice = spotprice;
	   this.sigma = sigma;
	   this.r = r;
   }


	@Override
	public ArrayList<Double> getPrices(ArrayList<Double> vectors) {
		ArrayList<Double> prices = new ArrayList<Double>(vectors.size());
		prices.add(0, spotprice);
	    for(int i = 1; i < vectors.size(); i++){
	    	double newprice = prices.get(i-1) * Math.exp(r-0.5*sigma*sigma + sigma*vectors.get(i-1));
	    	prices.add(i, newprice);
	}
		return prices;
	   
	}
}
