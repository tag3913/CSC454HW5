public class Event {
    private double[] time;
    public String action;
    public String model;

    public double[] getTime(){
        return time;
    }

    public Event(double[] newTime, String newAction){
	    this.time = newTime;
		action = newAction;
    }
}