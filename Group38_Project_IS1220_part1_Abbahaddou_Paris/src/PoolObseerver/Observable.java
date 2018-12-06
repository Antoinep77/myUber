package PoolObseerver;

public interface Observable {
	/**
	 * add an observer
	 * @param observer to add
	 */
	public void registerObserver(ObserverPool observer);
	/**
	 * remove an observer
	 * @param observer to remove
	 */
	public void removeObserver(ObserverPool observer);
	/**
	 * send a message to the cusomer in the car, when a new customer is sitting in the same car
	 */
	public void notifyObservers();
}
