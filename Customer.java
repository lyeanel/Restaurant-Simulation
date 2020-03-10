package cs2030.simulator;
/**
 * Customer object 
 * Includes ID, arrival time as object variables
 * Every creation adds into a total number counter
 * Has getter methods to retrieve information
 **/

public class Customer {
    private static int totalCust = 0;
    private int id;
    private double arrivalTime;

    /***
     * Constructor for Customer class
     * ID beginning from 1 assigned by arrival time as parameter
     ***/

    public Customer(double arrivalTime) {
	Customer.totalCust++; //1st object created = 1, ...
	this.arrivalTime = arrivalTime;
	this.id = Customer.totalCust; 
    }

    /**** Getters
     * ID, arrival time, number of customers, time as a string
     * ***/

    public int getID() {
	return this.id;
    }

    public double getArrival() {
	return this.arrivalTime;
    }

    public static int getCustCount() {
	return totalCust;
    }
    public String getString() {
	return String.format(".3f", this.arrivalTime);
    }
}
	

