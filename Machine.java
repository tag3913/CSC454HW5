import java.util.ArrayList;

@SuppressWarnings("unchecked")

public class Machine implements Model{

	//class variables
    private double processTime; //s
    private String type;  //either drill or press
    private int partsNum; //p
    private double timeToComplete; //t
    private final double INFINITY = 9999999;

    private static EventScheduler ES;

    private ArrayList<Model> deltas;
	private ArrayList<Model> lambdas;

    private static double[] currentTime = new double[2];
    private double[] previousTime = new double[2];

    //class constants
    private final int REAL = 0;
    private final int DISCRETE = 1;

    public Machine(String machineType, double t){
		timeToComplete = t;
		type = machineType;
        processTime = 0;
        partsNum = 0;
        previousTime[REAL] = 0;
        previousTime[DISCRETE] = 0;
        this.deltas = new ArrayList<Model>();
		this.lambdas = new ArrayList<Model>();
    }

    // time advance function is ta((p,s)) = s if p > 0 , and infinity otherwise.
    public double timeAdvance(){
		if(partsNum > 0){
			return processTime;
		}
		return INFINITY;
	}

    //internal state transition function is deltaint((p, s)) = (p - 1, t)
    public void deltaInt(){
		previousTime = currentTime;
		partsNum--;
		processTime = timeToComplete;

		for(Model m : lambdas){
			double[] eventTime = {currentTime[REAL], 0};
            Event event = new Event(eventTime, m, "External");
            event.setQuantity(lambda());
            ES.push(event);
		}

		if(partsNum > 0){
			double[] eventTime = {currentTime[REAL] + timeAdvance(), 0};
			Event event = new Event(eventTime, this, "Internal");
		    ES.push(event);
		}
	}

    //deltaext((p, s), e, q) =
    //(p + q, s - e) if p > 0
    //(p + q, t) if p = 0
    public void deltaExt(double elapsedTime, int newParts){
		previousTime = currentTime;
		Event newEvent = null;
		if(partsNum > 0){
			partsNum += newParts;
            processTime = processTime - elapsedTime;

            for(Comparable c : ES.getHeap()){
                Event event = (Event) c;
                if(event != null && event.getModel() == this && event.getDelta() == "Internal"){
					if (ES.remove(event)){
						double[] eventTime = {currentTime[REAL] + timeAdvance(), 0};
                        newEvent = new Event(eventTime, this, "Internal");
					}
				}
		    }
		}
		else{
			// idle
			partsNum += newParts;
			processTime = timeToComplete;
			double[] eventTime = {currentTime[REAL] + timeToComplete, 0};
            newEvent = new Event(eventTime, this, "Internal");
		}
		ES.push(newEvent);
	}

    // deltacon((p, s), q) = (p + q - 1, t).
	public void deltaCon(int newParts){
		previousTime = currentTime;
		processTime = timeToComplete;
		partsNum += (newParts - 1); // to compensate for the part that finished

		double[] eventTime = {currentTime[REAL] + timeAdvance(), 0};
        Event newEvent = new Event(eventTime, this, "Internal");
        ES.push(newEvent);

        for (Model m : lambdas){
			double[] nextEventTime = {currentTime[REAL], 0};
            newEvent = new Event(nextEventTime, m, "Internal");
            newEvent.setQuantity(lambda());
            ES.push(newEvent);
		}
	}


	//lambda always returns 1;
	public int lambda(){
	       return 1;
    }

	public static void setTime(double[] t){
		currentTime = t;
	}

	public double[] getTime(){
		return currentTime;
	}

	public static void setScheduler(EventScheduler es){
		ES = es;
	}

	public void newLambda(Model m){
		lambdas.add(m);
	}

	public void newDelta(Model m){
		deltas.add(m);
	}

	public String toString(){
	   return "Type: " + type + "Number of Parts: " + partsNum + "Process Time: " + processTime;
	}


}