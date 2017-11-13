public class Network {
	private AtomicModel[] models;
	private int counter = 0;

	private Network(){

	}

	public static NetworkBuilder builder(){
		return new NetworkBuilder();
	}

	public static class NetworkBuilder {
		private Model[] models;
		private int counter = 0;
		int numberOfModels;

		public NetworkBuilder(int numModels){
			numberOfModels = numModels;
			models = new Model[numberOfModels];
		}

		public NetworkBuilder addModel(Model nextModel){
		   this.models[counter] = nextModel;
		   counter++;
		   return this;
		}

		public Network build(){
			Network network = new Network();
			network.models = this.models;
		    network.counter = numberOfModels;
		    return network;
		}
	}
}