package cs2030.simulator;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Manager {
    ArrayList<String> output = new ArrayList<String>();
    PriorityQueue<Record> queue = new PriorityQueue<Record>();

    ArrayList<Customer> cList;
    ArrayList<Server> sList;


    public Manager(ArrayList<Customer> cList, ArrayList<Server> sList) {
	this.cList = cList;
	this.sList = sList;
    }

    public void addStatuses(Random rg) {

	for (int i = 0; i < cList.size(); i++) {
	    Customer c = cList.get(i);
	    queue.add(new Record(c.getID(), c.getArrival(), Record.ARRIVES));
	} //add everything into priority queue

	while (queue.size() != 0) {
	    int check = 0;
	    boolean canWait = true;
	    Record r = queue.remove(); //can be used
	    if (r.getStatus() == Record.ARRIVES) {
		for (int i = 0; i < sList.size(); i++) {
		    if (r.getArrival() >= sList.get(i).getReadyTime()) {
			queue.add(new Record(r.getID(), r.getArrival(), Record.SERVED, sList.get(i).getServerID()));
			double tempsvc1 = r.getArrival();
			double randomsvc1 = rg.getSvcTime();
			tempsvc1 += randomsvc1;
			sList.get(i).setReadyTime(tempsvc1);

			check++;
			canWait = false;
			Server.addServeCount();
			break;
		    }
		}

		if (canWait) {
		    for (int i = 0; i < sList.size(); i++) {
			if (r.getArrival() >= sList.get(i).getWaitTime()) {
			    Record waitCust = new Record(r.getID(), r.getArrival(), Record.WAITS, sList.get(i).getServerID());
			    queue.add(waitCust);
			    sList.get(i).setWaitTime();
			    waitCust.setWaitTime(sList.get(i).getWaitTime());
			    sList.get(i).addWaitCust(waitCust);
			    Server.addAvgTime(sList.get(i).getWaitTime() - r.getArrival());
			    check++;
			    break;
			}
		    }
		}
		if (check == 0) {
		    queue.add(new Record(r.getID(), r.getArrival(), Record.LEAVES));
		    Server.addLeaveCount();
		}
	    }
	    else if (r.getStatus() == Record.SERVED) {
		int a = r.getServedBy() - 1;
		queue.add(new Record(r.getID(), sList.get(a).getReadyTime(), Record.DONE, sList.get(a).getServerID()));
		
    
	    }
	    else if (r.getStatus() == Record.DONE) {
		int a = r.getServedBy() - 1;

		Server finished = sList.get(a);
		if (finished.getWaitingList().size() > 0) {
		    for (int i = 0; i < finished.getWaitingList().size(); i++) {
			Record servednew = new Record(finished.getWaitingCust(i).getID(), finished.getWaitTime(), Record.SERVED, finished.getServerID());
			queue.add(servednew);
			double tempsvc2 = finished.getWaitTime();
			double randomsvc2 = rg.getSvcTime();
			tempsvc2 += randomsvc2;
			finished.setReadyTime(tempsvc2);
			Server.addServeCount();
			finished.removeWaitCust(i);
			i--;
		    }
		}
	    }
	    output.add(r.getOutcome());

	}
    }

    public void printQueue() {
	for (Record r : queue) {
	    System.out.println(r.getOutcome());
	}
    }
    public void printStats() {
	for (int i = 0; i < output.size(); i++) {
	    System.out.println(output.get(i));
	}
	System.out.println("[" + String.format("%.3f", Server.getAvgTime() / Server.getServeCount()) + " " + Server.getServeCount() + " " + Server.getLeaveCount() + "]");
    }
}
		






