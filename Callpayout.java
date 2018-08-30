package Monte;

import java.util.ArrayList;

public class Callpayout implements PayOut {
    double strikeprice;
    ArrayList<Double> vectors;//random variables given to path
    
       Callpayout(double strikeprice){
    	this.strikeprice = strikeprice;
    }
	
	@Override
	public double getPayout(StockPath path, ArrayList<Double> vectors) {
		    this.vectors = vectors;
		    ArrayList<Double> prices = path.getPrices(vectors);
		    double Pricet= (double) prices.get(vectors.size()-1);
			return Math.max(0.0, Pricet- strikeprice);
		}
	
}


