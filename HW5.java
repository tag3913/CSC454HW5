import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HW5{
    public static void main (String[] args){
        File file;
        Scanner scan;
        try{
            newFile = new File("inputs.txt");
            scan = new Scanner(newFile);
		}
		catch (FileNotFoundException FNFE){
			System.out.print("No such file in repo");
		}

		Model Drill = new Drill();
		Model Press = new Press();
		Network network = Network.builder().addModel(Press).addModel(Drill).build();



		while (scanner.hasNext()){

		}










































































		}
    }

}