package PoolObseerver;

public interface ObserverPool {
	/**
	 * send a message to the customer 
	 * @param observable
	 */
	public void update(Observable observable);
}
