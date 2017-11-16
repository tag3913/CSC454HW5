public class Event implements Comparable<Event>{
    private double[] time;
    private Model model;
    private String delta = "";
    private final int REAL = 0;
    private final int DISCRETE = 1;

    private int quantity = 0;
    private double elapsedTime = 0;

    public double[] getTime(){
        return time;
    }

    public Model getModel(){
		return model;
	}

	public String getDelta(){
		return delta;
	}

    public Event(double[] newTime, Model m, String d){
	    this.time = newTime;
		model = m;
		delta = d;
    }

    public int compareTo(Event e){
		if(time[REAL] > e.getTime()[REAL]){
			return 1;
		}
		else { return 0; }
	}

	public String toString(){
		return " Delta: " + delta + "Time: " + time[REAL] + " Model: " + model + " Quantity: " + quantity + " Elapsed Time: " + elapsedTime;
	}

	public void setQuantity(int q){
		quantity = q;
	}

	public void setElapsed(double e){
			elapsedTime = e;
	}
}