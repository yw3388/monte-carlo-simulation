package Monte;

import java.util.ArrayList;

public class Stats {
    
	ArrayList<Double>_initial = new ArrayList<Double>();//initial arraylist of payouts zero
	int N;//times of simulation
	int curr_iter = 0;		// current iteration idx
	double mu = 0;
	double sigma = 0;
	double secondMom;
	
	public Stats(int N){
		this.N = N;
	}
		
	public void setExpectation(ArrayList<Double> initial){
		this._initial = initial;
		double sum = 0;
		for(int n = 0; n < initial.size(); n++){
			sum += initial.get(n);	
		}
		this.mu = sum/initial.size();
	}
	
	public void setSigma(){
		
		this.sigma = Math.sqrt(secondMom-mu*mu);
	}
	
	public double getExpectation(){
		return this.mu;
	}
	
	public double getSigma(){
		return this.sigma;
	}
	public double getSecondMoments(){
		return this.secondMom;
	}
	
	public int getTimes(){
		return this.curr_iter;
	}
	
	public void newstats(double newPayout){
		_initial.add(newPayout);
		curr_iter++;
		if(curr_iter == 1){
			mu = newPayout;
			sigma = 0;
			return;
		}
		this.mu =( mu * (curr_iter-1) + newPayout)/(curr_iter);
		double r = 0;
		for(int i =0; i < curr_iter; i++){
			r = r + (_initial.get(i) - mu) * (_initial.get(i) - mu);
		}
		sigma = Math.sqrt(r/curr_iter);
	}
}
