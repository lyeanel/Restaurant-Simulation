package cs2030.simulator;
import java.util.ArrayList;
/** 
 * Server object
 **/

public class Server {
    private int id;
    private double readyTime = 0;
    private double waitreadyTime = 0;
    private static double avgTime = 0;
    private static int serveCount = 0;
    private static int leaveCount = 0;
    ArrayList<Record> waitList = new ArrayList<Record>();

    public Server(int id) {
	this.id = id;
    }

    public int getServerID() {
	return this.id;
    }

    public void addWaitCust(Record wait) {
	waitList.add(wait);
    }

    public void removeWaitCust(int index) {
	waitList.remove(index);
    }

    public Record getWaitingCust(int index) {
	return waitList.get(index);
    }

    public ArrayList getWaitingList() {
	return waitList;
    }
    
    public double getReadyTime() {
	return this.readyTime;
    }

    public void setReadyTime(double time) {
	this.readyTime = time;
    }
    
    public double getWaitTime() {
	return this.waitreadyTime;
    }

    public void setWaitTime() {
	this.waitreadyTime = this.readyTime;
    }

    public static double getAvgTime() {
	return avgTime;
    }

    public static void addAvgTime(double time) {
	avgTime = avgTime + time;
    }

    public static int getServeCount() {
	return serveCount;
    }
    
    public static void addServeCount() {
	serveCount++;
    }
    
    public static int getLeaveCount() {
	return leaveCount;
    }

    public static void addLeaveCount() {
	leaveCount++;
    }

    
}



