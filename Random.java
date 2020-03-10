package cs2030.simulator;

import cs2030.simulator.RandomGenerator;

public class Random {
    public RandomGenerator rg;
    public int seed;
    public double lambda;
    public double mu;

    public Random(int seed, double lambda, double mu) {
	this.rg = new RandomGenerator(seed, lambda, mu);
    }
    
    public RandomGenerator getRNG() {
	return this.rg;
    }

    public double getArrTime() {
	return this.rg.genInterArrivalTime();
    }

    public double getSvcTime() {
	return this.rg.genServiceTime();
    }
}

    


    
