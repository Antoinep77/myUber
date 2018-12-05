package PoolObseerver;

public interface Observable {
	public void registerObserver(ObserverPool observer);
	public void removeObserver(ObserverPool observer);
	public void notifyObservers();
}
