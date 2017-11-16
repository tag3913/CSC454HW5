import java.util.ArrayList;

public class Network implements Model{
	private Model[] models;
	private int counter = 0;
	private ArrayList<Model> deltas;
	private ArrayList<Model> lambdas;

    //couples the models in a generic fashion
	public void inOut(Model in, Model out){
		in.newLambda(out);
		out.newDelta(in);
	}

    public ArrayList<Model> getDeltas(){
		return deltas;
	}

	public ArrayList<Model> getLambdas(){
		return lambdas;
	}

	public Model[] getModels(){
		return models;
	}

	public static NetworkBuilder builder(){
		return new NetworkBuilder();
	}

    //included these in order to make Network a Model
	public double timeAdvance(){return 0;};
	public void deltaInt(){};
	public int lambda(){return 1;};
	public void deltaExt(double e, int q){};
	public void deltaCon(int q){};

	public void newDelta(Model in){};
    public void newLambda(Model out){};

	public static class NetworkBuilder {
		private Model[] models = new Model[50];
		private int counter = 0;
        private ArrayList<Model> deltas = new ArrayList<Model>();
		private ArrayList<Model> lambdas = new ArrayList<Model>();

		public NetworkBuilder addModel(Model nextModel){
		   this.models[counter] = nextModel;
		   counter++;
		   return this;
		}

		public NetworkBuilder addDelta(Model in){
			this.deltas.add(in);
			return this;
		}

		public NetworkBuilder addLambda(Model out){
			this.lambdas.add(out);
			return this;
		}

		public Network build(){
			Network network = new Network();
			network.models = this.models;
		    network.counter = this.models.length;
		    network.deltas = this.deltas;
			network.lambdas = this.lambdas;
		    return network;
		}
	}
}