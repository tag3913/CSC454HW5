public interface Model<delta, lambda>{
    public double timeAdvance();
    public void deltaInt();
    public int lambda();
    public void deltaExt(double e, int q);
    public void deltaCon(int q);

    public void newDelta(Model in);
    public void newLambda(Model out);
}