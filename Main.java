import java.util.Scanner;
import java.util.ArrayList;
import cs2030.simulator.Record;
import cs2030.simulator.Manager;
import cs2030.simulator.Customer;
import cs2030.simulator.Server;
import cs2030.simulator.Random;


/** Represents the Main Class
 * @author Lew Kee Siong Lionel
 * @author e0318709@u.nus.edu
 * @author A0185418X
 **/


public class Main {
    public static void main(String[] args) {
	ArrayList<Customer> cList = new ArrayList<Customer>();
	ArrayList<Server> sList = new ArrayList<Server>();
	Scanner sc = new Scanner(System.in);
	// get input from user
	int seedvalue = sc.nextInt();
	int noServers = sc.nextInt();
	int noCustomers = sc.nextInt();
	double arrivalRate = sc.nextDouble();
	double serviceRate = sc.nextDouble();

	Random randomgen = new Random(seedvalue, arrivalRate, serviceRate);
	
	// create server list
	for (int i = 0; i < noServers; i++) {
	    sList.add(new Server(i+1));
	}


	// create first customer with timestamp 0.00
	cList.add(new Customer(0));
	double tempTime = 0;

	for (int i = 0; i < noCustomers-1; i++) {
	    double randomtime = randomgen.getArrTime();
	    tempTime += randomtime;
	    cList.add(new Customer(tempTime));
	}
	
	Manager m = new Manager(cList, sList);
	m.addStatuses(randomgen);
	m.printStats();
    }
}
	

	    
