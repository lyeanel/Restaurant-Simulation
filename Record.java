package cs2030.simulator;

public class Record implements Comparable<Record> {

    public static final int DONE = 0;
    public static final int ARRIVES = 1;
    public static final int SERVED = 2;
    public static final int LEAVES = 3;
    public static final int WAITS = 4;
    
    private int id;
    private double arrivalTime;
    private int status;
    private int servedBy;
    private String outcome;
    private double waitTime;

    public Record(int id, double arrivalTime, int status) //leaves without being served 
    {
	this.id = id;
	this.arrivalTime = arrivalTime;
	this.status = status; 
	this.outcome = getOutcome();
    }
    public Record(int id, double arrivalTime, int status, int servedBy) {
	this.id = id;
	this.arrivalTime = arrivalTime;
	this.status = status;
	this.servedBy = servedBy;
	this.waitTime = 0;
    }
    
    public void setWaitTime(double time) {
	this.waitTime = time;
    }
    

    public int getID() {
	return this.id;
    }

    public double getArrival() {
	return this.arrivalTime;
    }

    public int getStatus() {
	return this.status;
    }
    
    public int getServedBy(){
	return this.servedBy;
    }

    public String getOutcome() {
	String arr = String.format("%.3f", getArrival());
	if (this.status == ARRIVES) {
	    return arr + " " + this.getID() + " arrives";
	}

	else if (this.status == SERVED) {
	    return arr + " " + this.getID() + " served by " + this.getServedBy();
	}

	else if (this.status == DONE) {
	    return arr + " " + this.getID() + " done serving by " + this.getServedBy();
	}

	else if (this.status == WAITS) {
	    return arr + " " + this.getID() + " waits to be served by " + this.getServedBy();
	}

	else {
	    return arr + " " + this.getID() + " leaves";
	}
    }
    @Override
    public int compareTo(Record r){
	if (this.getArrival() > r.getArrival()) {
	    return 1;
	}
	else if (this.getArrival() == r.getArrival()) {
	    if (this.getID() > r.getID()) {
		return 1;
	    }
	    else if (this.getID() == r.getID()) {
		if (this.getStatus() < r.getStatus()) {
		    return -1;
		}
		else if (this.getStatus() == r.getStatus()) {
		    return 0;
		}
		else {
		    return 1;
		}
	    }
	    else {
		return -1;
	    }
	}
	else {
	    return -1;
	}
    }
}




	
            
