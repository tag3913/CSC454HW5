import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HW5{
    public static void main (String[] args){
		int a = 5;
        File newFile;
        Scanner scan;
        try{
            newFile = new File("inputs.txt");
            scan = new Scanner(newFile);
		}
		catch (FileNotFoundException FNFE){
			System.out.print("No such file in repo");
			return;
		}
        EventScheduler<Event> es = new EventScheduler<>();
        Model press = new Machine("Press", 1.0);
		Model drill = new Machine("Drill", 2.0);
		Machine.setScheduler(es);
		double[] mTime = {0,0};
		Machine.setTime(mTime);

		Network network = Network.builder().addModel(press).addModel(drill).addDelta(press).addLambda(drill).build();
		network.inOut(press,drill);
		press.newDelta(network);
		drill.newLambda(network);

        double previousTime = 0;
		while (scan.hasNext()){
            double time = scan.nextDouble();
            int quantity = scan.nextInt();
            double elapsed = time - previousTime;
            previousTime = time;

            for(Model m: network.getDeltas()){
				double[] eventTime = {time, 0};
				Event newEvent = new Event(eventTime, m, "External");
				newEvent.setQuantity(quantity);
				newEvent.setElapsed(elapsed);
				es.push(newEvent);
			}
		}
        System.out.println(es.toString());

        while(){

		}


	}
}