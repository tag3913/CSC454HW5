public class Drill implements Model{
    private int processTime;
    private int partsNum;
    private final int TIME_TO_COMPLETE = 2;
    private final int INFINITY = 999;
    private double[] Time;
    private final int REAL = 0;
    private final int DISCRETE = 1;

    public Drill(){
        processTime = 0;
        partsNum = 0;
    }

    public int timeAdvance(){
		if(partsNum > 0){
			return processTime;
		}
		return INFINITY;
	}

    public void deltaInt(){
		partsNum--;
		processTime = TIME_TO_COMPLETE;
		schedule("deltaInt");
	}

    public int lambda(){
       return 1;
    }

    public void deltaExt(int elapsedTime, int newParts){
		numParts += newParts;
		if(numParts == 0){
			processTime -= elapsedTime;
		}
		else{
			processTime = TIME_TO_COMPLETE;
		}
		schedule("deltaExt");
	}

	public void schedule(String action){
		Time = {Time[REAL] + timeAdvance(), 0);

	}

	public void setTime(double realTime, double discreteTime){
		Time = {realTime, discreteTime};
	}

}