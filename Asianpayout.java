package Monte;

import java.util.ArrayList;

public class Asianpayout implements PayOut{
        ArrayList<Double>vectors; //random variables given to path
        double strikeprice;
        
        Asianpayout(double strikeprice){
        	this.strikeprice = strikeprice;
        }
        
	@Override
	public double getPayout(StockPath path, ArrayList<Double> vectors) {
		// TODO Auto-generated method stub
		this.vectors = vectors;
		ArrayList<Double> prices2 = path.getPrices(vectors);
		//ArrayList<Double> Payoutlist = new ArrayList<Double>(prices2.size());
	    double Payout = 0;
	    for(int i = 0; i < prices2.size(); i++){
	    Payout += (double) prices2.get(i);
	    }
	    Payout /= (double) prices2.size();
	    Payout = Math.max(0.0, Payout-strikeprice);
	    return Payout;
	    
	}
	
	
	/*public static void main(String[] args){
		ArrayList vectors = new ArrayList<Double>(2);
		vectors.add(2.2);
		vectors.add(2.3);
		GeometricPath p = new GeometricPath(3, 0.01, 0.00001, 3);
		ArrayList<Double> prices = p.getPrices(vectors);
		//ArrayList<Double> Payoutlist = new ArrayList<Double>(prices2.size());
	    double Payout = 0;
	    for(int i = 0; i < prices.size(); i++){
	    Payout += (double) prices.get(i);
	    }
	    Payout /= (double) prices.size();
	    Payout = Math.max(0.0, Payout-3);
		System.out.println(Payout);
	*/	
		
		
		
	}

